module com.example.hospitalmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.hospitalmanagement to javafx.fxml;
    exports com.example.hospitalmanagement;
}