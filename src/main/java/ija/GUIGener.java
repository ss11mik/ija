package ija;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import ija.dataStructures.*;
import ija.ijaProject.UMLController;
import java.util.Observer;
import java.util.Observable;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;

/**
 * Obsluhuje tvorbu a editaci diagramu.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class GUIGener {

    private static Color bgColor = Color.DARKCYAN;
    private static Color strokeColor = Color.BLACK;
    private static float strokeWidth = 8;

    /**
     * Obsluhuje drag and drop funkce pro presouvani objektu
     */
    private static class DragDrop implements EventHandler<MouseEvent> {
        Pane c;

        /**
         * Zajistuje focus na graficky objekt, ktery byl chycen
         * @param n aktivni graficky objekt
         */
        public DragDrop(Pane n) {
            c = n;
        }

        /**
         * Zajistuje presouvani grafickych objektu
         * @param event udalost mysi
         */
        @Override
        public void handle(MouseEvent event) {
            c.relocate(event.getSceneX() - c.getWidth(), event.getSceneY() - c.getHeight());
        }
    };


    /**
     * Zajistuje prekresleni GUI pri zmene tridy
     * @param ctx controller pro GUI
     * @param data data UML tridy
     * @return Vraci upraveny vertical box pro GUI
     */
    public static VBox createClass (UMLController ctx, UMLClass data) {
        try {
            final VBox vbox = FXMLLoader.load(ctx.getClass().getResource("class-box.fxml"));


            Label name = (Label) vbox.lookup("#name");
            name.textProperty().bind(data.getNameProperty());

            VBox attrs = (VBox) vbox.lookup("#attrs");
            for (UMLAttribute att : data.getAttributesProperty()) {
                attrs.getChildren().add(new Label(att.toString()));
            }



            VBox methods = (VBox) vbox.lookup("#methods");
            TextArea ta_methods = (TextArea) methods.lookup("#ta_methods");

            for (UMLMethod meth : data.getMethodsProperty()) {
                methods.getChildren().add(new Label(meth.toString()));
            }

            data.getMethodsProperty().addListener((observable, oldValue, newValue) -> {
                /**
                * Pripise dalsi metodu mezi metody tridy
                */
                ta_methods.setText(ta_methods.getText() + newValue.toString() + "\n");
                ta_methods.setPrefHeight(ta_methods.getHeight() + 18);
            });

            Button btnAddMethod = (Button) vbox.lookup("#btn_addmethod");
            btnAddMethod.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    data.addMethod(new UMLMethod("aaaaa"));
                    //TODO input box
                }
            });

            vbox.setOnMouseDragged(new DragDrop(vbox));


            return vbox;
        }
        catch (IOException e) {
            return null;
        }
    }

    /**
     * Zajistuje prekresleni GUI pri zmene objektu
     * @param ctx controller pro GUI
     * @param data data UML objektu
     * @return Vraci upraveny vertical box pro GUI
     */
    public static VBox createSeqObject (UMLController ctx, UMLObject data) {
        try {
            final VBox vbox = FXMLLoader.load(ctx.getClass().getResource("seq-obj.fxml"));


            Label name = (Label) vbox.lookup("#name");
            name.setText(data.getInstance().getName());
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
