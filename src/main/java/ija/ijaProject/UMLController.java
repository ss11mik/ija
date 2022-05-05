package ija.ijaProject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.Node;

import java.util.List;

import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Bounds;

import ija.ImportExport;
import ija.GUIGener;
import ija.Draggable;


import ija.dataStructures.*;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Obsluhuje GUI pro tvorbu diagramu.
 * Obsahuje metody volane pri interakci uzivatele.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLController {

    /** koren dat */
    UML data = new UML("New UML Diagram");

    /** Hlavni prvek base-view */
    @FXML
    public BorderPane root;

    /** Pro zobrazovani diagramu */
    @FXML
    private TabPane tabs;

    /** TextField v prave dolni casti */
    @FXML
    private TextField text_renameDiagram;

    /** TextField v pravem hornim rohu */
    @FXML
    private TextField textField_names;

    /** TextArea v okne tridy pro atributy */
    @FXML
    private TextArea ta_attributes = new TextArea();



    @FXML
    protected void initialize() {

        try {
            Tab tab = FXMLLoader.load(this.getClass().getResource("tab-class.fxml"));
            tab.textProperty().bind(data.getClassDiagram().getNameProperty());

            tabs.getTabs().add(tab);

            refreshTabs();
            data.getSeqDiagramsProperty().addListener((observable, oldValue, newValue) -> {refreshTabs(newValue);});

            refreshClasses(data.getClassDiagram().getClasses());
            data.getClassDiagram().getClassesProperty().addListener((observable, oldValue, newValue) -> {refreshClasses(newValue);});
        } catch (IOException e) {
        }
    }

    void refreshTabs () {
        refreshTabs(data.getSeqDiagrams());
    }
    void refreshTabs (List<UMLDiagramSequence> newValue) {
        for (UMLDiagramSequence seqDia : newValue) {
    System.out.println("aa");

        }
    }

    void refreshClasses () {
        refreshClasses(data.getClassDiagram().getClasses());
    }
    void refreshClasses (List<UMLClass> newValue) {
        Pane p = (Pane) getCurrentTabContent().lookup("#Content");
        ArrayList<Node> toRemove = new ArrayList();
        for (Node child : p.getChildren()) {
            if (child instanceof VBox)
                toRemove.add(child);
        }
        p.getChildren().removeAll(toRemove);

        for (UMLClass cl : newValue) {
            VBox newClass = GUIGener.createClass(this, cl);
            p.getChildren().add(newClass);
        }
    }


    /** filtr pro vyber JSON souboru pro nacteni */
    private static FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");

    /**
     * @return Vrati aktivni graficky objekt diagramu
     */
    private Pane getCurrentTabContent () {
        return (Pane) tabs.getSelectionModel().getSelectedItem().getContent();
    }

    /**
     * Nabidne k prohledani soubory k nacteni (pouze JSON)
     */
    @FXML
    private void menuOpen () {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            data = ImportExport.load(file);
            initialize();
        }
    }

    /**
     * Ulozi data do souboru zvoleneho ve vyskakovacim okne
     */
    @FXML
    private void menuSave () {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            ImportExport.save(data, file);
        }
    }

    /**
     * Zavre aplikaci
     */
    @FXML
    private void menuClose () {
        //TODO ask if save
        System.exit(0);
    }

    /**
     * Prida novou tridu do diagramu.
     * Nazev tridy odpovida textu uvnitr TextFieldu v pravem hornim rohu
     */
    @FXML
    private void addClass () {

        TextInputDialog dialog = new TextInputDialog("enter class name");
        dialog.setTitle("New Class");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter class name:");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            UMLClass cl = new UMLClass(result.get(), true);
            refreshClasses();
            data.getClassDiagram().addClass(cl);
        }
    }

    @FXML
    private void removeClass(){
        //TODO
        // in UI

        Dialog<UMLClass> dialog = new Dialog();
        dialog.setTitle("Remove Class");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        ComboBox comBoxClass = new ComboBox();
        comBoxClass.getItems().setAll(data.getClassDiagram().getClasses());
        comBoxClass.getSelectionModel().select(0);

        grid.add(new Label("Please enter class to remove:"),0,1);
        grid.add(comBoxClass,1,1);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> comBoxClass.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return (UMLClass) comBoxClass.getSelectionModel().getSelectedItem();
            }
            return null;
        });

        Optional<UMLClass> result = dialog.showAndWait();
        result.ifPresent(cl -> {
            data.getClassDiagram().removeClass(cl);

            Node toRemove = null;
            Pane content = ((Pane) getCurrentTabContent().lookup("#Content"));
            for (Node e : content.getChildren()) {
                if (e.lookup("#name") != null && ((Label) e.lookup("#name")).getText().equals(cl.getName()))
                    toRemove = e;
            }
            content.getChildren().remove(toRemove);
        });


    }


    /**
     * Prida novy objekt do diagramu
     */
    @FXML
    private void addObject () {

        Dialog<Pair<String,UMLClass>> dialog = new Dialog();
        dialog.setTitle("Add object");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        TextField name = new TextField();
        name.setPromptText("name");
        ComboBox comBoxClass = new ComboBox();
        comBoxClass.getItems().setAll(data.getClassDiagram().getClasses());
        comBoxClass.getSelectionModel().select(0);

        grid.add(new Label("Name of new object:"), 0,0);
        grid.add(name,1,0);
        grid.add(new Label("Class of new object:"),0,1);
        grid.add(comBoxClass,1,1);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(name.getText(), (UMLClass) comBoxClass.getSelectionModel().getSelectedItem());
            }
            return null;
        });

        Optional<Pair<String,UMLClass>> result = dialog.showAndWait();
        result.ifPresent(cl -> {

            UMLObject obj = new UMLObject(cl.getKey(), cl.getValue());

            VBox newClass = GUIGener.createSeqObject(this, obj);
            ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);

            //TODO the correct one
            data.getSeqDiagrams().get(0).addObject(obj);
        });
    }

    /**
     * Vytvori novou kartu pro novy sekvencni diagram
     */
    @FXML
    private void menuNewSeq () throws IOException {

        UMLDiagramSequence newSeq = new UMLDiagramSequence("Sequence Diagram");

        Tab tab = FXMLLoader.load(this.getClass().getResource("tab-seq.fxml"));
        tab.textProperty().bind(newSeq.getNameProperty());

        tabs.getTabs().add(tab);

        data.addSeqDiagram(newSeq);
    }

    /**
     * Ve vyskakovacim okne vypise napovedu a zpusob pouziti
     */
    @FXML
    private void menuHelp () {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UML Editor IJA '22 Edition");
        alert.setHeaderText("UML Editor IJA '22 Edition");
        alert.setContentText("Authors:\nxmikul69,\nxmechl01\n\n @ BUT FIT");
        alert.showAndWait().ifPresent(rs -> { });
    }

    /**
     * Ve vyskakovacim okne zobrazi zakladni informace o aplikaci
     */
    @FXML
    private void menuAbout () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UML Editor IJA '22 Edition");
        alert.setHeaderText("UML Editor IJA '22 Edition");
        alert.setContentText("Authors:\nxmikul69,\nxmechl01\n\n @ BUT FIT");
        alert.showAndWait().ifPresent(rs -> { });
    }

    /**
     * Prejmenuje diagram trid na nazev zadany v pravem dolnim rohu
     */
    @FXML
    private void renameDiagram(){
        int i = tabs.getSelectionModel().getSelectedIndex();

        // expects that class dia is always first
        if (i == 0) {
            data.getClassDiagram().setName(text_renameDiagram.getText());
        }
        else {
            data.getSeqDiagrams().get(i-1).setName(text_renameDiagram.getText());
        }
    }


    Node a, bb;
    @FXML
    private void addRelation(){

        Node first = a;
        Node second = bb;

        Line line = new Line();
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);

        Pane content = ((Pane)getCurrentTabContent().lookup("#Content"));

        ObjectBinding<Bounds> l1 = Bindings.createObjectBinding(() -> {
            return first.getBoundsInParent();
        }, first.boundsInParentProperty(), first.localToSceneTransformProperty(), content.localToSceneTransformProperty());

        ObjectBinding<Bounds> l2 = Bindings.createObjectBinding(() -> {
            return second.getBoundsInParent();
        }, bb.boundsInParentProperty(), second.localToSceneTransformProperty(), content.localToSceneTransformProperty());


        DoubleBinding startX = Bindings.createDoubleBinding(() -> {
            Bounds b = l1.get();
            return b.getMinX() + b.getWidth() / 2 ;
        }, l1);
        DoubleBinding startY = Bindings.createDoubleBinding(() -> {
            Bounds b = l1.get();
            return b.getMinY() + b.getHeight() / 2 ;
        }, l1);


        DoubleBinding endX = Bindings.createDoubleBinding(() -> {
            Bounds b = l2.get();
            return b.getMinX() + b.getWidth() / 2 ;
        }, l2);
        DoubleBinding endY = Bindings.createDoubleBinding(() -> {
            Bounds b = l2.get();
            return b.getMinY() + b.getHeight() / 2 ;
        }, l2);

        line.startXProperty().bind(startX);
        line.startYProperty().bind(startY);
        line.endXProperty().bind(endX);
        line.endYProperty().bind(endY);


        content.getChildren().add(line);
        line.toBack();
    }
    @FXML
    private void addMessage(){

System.out.println("aa");


    }


}
