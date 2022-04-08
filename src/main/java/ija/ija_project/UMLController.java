package ija.ija_project;

import java.util.List;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

import ija.ImportExport;

import ija.data_structures.*;

public class UMLController {

    // the root of data
    UML_Diagram class_diagram = new UML_Diagram("test");
    List<UML_Diagram_Sequence> seq_diagrams = new ArrayList();

    @FXML
    private TabPane tabs;

    @FXML
    private void redrawCanvas() {

    }


    @FXML
    private void menuOpen () {
        String filename = "foo.json"; //TODO ask user
        class_diagram = ImportExport.load(filename);
    }

    @FXML
    private void menuSave () {
        String filename = "foo.json"; //TODO ask user
        ImportExport.save(class_diagram, filename);
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
