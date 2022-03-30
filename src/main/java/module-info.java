module ija.ija_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens ija.ija_project to javafx.fxml;
    exports ija.ija_project;
}