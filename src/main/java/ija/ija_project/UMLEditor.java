package ija.ija_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.Group;

import java.io.IOException;


public class UMLEditor extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UMLEditor.class.getResource("base-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 540);
        stage.setTitle("UML Editor");
        stage.setScene(scene);
        stage.show();

        TabPane tabs = (TabPane) scene.lookup("#tabs");
        tabs.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            Group groupClass = (Group) scene.lookup("#group_class");
            Group groupSequence = (Group) scene.lookup("#group_sequnce");

            if (newTab.getId() != null && newTab.getId().equals("tab-class")) {
                groupClass.setVisible(true);
                groupSequence.setVisible(false);
            }
            else {
                groupClass.setVisible(false);
                groupSequence.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}
