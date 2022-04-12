/**
 * Obsluhuje GUI pro tvorbu diagramu.
 * Obsahuje metody volane pri interakci uzivatele.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.ija_project;

import javafx.fxml.FXMLLoader;
import java.io.File;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import java.io.IOException;

import ija.ImportExport;
import ija.GUIGener;
import javafx.scene.layout.Pane;


import ija.data_structures.*;

public class UMLController {

    // the root of data
    UML data = new UML("test");

    @FXML
    private BorderPane root;

    @FXML
    private TabPane tabs;

    @FXML
    private TextField text_renameDiagram;

    @FXML
    private TextField textField_names;

    @FXML
    private TextArea ta_attributes = new TextArea();

    @FXML
    private TextArea ta_methods = new TextArea();


    private static FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");


    private Pane getCurrentTabContent () {
        return (Pane) tabs.getSelectionModel().getSelectedItem().getContent();
    }


    @FXML
    private void generFoo() {
        UMLClass cl = new UMLClass("aaa", true);
//         class_diagram.add_class(cl);

      //  GUIGener.createClass(cl);
    }


    @FXML
    private void menuOpen () {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            data = ImportExport.load(file);
        }
    }

    @FXML
    private void menuSave () {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            ImportExport.save(data, file);
        }
    }

    @FXML
    private void menuClose () {
        //TODO ask if save
        System.exit(0);
    }

    @FXML
    private void addClass () {
        UMLClass cl = new UMLClass(textField_names.getText(), true);

//         cl.add_attribute(new UMLAttribute("sdwsa"));
//         cl.add_method(new UMLMethod("sdwsa"));
        VBox newClass = GUIGener.createClass(this, cl);


        ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);

        data.getClassDiagram().addClass(cl);
    }


    @FXML
    private void addObject () {
        //TODO
        UMLObject cl = new UMLObject(new UMLClass("New ObjectClassa", true));

        VBox newClass = GUIGener.createSeqObject(this, cl);
        ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);

//         data.getClassDiagram().add_class(cl);
        data.getSeqDiagrams().get(0).addObject(cl);
    }


    @FXML
    private void menuNewSeq () throws IOException {

        Tab tab = FXMLLoader.load(this.getClass().getResource("tab-seq.fxml"));
        tabs.getTabs().add(tab);

        UMLDiagramSequence newSeq = new UMLDiagramSequence("todo");
        data.addSeqDiagram(newSeq);
    }

    @FXML
    private void menuHelp () {
    data.getClassDiagram().getClasses().get(0).setName("Renamed");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UML Editor IJA '22 Edition");
        alert.setHeaderText("UML Editor IJA '22 Edition");
        alert.setContentText("Authors:\nxmikul69,\nxmechl01\n\n @ BUT FIT");
        alert.showAndWait().ifPresent(rs -> { });
    }

    @FXML
    private void menuAbout () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UML Editor IJA '22 Edition");
        alert.setHeaderText("UML Editor IJA '22 Edition");
        alert.setContentText("Authors:\nxmikul69,\nxmechl01\n\n @ BUT FIT");
        alert.showAndWait().ifPresent(rs -> { });
    }

    @FXML
    private void renameDiagram(){

        data.getClassDiagram().setName(text_renameDiagram.getText());
    }

    @FXML
    private void addAttribute(){
        // prida atribut do diagramu kdyz je volana z base-view, ale nezmeni text v class-box
        data.getClassDiagram().getClasses().get(0).addAttribute(new UMLAttribute(textField_names.getText()));
        ta_attributes.setText("pridano"); //ta_attributes.getText() + "\n" + textField_names.getText());
    }

    @FXML
    private void writeTextAttr(){
        // zmeni text v class-box, ale neumi vytvorit atribut v base-view
        ta_attributes.setText(ta_attributes.getText() + "attribut" + "\n");
        ta_attributes.setPrefHeight(ta_attributes.getHeight() + 18);
    }

    @FXML
    private void writeTextMeth(){
        ta_methods.setText(ta_methods.getText() + "metoda" + "\n");
        ta_methods.setPrefHeight(ta_methods.getHeight() + 18);
    }



}
