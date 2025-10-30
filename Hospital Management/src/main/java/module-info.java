module com.example.hospitalmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hospitalmanagement to javafx.fxml;
    exports com.example.hospitalmanagement;
}