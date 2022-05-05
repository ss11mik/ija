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
    UML data = new UML("test");

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
    private Button bt_addclass;



    @FXML
    protected void initialize() {

        try {
            Tab tab = FXMLLoader.load(this.getClass().getResource("tab-class.fxml"));
            tab.textProperty().bind(data.getClassDiagram().getNameProperty());

            tabs.getTabs().add(tab);
        } catch (IOException e) {
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
        UMLClass cl = new UMLClass(textField_names.getText(), true);

        VBox newClass = GUIGener.createClass(this, cl);

        ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);


        data.getClassDiagram().addClass(cl);

        a = bb;
        bb = newClass;
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

    @FXML
    private void addClassForm () {

        TextInputDialog dialog = new TextInputDialog("enter class name");
        dialog.setTitle("New Class");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter class name:");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            System.out.println("class name: " + result.get());
            UMLClass cl = new UMLClass(result.get(), true);
            data.getClassDiagram().addClass(cl);
            //VBox newClass = GUIGener.createClass(this, cl);
            //((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);
        }
    }

    @FXML
    private void addAttribForm () {

        Dialog<Pair<String, String>> dialog = new Dialog();
        dialog.setTitle("New Attribute");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        TextField name = new TextField();
        name.setPromptText("name");
        TextField datType = new TextField();
        datType.setPromptText("data type");
        TextField accMod = new TextField();
        accMod.setPromptText("access modifier");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name,1,0);
        grid.add(new Label("Data type:"),0,1);
        grid.add(datType,1,1);
        grid.add(new Label("Access modifier:"),0,2);
        grid.add(accMod,1,2);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(name.getText(), datType.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(nameType -> {
            System.out.println("Name=" + nameType.getKey() + ", Type=" + nameType.getValue());
        });
    }

    @FXML
    private void addMethodForm () {

        Dialog<Pair<String, String>> dialog = new Dialog();
        dialog.setTitle("New Method");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        TextField name = new TextField();
        name.setPromptText("name");
        TextField datType = new TextField();
        datType.setPromptText("return data type");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name,1,0);
        grid.add(new Label("Return data type:"),0,1);
        grid.add(datType,1,1);


        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(name.getText(), datType.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(nameType -> {
            System.out.println("Name=" + nameType.getKey() + ", Return type=" + nameType.getValue());
        });
    }


    @FXML
    private void addArgumentForm () {

        Dialog<Pair<String, String>> dialog = new Dialog();
        dialog.setTitle("New Argument");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        TextField name = new TextField();
        name.setPromptText("name");
        TextField datType = new TextField();
        datType.setPromptText("data type");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name,1,0);
        grid.add(new Label("Data type:"),0,1);
        grid.add(datType,1,1);


        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(name.getText(), datType.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(nameType -> {
            System.out.println("Name=" + nameType.getKey() + ", Type=" + nameType.getValue());
        });
    }

    /**
     * Prida novy objekt do diagramu
     */
    @FXML
    private void addObject () {

        Dialog<UMLClass> dialog = new Dialog();
        dialog.setTitle("Add object");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        ComboBox comBoxClass = new ComboBox();
        comBoxClass.getItems().setAll(data.getClassDiagram().getClasses());
        comBoxClass.getSelectionModel().select(0);

        grid.add(new Label("Class of new object:"),0,1);
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

            UMLObject obj = new UMLObject(cl);

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

    /**
     * Prida atribut do tridy
     */
    @FXML
    private void addAttribute(){
        // prida atribut do diagramu kdyz je volana z base-view, ale nezmeni text v class-box
        data.getClassDiagram().getClasses().get(0).addAttribute(new UMLAttribute(textField_names.getText()));
        ta_attributes.setText("pridano"); //ta_attributes.getText() + "\n" + textField_names.getText());
    }

    Node a, bb;
    @FXML
    private void addRelation(){

        Line line = new Line();
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);

        Pane content = ((Pane)getCurrentTabContent().lookup("#Content"));

        ObjectBinding<Bounds> label1InStack = Bindings.createObjectBinding(() -> {
//             Bounds label1InScene = a.localToScene(a.getBoundsInLocal());
            Bounds label1InScene = (a.getBoundsInParent());
            return label1InScene;
//             return content.sceneToLocal(label1InScene);
        }, a.boundsInParentProperty(), a.localToSceneTransformProperty(), content.localToSceneTransformProperty());
// translateProperty
        ObjectBinding<Bounds> label3InStack = Bindings.createObjectBinding(() -> {
//             Bounds label3InScene = bb.localToScene(bb.getBoundsInLocal());
//             return content.sceneToLocal(label3InScene);
            Bounds label1InScene = (bb.getBoundsInParent());
            return label1InScene;
        }, bb.boundsInParentProperty(), bb.localToSceneTransformProperty(), content.localToSceneTransformProperty());


        DoubleBinding startX = Bindings.createDoubleBinding(() -> label1InStack.get().getMaxX(), label1InStack);
        DoubleBinding startY = Bindings.createDoubleBinding(() -> {
            Bounds b = label1InStack.get();
            return b.getMinY() + b.getHeight() / 2 ;
        }, label1InStack);


        DoubleBinding endX = Bindings.createDoubleBinding(() -> label3InStack.get().getMinX(), label3InStack);
        DoubleBinding endY = Bindings.createDoubleBinding(() -> {
            Bounds b = label3InStack.get();
            return b.getMinY() + b.getHeight() / 2 ;
        }, label3InStack);

        line.startXProperty().bind(startX);
        line.startYProperty().bind(startY);
        line.endXProperty().bind(endX);
        line.endYProperty().bind(endY);

        root.getChildren().add(line);
    }


    @FXML
    private void addMessage(){

System.out.println("aa");


    }


}
