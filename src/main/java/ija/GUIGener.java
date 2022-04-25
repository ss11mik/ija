package ija;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.Node;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

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
        UMLController ctx;

        /**
         * Zajistuje focus na graficky objekt, ktery byl chycen
         * @param n aktivni graficky objekt
         */
        public DragDrop(Pane n, UMLController ctx) {
            c = n;
            this.ctx = ctx;
        }

        /**
         * Zajistuje presouvani grafickych objektu
         * @param event udalost mysi
         */
        @Override
        public void handle(MouseEvent event) {
//             c.relocate(event.getSceneX() - c.getWidth(), event.getSceneY() - c.getHeight());
// c.setCenterX(c.getCenterX() + 400);

//             c.setTranslateX(
//                     event.initialTranslateX
//                         + event.getX()
//                         - dragContext.mouseAnchorX);
            c.setTranslateX(event.getSceneX() - c.getWidth());
//             c.startFullDrag();
// ctx.root.layout();
// c.refresh();
// c.notify();
//         event.consume();
// c.getChildren().get(0).fire();
// TextArea ta_methods = (TextArea) c.lookup("#ta_methods");
// c.requestFocus();
// ta_methods.requestFocus();
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
            TextArea ta_attrs = (TextArea) attrs.lookup("#ta_attributes");
            for (UMLAttribute att : data.getAttributesProperty()) {
                attrs.getChildren().add(new Label(att.toString()));
            }

            data.getAttributesProperty().addListener((observable, oldValue, newValue) -> {
                /**
                * Pripise dalsi atribut mezi atributy tridy
                */
                ta_attrs.setText(ta_attrs.getText() + newValue.toString() + "\n");
                ta_attrs.setPrefHeight(ta_attrs.getHeight() + 18);
            });
            Button btnAddAttr = (Button) vbox.lookup("#btn_addAttribute");
            btnAddAttr.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    data.addAttribute(new UMLAttribute("aaaaa"));
                    //TODO input box
                }
            });



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

            vbox.setOnMouseDragged(new DragDrop(vbox, ctx));

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
