package ija.ija_project;

import javafx.fxml.FXMLLoader;
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
import java.io.IOException;
import javafx.scene.control.Alert;

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
    private void addClass () {

    }



    @FXML
    private void menuNewSeq () throws IOException {

        Tab tab = FXMLLoader.load(this.getClass().getResource("tab-seq.fxml"));
        tabs.getTabs().add(tab);

        UML_Diagram_Sequence newSeq = new UML_Diagram_Sequence("todo");
        seq_diagrams.add(newSeq);
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
