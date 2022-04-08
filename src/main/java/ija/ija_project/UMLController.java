package ija.ija_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TabPane;

public class UMLController {
    @FXML
    private TabPane tabs;

    @FXML
    private void redrawCanvas() {
//         double w=canvas.getWidth();
//         double h=canvas.getHeight();
//         System.out.println(w);
//         GraphicsContext gc=canvas.getGraphicsContext2D();
//         gc.clearRect(0, 0, w, h);
//         gc.beginPath();
//         gc.rect(10, 10, w-200, h-20);
//         gc.stroke();
    }


    @FXML
    private void menuOpen () {
    }

    @FXML
    private void menuSave () {
    }

    @FXML
    private void menuClose () {
        //TODO ask if save
        System.exit(0);
    }
}
