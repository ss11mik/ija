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

        refreshTabs();
        data.getSeqDiagramsProperty().addListener((observable, oldValue, newValue) -> {refreshTabs(newValue);});

//         refreshClasses(data.getClassDiagram().getClasses());
        data.getClassDiagram().getClassesProperty().addListener((observable, oldValue, newValue) -> {refreshClasses(newValue);});

            int i =1;
        for (UMLDiagramSequence seq : data.getSeqDiagrams()) {
            final int thisI = i;
//             refreshObjects(seq.getObjectsProperty(), i);
            seq.getObjectsProperty().addListener((observable, oldValue, newValue) -> {refreshObjects(newValue, thisI);});
            i++;
        }

    }

    void refreshTabs () {
        refreshTabs(data.getSeqDiagrams());
    }
    void refreshTabs (List<UMLDiagramSequence> newValue) {

        try {
            Tab tab = FXMLLoader.load(this.getClass().getResource("tab-class.fxml"));
            tab.textProperty().bind(data.getClassDiagram().getNameProperty());

            tabs.getTabs().clear();
            tabs.getTabs().add(tab);
            refreshClasses(data.getClassDiagram().getClasses());

            for (UMLDiagramSequence seqDia : newValue) {

                tab = FXMLLoader.load(this.getClass().getResource("tab-seq.fxml"));
                tab.textProperty().bind(seqDia.getNameProperty());

                tabs.getTabs().add(tab);

                for (UMLObject obj : seqDia.getObjectsProperty()) {
                    VBox newClass = GUIGener.createSeqObject(this, obj);
//                     ((Pane) (tab.getContent()).lookup("#Content")).getChildren().add(newClass);
                }
            }
        } catch (IOException e) {
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

    void refreshObjects (List<UMLObject> newValue, int diaIndex) {
        Pane p = (Pane) getCurrentTabContent().lookup("#Content");
        p.getChildren().clear();

        for (UMLObject obj : newValue) {
            VBox newClass = GUIGener.createSeqObject(this, obj);
            (p).getChildren().add(newClass);
        }
        //TODO, need to know the tab
    }




    /** filtr pro vyber JSON souboru pro nacteni */
    private static FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");


    /**
     * @return Vrati aktivni graficky objekt diagramu
     */
    private Pane getCurrentTabContent () {
        return (Pane) tabs.getSelectionModel().getSelectedItem().getContent();
    }

    @FXML
    private void generFoo() {
        UMLClass cl = new UMLClass("aaa", true);
//         class_diagram.add_class(cl);

      //  GUIGener.createClass(cl);
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
    private void addInterface () {

        TextInputDialog dialog = new TextInputDialog("enter interface name");
        dialog.setTitle("New Interface");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter interface name:");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            UMLClass cl = new UMLClass(result.get(), false);
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

            data.getSeqDiagrams().get(tabs.getSelectionModel().getSelectedIndex()-1).addObject(obj);
        });
    }

    /**
     * Vytvori novou kartu pro novy sekvencni diagram
     */
    @FXML
    private void menuNewSeq () throws IOException {

        UMLDiagramSequence newSeq = new UMLDiagramSequence("Sequence Diagram");

//         tabs.getTabs().add(tab);

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

        UMLDiagramSequence current = data.getSeqDiagrams().get(tabs.getSelectionModel().getSelectedIndex()-1);

        Dialog<UMLMessage> dialog = new Dialog();
        dialog.setTitle("Add message");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        ComboBox comBoxType = new ComboBox();
        comBoxType.getItems().setAll(UMLMessage.MessageType.values());
        comBoxType.getSelectionModel().select(0);
        ComboBox comBoxObjectfrom = new ComboBox();
        comBoxObjectfrom.getItems().setAll(current.getObjects());
        comBoxObjectfrom.getSelectionModel().select(0);
        ComboBox comBoxObjectto = new ComboBox();
        comBoxObjectto.getItems().setAll(current.getObjects());
        comBoxObjectto.getSelectionModel().select(0);
        TextField textTime = new TextField();
        textTime.setPromptText("time in seconds");
        ComboBox<UMLMethod> comBoxMethod = new ComboBox();

        comBoxMethod.getItems().setAll(((UMLObject) comBoxObjectto.valueProperty().get()).getInstance().getMethods());
        comBoxMethod.getSelectionModel().select(0);

        comBoxObjectto.valueProperty().addListener((observable, oldValue, newValue) -> {
            comBoxMethod.getItems().setAll(((UMLObject) newValue).getInstance().getMethods());
            comBoxMethod.getSelectionModel().select(0);
        });

        grid.add(new Label("Message type:"), 0,0);
        grid.add(comBoxType,1,0);
        grid.add(new Label("Message from:"),0,1);
        grid.add(comBoxObjectfrom,1,1);
        grid.add(new Label("Message to:"),0,2);
        grid.add(comBoxObjectto,1,2);
        grid.add(new Label("Time:"),0,3);
        grid.add(textTime,1,3);
        grid.add(new Label("Method:"),0,4);
        grid.add(comBoxMethod,1,4);


        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> comBoxType.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new UMLMessage((UMLMessage.MessageType) comBoxType.getSelectionModel().getSelectedItem(),
                        (UMLObject) comBoxObjectfrom.getSelectionModel().getSelectedItem(),
                        (UMLObject) comBoxObjectto.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(textTime.getText()),
                        (UMLMethod) comBoxMethod.getSelectionModel().getSelectedItem());
            }
            return null;
        });

        Optional<UMLMessage> result = dialog.showAndWait();
        result.ifPresent(cl -> {

            UMLMessage msg = new UMLMessage(cl.getType(), cl.getFrom(), cl.getTo(), cl.getTimeStart(), cl.getMethod());

            current.addMessage(msg);
        });


    }


}
