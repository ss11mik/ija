package ija;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

import ija.data_structures.*;
import ija.ija_project.UMLController;


public class GUIGener {

    private static Color bgColor = Color.DARKCYAN;
    private static Color strokeColor = Color.BLACK;
    private static float strokeWidth = 8;


//     public static VBox createClass (Context ctx, UML_Class data) {
    public static VBox createClass (UMLController ctx, UML_Class data) {
//         Rectangle shape = new Rectangle();
//
//         shape.setWidth(300.0f);
//         shape.setHeight(150.0f);
//         //Setting other properties
//         shape.setFill(bgColor);
//         shape.setStrokeWidth(strokeWidth);
//         shape.setStroke(strokeColor);


            VBox vbox = null;
            try {
                vbox = FXMLLoader.load(ctx.getClass().getResource("class-box.fxml"));
            }
            catch (IOException e) {
             //   return null;
            }



            return vbox;

    }

}
