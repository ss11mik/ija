package ija.ija_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ija.ImportExport;

import java.io.IOException;
import ija.data_structures.*;


public class UMLEditor extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UMLEditor.class.getResource("base-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("UML Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    UML_Diagram a = new UML_Diagram();

        ImportExport.save(a, "somedatafile.json"); //foo
        UML_Diagram d = ImportExport.load("somedatafile.json"); //foo

        launch();
    }

}
