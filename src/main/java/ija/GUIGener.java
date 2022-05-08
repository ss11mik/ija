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
import ija.Draggable;

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

import javafx.beans.property.*;

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
     * Zajistuje prekresleni GUI pri zmene tridy
     * @param ctx controller pro GUI
     * @param data data UML tridy
     * @return Vraci upraveny vertical box pro GUI
     */
    public static VBox createClass (UMLController ctx, UMLClass data) {
        try {
            final VBox vbox = FXMLLoader.load(ctx.getClass().getResource("class-box.fxml"));
            Draggable.Nature d = new Draggable.Nature(vbox);

            vbox.setTranslateX(data.getXLocation());
            data.getXLocationProperty().bind(vbox.translateXProperty());
            vbox.setTranslateY(data.getYLocation());
            data.getYLocationProperty().bind(vbox.translateYProperty());


            Label name = (Label) vbox.lookup("#name");
            data.getNameProperty().addListener((observable, oldValue, newValue) -> {
                name.setText((data.isClass()? "" : "<<interface>>\n") + newValue);
            });
            name.setText((data.isClass()? "" : "<<interface>>\n") + data.getName());


            Button btnEditName = (Button) vbox.lookup("#btn_editName");
            btnEditName.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    TextInputDialog dialog = new TextInputDialog(data.getName());
                    dialog.setTitle("Class");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Please new enter class name:");

                    Optional<String> result = dialog.showAndWait();
                    if(result.isPresent()) {

                        if(result.get().length() == 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Class name cannot be empty!");
                            alert.setContentText("Class name cannot be empty!");
                            alert.showAndWait().ifPresent(rs -> {});
                            return;
                        }
                        data.setName(result.get());
                    }
            }});

            VBox attrs = (VBox) vbox.lookup("#attrs");
            Label label_attrs = (Label) attrs.lookup("#text_attributes");

            for (UMLAttribute att : data.getAttributesProperty()) {
                label_attrs.setText(label_attrs.getText() + att.toString() + "\n");
            }

            data.getAttributesProperty().addListener((observable, oldValue, newValue) -> {
                /**
                * Pripise dalsi atribut mezi atributy tridy
                */
                label_attrs.setText("");
                for (int i =0; i < newValue.size(); i++){
                    label_attrs.setText(label_attrs.getText() + newValue.get(i).toString() + "\n");
                }
            });

            Button btnAddAttr = (Button) vbox.lookup("#btn_addAttribute");
            btnAddAttr.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
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
                    datType.getSelectionModel().select(0);
                    ComboBox accMod = new ComboBox();
                    accMod.getItems().setAll(UMLAttribute.AccesModifier.values());
                    accMod.getSelectionModel().select(0);

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
                    Dialog<UMLAttribute> dialog = new Dialog();
                    dialog.setTitle("Delete Attribute");
                    dialog.setHeaderText(null);

                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20,30,10,30));


                    if(data.getAttributes().size() == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("No attributes to remove!");
                        alert.setContentText("No attributes to remove!");
                        alert.showAndWait().ifPresent(rs -> {});
                        return;
                    }

                    ComboBox item = new ComboBox();
                    item.getItems().setAll(data.getAttributes());
                    item.getSelectionModel().select(0);

                    grid.add(new Label("Name:"), 0, 0);
                    grid.add(item,1,0);

                    dialog.getDialogPane().setContent(grid);

                    Platform.runLater(() -> item.requestFocus());

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            return (UMLAttribute) item.getSelectionModel().getSelectedItem();
                        }
                        return null;
                    });

                    Optional<UMLAttribute> result = dialog.showAndWait();
                    result.ifPresent(nameDel -> {

                        //TODO check for usage

                        data.removeAttribute(nameDel);
                    });
                }
            });



            VBox methods = (VBox) vbox.lookup("#methods");
            Label label_methods = (Label) methods.lookup("#text_methods");

            for (UMLMethod meth : data.getMethodsProperty()) {
                label_methods.setText(label_methods.getText() + meth.toString() + "\n");
            }

            data.getMethodsProperty().addListener((observable, oldValue, newValue) -> {
                /**
                * Pripise dalsi metodu mezi metody tridy
                */
                label_methods.setText("");
                for (int i = 0; i < newValue.size(); i++){
                    label_methods.setText(label_methods.getText() + newValue.get(i).toString() + "\n");
                }
            });

            Button btnAddMethod = (Button) vbox.lookup("#btn_addmethod");
            btnAddMethod.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
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
                    datType.getSelectionModel().select(0);

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
                        data.addMethod(new UMLMethod(nameType.getKey(), nameType.getValue()));
                    });
                }
            });

            Button btDelMeth = (Button) vbox.lookup("#btDelMeth");
            btDelMeth.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Dialog<UMLMethod> dialog = new Dialog();
                    dialog.setTitle("Delete Method");
                    dialog.setHeaderText(null);

                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20,30,10,30));

                    if(data.getMethods().size() == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("No methods to remove!");
                        alert.setContentText("No methods to remove!");
                        alert.showAndWait().ifPresent(rs -> {});
                        return;
                    }

                    ComboBox item = new ComboBox();
                    item.getItems().setAll(data.getMethods());
                    item.getSelectionModel().select(0);


                    grid.add(new Label("Name:"), 0, 0);
                    grid.add(item,1,0);

                    dialog.getDialogPane().setContent(grid);

                    Platform.runLater(() -> item.requestFocus());

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            return (UMLMethod) item.getSelectionModel().getSelectedItem();
                        }
                        return null;
                    });

                    Optional<UMLMethod> result = dialog.showAndWait();
                    result.ifPresent(nameDel -> {
                        data.removeMethod(nameDel);
                    });
                }
            });

            return vbox;
        }
        catch (IOException e) {
            System.out.println(e);
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
            name.setText(data.getName() + " : " + data.getInstance().getName());

            return vbox;
        }
        catch (IOException e) {
            return null;
        }
    }

}
