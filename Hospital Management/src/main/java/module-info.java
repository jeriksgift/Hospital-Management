module com.example.hospitalmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;
    requires transitive itextpdf;
    requires com.google.protobuf;


    opens com.example.hospitalmanagement to javafx.fxml;
    exports com.example.hospitalmanagement;
}