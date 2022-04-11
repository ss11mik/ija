/**
 * Obsluhuje tvorbu a editaci diagramu.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import ija.data_structures.*;
import ija.ija_project.UMLController;


public class GUIGener {

    private static Color bgColor = Color.DARKCYAN;
    private static Color strokeColor = Color.BLACK;
    private static float strokeWidth = 8;

    private static class DragDrop implements EventHandler<MouseEvent> {
        Pane c;

        public DragDrop(Pane n) {
            c = n;
        }

        @Override
        public void handle(MouseEvent event) {
            c.relocate(event.getSceneX() - c.getWidth(), event.getSceneY() - c.getHeight());
        }
    };



    public static VBox createClass (UMLController ctx, UMLClass data) {
        try {
            final VBox vbox = FXMLLoader.load(ctx.getClass().getResource("class-box.fxml"));


            Label name = (Label) vbox.lookup("#name");
            name.setText(data.get_name());

            VBox attrs = (VBox) vbox.lookup("#attrs");
            for (UMLAttribute att : data.get_attributes()) {
                attrs.getChildren().add(new Label(att.toString()));
            }

            VBox methods = (VBox) vbox.lookup("#methods");
            for (UMLMethod meth : data.get_methods()) {
                methods.getChildren().add(new Label(meth.get_name()));
            }

            Button btnAddMethod = (Button) vbox.lookup("#btn_addmethod");
            btnAddMethod.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    data.add_method(new UMLMethod("aaaaa"));
                }
            });

            vbox.setOnMouseDragged(new DragDrop(vbox));


            return vbox;
        }
        catch (IOException e) {
            return null;
        }
    }

    public static VBox createSeqObject (UMLController ctx, UMLObject data) {
        try {
            final VBox vbox = FXMLLoader.load(ctx.getClass().getResource("seq-obj.fxml"));


            Label name = (Label) vbox.lookup("#name");
            name.setText(data.get_instance().get_name());
/*
            VBox attrs = (VBox) vbox.lookup("#attrs");
            for (UML_Attribute att : data.get_attributes()) {
                attrs.getChildren().add(new Label(att.get_name()));
            }

            VBox methods = (VBox) vbox.lookup("#methods");
            for (UML_Method meth : data.get_methods()) {
                methods.getChildren().add(new Label(meth.get_name()));
            }*/


            return vbox;
        }
        catch (IOException e) {
            return null;
        }
    }

}
