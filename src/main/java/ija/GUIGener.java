package ija;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.control.Label;

import ija.data_structures.*;
import ija.ija_project.UMLController;


public class GUIGener {

    private static Color bgColor = Color.DARKCYAN;
    private static Color strokeColor = Color.BLACK;
    private static float strokeWidth = 8;


    public static VBox createClass (UMLController ctx, UML_Class data) {

            VBox vbox = null;
            try {
                vbox = FXMLLoader.load(ctx.getClass().getResource("class-box.fxml"));
            }
            catch (IOException e) {
               return null;
            }

            VBox attrs = (VBox) vbox.lookup("#attrs");
            for (UML_Attribute att : data.get_attributes()) {
                attrs.getChildren().add(new Label(att.get_name()));
            }

            VBox methods = (VBox) vbox.lookup("#methods");
            for (UML_Method meth : data.get_methods()) {
                methods.getChildren().add(new Label(meth.get_name()));
            }



            return vbox;

    }

}
