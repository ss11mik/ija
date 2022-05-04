package ija;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import ija.dataStructures.*;
import ija.ijaProject.UMLController;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.ComboBox;

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
            c.relocate(event.getSceneX() - c.getWidth(), event.getSceneY() - c.getHeight());
// c.setCenterX(c.getCenterX() + 400);

//             c.setTranslateX(
//                     event.initialTranslateX
//                         + event.getX()
//                         - dragContext.mouseAnchorX);
//             c.setTranslateX(event.getSceneX() - c.getWidth());
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
                ta_attrs.setText("");
                for(int i = 0; i < newValue.size(); i++){
                    ta_attrs.setText(ta_attrs.getText() + newValue.get(i).toString() + "\n");
                    ta_attrs.setPrefHeight(ta_attrs.getLength()+5);
                }

            });
            Button btnAddAttr = (Button) vbox.lookup("#btn_addAttribute");
            btnAddAttr.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    //data.addAttribute(new UMLAttribute("aaaaa"));
                    //TODO input box
                    Dialog<Object[]> dialog = new Dialog();
                    dialog.setTitle("New Attribute");
                    dialog.setHeaderText(null);

                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20,30,10,30));

                    TextField name = new TextField();
                    name.setPromptText("name");
                    ComboBox datType = new ComboBox();
                    datType.getItems().setAll(UMLAttribute.DataType.values());
                    ComboBox accMod = new ComboBox();
                    accMod.getItems().setAll(UMLAttribute.AccesModifier.values());

                    grid.add(new Label("Name:"), 0, 0);
                    grid.add(name,1,0);
                    grid.add(new Label("Data type:"),0,1);
                    grid.add(datType,1,1);
                    grid.add(new Label("Access modifier:"),0,2);
                    grid.add(accMod,1,2);

                    dialog.getDialogPane().setContent(grid);

                    Platform.runLater(() -> name.requestFocus());

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            Object[] arr = {name.getText(), datType.getSelectionModel().getSelectedItem(), accMod.getSelectionModel().getSelectedItem()};
                            return arr;
                        }
                        return null;
                    });

                    Optional<Object[]> result = dialog.showAndWait();
                    result.ifPresent(nameType -> {
                        System.out.println("Name=" + nameType[0] + ", Type=" + nameType[1] + ", Acces=" + nameType[2]);
                        data.addAttribute(new UMLAttribute(nameType[0].toString(), (UMLAttribute.DataType) nameType[1], (UMLAttribute.AccesModifier) nameType[2]));
                    });
                }
            });

            Button btDelAttr = (Button) vbox.lookup("#btDelAttr");
            btDelAttr.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Dialog<String> dialog = new Dialog();
                    dialog.setTitle("Delete Attribute");
                    dialog.setHeaderText(null);

                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20,30,10,30));

                    TextField name = new TextField();
                    name.setPromptText("name");

                    grid.add(new Label("Name:"), 0, 0);
                    grid.add(name,1,0);

                    dialog.getDialogPane().setContent(grid);

                    Platform.runLater(() -> name.requestFocus());

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            return name.getText();
                        }
                        return null;
                    });

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(nameDel -> {
                        System.out.println("Name=" + nameDel);
                        data.removeAttribute(nameDel);
                    });
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
                ta_methods.setText("");
                for (int i = 0; i < newValue.size(); i++){
                    ta_methods.setText(ta_methods.getText() + newValue.get(i).toString() + "\n");
                    ta_methods.setPrefHeight(ta_methods.getLength()+5);
                }
            });

            Button btnAddMethod = (Button) vbox.lookup("#btn_addmethod");
            btnAddMethod.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    //data.addMethod(new UMLMethod("aaaaa"));
                    //TODO input box
                    Dialog<Pair<String, UMLAttribute.DataType>> dialog = new Dialog();
                    dialog.setTitle("New Method");
                    dialog.setHeaderText(null);

                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20,30,10,30));

                    TextField name = new TextField();
                    name.setPromptText("name");
                    ComboBox datType = new ComboBox();
                    datType.getItems().setAll(UMLAttribute.DataType.values());

                    grid.add(new Label("Name:"), 0, 0);
                    grid.add(name,1,0);
                    grid.add(new Label("Return data type:"),0,1);
                    grid.add(datType,1,1);


                    dialog.getDialogPane().setContent(grid);

                    Platform.runLater(() -> name.requestFocus());

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            return new Pair<>(name.getText(), (UMLAttribute.DataType) datType.getSelectionModel().getSelectedItem());
                        }
                        return null;
                    });

                    Optional<Pair<String, UMLAttribute.DataType>> result = dialog.showAndWait();
                    result.ifPresent(nameType -> {
//                         System.out.println("Name=" + nameType.getKey() + ", Return type=" + nameType.getValue());
                        data.addMethod(new UMLMethod(nameType.getKey(), nameType.getValue(), new ArrayList()));
                    });
                }
            });

            Button btDelMeth = (Button) vbox.lookup("#btDelMeth");
            btDelMeth.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Dialog<String> dialog = new Dialog();
                    dialog.setTitle("Delete Method");
                    dialog.setHeaderText(null);

                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20,30,10,30));

                    TextField name = new TextField();
                    name.setPromptText("name");

                    grid.add(new Label("Name:"), 0, 0);
                    grid.add(name,1,0);

                    dialog.getDialogPane().setContent(grid);

                    Platform.runLater(() -> name.requestFocus());

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            return name.getText();
                        }
                        return null;
                    });

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(nameDel -> {
                        System.out.println("Name=" + nameDel);
                        data.removeMethod(nameDel);
                    });
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
