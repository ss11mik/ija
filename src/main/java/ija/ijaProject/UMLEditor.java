package ija.ijaProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.Group;

import java.io.IOException;
import ija.ijaProject.UMLController;

/**
 * Obsluhuje spusteni GUI a prvotni nastaveni zobrazeni.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLEditor extends Application {

    /**
     * Nastavi uvodni zobrazeni GUI
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UMLEditor.class.getResource("base-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 540);
        stage.setTitle("UML Editor");
        stage.setScene(scene);
        stage.show();

        TabPane tabs = (TabPane) scene.lookup("#tabs");
        tabs.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab != null) {
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
            }
        });
    }

    /**
     * Nastartuje aplikaci
     * @param args parametry terminalu
     */
    public static void main(String[] args) {
        launch();
    }

}
