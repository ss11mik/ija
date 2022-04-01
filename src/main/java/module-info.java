module ija.ija_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.databind;
    opens ija to com.fasterxml.jackson.databind;
    opens ija.data_structures to com.fasterxml.jackson.databind;

    opens ija.ija_project to javafx.fxml;
    exports ija.ija_project;
}
