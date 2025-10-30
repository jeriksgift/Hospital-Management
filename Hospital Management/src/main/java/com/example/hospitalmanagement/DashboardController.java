package com.example.hospitalmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DashboardController {
    public int user_type;
    private String hospitalName = "Java Hospital";

    @FXML
    private Label hospital_Name;
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
}
