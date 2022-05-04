module ija.ija_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.guava;
    opens ija to com.fasterxml.jackson.databind;
    opens ija.dataStructures to com.fasterxml.jackson.databind;

    opens ija.ijaProject to javafx.fxml;
    exports ija.ijaProject;
}
