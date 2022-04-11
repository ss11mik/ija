/**
 * Obsluhuje GUI pro tvorbu diagramu.
 * Obsahuje metody volane pri interakci uzivatele.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.ija_project;

import javafx.fxml.FXMLLoader;
import java.io.File;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import java.io.IOException;
import javafx.scene.control.Alert;
import ija.ImportExport;
import ija.GUIGener;
import javafx.scene.layout.Pane;


import ija.data_structures.*;

public class UMLController {

    // the root of data
    UML data = new UML("test");

    @FXML
    private VBox root;

    @FXML
    private TabPane tabs;

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
        UMLClass cl = new UMLClass("New Class", true);

//         cl.add_attribute(new UMLAttribute("sdwsa"));
        VBox newClass = GUIGener.createClass(this, cl);


        ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);

        data.getClassDiagram().add_class(cl);
    }

    @FXML
    private void addObject () {
        //TODO
        UMLObject cl = new UMLObject(new UMLClass("New ObjectClassa", true));

        VBox newClass = GUIGener.createSeqObject(this, cl);
        ((Pane)getCurrentTabContent().lookup("#Content")).getChildren().add(newClass);

//         data.getClassDiagram().add_class(cl);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UML Editor IJA '22 Edition");
        alert.setHeaderText("UML Editor IJA '22 Edition");
        alert.setContentText("Authors:\nxmikul69,\nxmechl01\n\n @ BUT FIT");
        alert.showAndWait().ifPresent(rs -> { });
    }

}
