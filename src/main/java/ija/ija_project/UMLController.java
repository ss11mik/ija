package ija.ija_project;

import java.io.File;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;

import ija.ImportExport;

import ija.data_structures.*;

public class UMLController {

    // the root of data
    UML_Diagram class_diagram = new UML_Diagram("test");
    List<UML_Diagram_Sequence> seq_diagrams = new ArrayList();

    @FXML
    private VBox root;

    @FXML
    private TabPane tabs;

    private static FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");



    @FXML
    private void redrawCanvas() {

    }


    @FXML
    private void menuOpen () {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            class_diagram = ImportExport.load(file);
        }
    }

    @FXML
    private void menuSave () {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            ImportExport.save(class_diagram, file);
        }
    }

    @FXML
    private void menuClose () {
        //TODO ask if save
        System.exit(0);
    }



    @FXML
    private void menuNewSeq () {
        Tab newTab = new Tab("Sequence diagram" , new Label("foo bar"));
        UML_Diagram_Sequence newSeq = new UML_Diagram_Sequence("todo");

        seq_diagrams.add(newSeq);
        tabs.getTabs().add(newTab);
    }
}
