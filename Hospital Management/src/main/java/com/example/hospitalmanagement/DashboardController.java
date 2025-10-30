package com.example.hospitalmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardController {
    public int user_type;
    private String hospitalName = "Java Hospital";

    @FXML
    private Label hospital_Name;
    @FXML
    private Label hospital_Name1;
    @FXML
    private Label user_type_txt;
    @FXML
    private Button add_doctor_btn;

    public void initialize() {
        hospital_Name.setText(hospitalName);
        if (user_type == 1) {
            user_type_txt.setText("Admin");
            add_doctor_btn.setVisible(true);
        }
        else {
            user_type_txt.setText("Receptionist");
            add_doctor_btn.setVisible(false);
        }
    }
    public void logout(ActionEvent event) {
        try {
            user_type = 0;

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 900, 600);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle(hospitalName);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to logout: " + e.getMessage());
        }
    }
    private void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
