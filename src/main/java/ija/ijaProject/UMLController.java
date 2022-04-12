package ija.ijaProject;

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


import ija.dataStructures.*;

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
    private BorderPane root;

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

    /** TextArea v okne tridy pro metody */
    @FXML
    private TextArea ta_methods = new TextArea();

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

//         cl.add_attribute(new UMLAttribute("sdwsa"));
//         cl.add_method(new UMLMethod("sdwsa"));
        VBox newClass = GUIGener.createClass(this, cl);


        ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);

        data.getClassDiagram().addClass(cl);
    }


    /**
     * Prida novy objekt do diagramu
     */
    @FXML
    private void addObject () {
        //TODO
        UMLObject cl = new UMLObject(new UMLClass("New ObjectClassa", true));

        VBox newClass = GUIGener.createSeqObject(this, cl);
        ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);

//         data.getClassDiagram().add_class(cl);
        data.getSeqDiagrams().get(0).addObject(cl);
    }

    /**
     * Vytvori novou kartu pro novy sekvencni diagram
     */
    @FXML
    private void menuNewSeq () throws IOException {

        Tab tab = FXMLLoader.load(this.getClass().getResource("tab-seq.fxml"));
        tabs.getTabs().add(tab);

        UMLDiagramSequence newSeq = new UMLDiagramSequence("todo");
        data.addSeqDiagram(newSeq);
    }

    /**
     * Ve vyskakovacim okne vypise napovedu a zpusob pouziti
     */
    @FXML
    private void menuHelp () {
    data.getClassDiagram().getClasses().get(0).setName("Renamed");

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

        data.getClassDiagram().setName(text_renameDiagram.getText());
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

    /**
     * Pripise dalsi atribut mezi atributy tridy
     */
    @FXML
    private void writeTextAttr(){
        // zmeni text v class-box, ale neumi vytvorit atribut v base-view
        ta_attributes.setText(ta_attributes.getText() + "attribut" + "\n");
        ta_attributes.setPrefHeight(ta_attributes.getHeight() + 18);
    }

    /**
     * Pripise dalsi metodu mezi metody tridy
     */
    @FXML
    private void writeTextMeth(){
        ta_methods.setText(ta_methods.getText() + "metoda" + "\n");
        ta_methods.setPrefHeight(ta_methods.getHeight() + 18);
    }

}
