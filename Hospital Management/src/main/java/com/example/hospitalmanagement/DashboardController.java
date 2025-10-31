package com.example.hospitalmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
    @FXML
    private Button add_ambulance_btn;
    @FXML
    private TableView<Medicine> medicineTable;
    @FXML
    private TableColumn<Medicine, Integer> colId;
    @FXML
    private TableColumn<Medicine, String> colName;
    @FXML
    private TableColumn<Medicine, Double> colPrice;
    @FXML
    private TableView<Doctor> doctorTable;
    @FXML
    private TableColumn<Doctor, Integer> colDId;
    @FXML
    private TableColumn<Doctor, String> colDName;
    @FXML
    private TableColumn<Doctor, String> colDSpecialization;
    @FXML
    private TableColumn<Doctor, String> colDDOJ;
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, Integer> colPId;
    @FXML
    private TableColumn<Patient, String> colPName;
    @FXML
    private TableColumn<Patient, String> colPAge;
    @FXML
    private TableColumn<Patient, String> colPGender;
    @FXML
    private TableColumn<Patient, String> colPDisease;
    @FXML
    private TableColumn<Patient, Integer> colPFees;
    @FXML
    private TableColumn<Patient, Integer> colPRno;
    @FXML
    private TableColumn<Patient, String> colPAdmitDate;
    @FXML
    private TableView<Room> roomTable;
    @FXML
    private TableColumn<Room, Integer> colRNo;
    @FXML
    private TableColumn<Room, String> colRType;
    @FXML
    private TableColumn<Room, String> colRStatus;
    @FXML
    private TableView<Ambulance> ambulanceTable;
    @FXML
    private TableColumn<Ambulance, Integer> colDriId;
    @FXML
    private TableColumn<Ambulance, String> colDDriName;
    @FXML
    private TableColumn<Ambulance, String> VNo;
    private ObservableList<Medicine> medicineList = FXCollections.observableArrayList();
    private ObservableList<Doctor> doctorList = FXCollections.observableArrayList();
    private ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private ObservableList<Room> roomList = FXCollections.observableArrayList();
    private ObservableList<Ambulance> ambulanceList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane dashboard_page;
    @FXML
    private AnchorPane medicine_page;
    @FXML
    private AnchorPane doctor_page;
    @FXML
    private AnchorPane patients_page;
    @FXML
    private AnchorPane rooms_page;
    @FXML
    private AnchorPane ambulance_page;
    @FXML
    private AnchorPane add_doctor_page;
    @FXML
    private AnchorPane add_ambulance_page;
    @FXML
    private AnchorPane admit_patient_page;
    @FXML
    private Button medicine_btn;
    @FXML
    private Button admit_patient_btn;
    @FXML
    private Button discharge_patient_btn;
    @FXML
    private Button patient_info_btn;
    @FXML
    private Button update_patient_details_btn;
    @FXML
    private Button billing_btn;
    @FXML
    private Button rooms_btn;
    @FXML
    private Button doctors_btn;
    @FXML
    private Button ambulances_btn;
    @FXML
    private TextField doctorNameTxtField;
    @FXML
    private TextField doctorSpecializationTxtField;
    @FXML
    private TextField ambulanceNameTxtField;
    @FXML
    private TextField ambulanceVnoTxtField;
    @FXML
    private TextField patientNameTxtField;
    @FXML
    private TextField ageTxtField;
    @FXML
    private RadioButton mGenderRBtn;
    @FXML
    private RadioButton fGenderRBtn;
    @FXML
    private TextField diseaseTxtField;
    @FXML
    private TextField feesTxtField;
    @FXML
    private ChoiceBox<Integer> rNoChoiceBox;
    private void hideAllPages() {
        medicine_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        admit_patient_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        discharge_patient_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        patient_info_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        update_patient_details_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        billing_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        rooms_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        doctors_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        ambulances_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        add_doctor_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");
        add_ambulance_btn.setStyle("-fx-background-color: rgba(255, 187, 225,0.5)");

        dashboard_page.setVisible(false);
        medicine_page.setVisible(false);
        doctor_page.setVisible(false);
        patients_page.setVisible(false);
        rooms_page.setVisible(false);
        ambulance_page.setVisible(false);
        add_doctor_page.setVisible(false);
        add_ambulance_page.setVisible(false);
        admit_patient_page.setVisible(false);
    }

    public void initialize() {
        hospital_Name.setText(hospitalName);
        if (user_type == 1) {
            user_type_txt.setText("Admin");
            add_doctor_btn.setVisible(true);
            add_ambulance_btn.setVisible(true);
        } else {
            user_type_txt.setText("Receptionist");
            add_doctor_btn.setVisible(false);
            add_ambulance_btn.setVisible(false);
        }
        hideAllPages();
        dashboard_page.setVisible(true);
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void dashboard_btn() {
        hideAllPages();
        dashboard_page.setVisible(true);
    }

    public void medicines_btn() {
        hideAllPages();
        medicine_page.setVisible(true);
        medicine_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
        colId.setCellValueFactory(new PropertyValueFactory<>("medId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        loadMedicines();
    }

    public void doctors_btn() {
        hideAllPages();
        doctor_page.setVisible(true);
        doctors_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
        colDId.setCellValueFactory(new PropertyValueFactory<>("dId"));
        colDName.setCellValueFactory(new PropertyValueFactory<>("dName"));
        colDSpecialization.setCellValueFactory(new PropertyValueFactory<>("dSpecialization"));
        colDDOJ.setCellValueFactory(new PropertyValueFactory<>("dDoj"));
        loadDoctors();
    }

    public void patients_info_btn() {
        hideAllPages();
        patients_page.setVisible(true);
        patient_info_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
        colPId.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        colPAge.setCellValueFactory(new PropertyValueFactory<>("pAge"));
        colPGender.setCellValueFactory(new PropertyValueFactory<>("pGender"));
        colPDisease.setCellValueFactory(new PropertyValueFactory<>("pDisease"));
        colPFees.setCellValueFactory(new PropertyValueFactory<>("pFees"));
        colPRno.setCellValueFactory(new PropertyValueFactory<>("pRno"));
        colPAdmitDate.setCellValueFactory(new PropertyValueFactory<>("pAdmitDate"));
        loadPatients();
    }
    public void rooms_btn_onClick() {
        hideAllPages();
        rooms_page.setVisible(true);
        rooms_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
        colRNo.setCellValueFactory(new PropertyValueFactory<>("rNo"));
        colRType.setCellValueFactory(new PropertyValueFactory<>("rType"));
        colRStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadRooms();
    }
    public void ambulances_btn(){
        hideAllPages();
        ambulance_page.setVisible(true);
        ambulances_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
        colDriId.setCellValueFactory(new PropertyValueFactory<>("driId"));
        colDDriName.setCellValueFactory(new PropertyValueFactory<>("driName"));
        VNo.setCellValueFactory(new PropertyValueFactory<>("vNo"));
        loadAmbulances();
    }
    public void go_to_add_doctor_page() {
        hideAllPages();
        add_doctor_page.setVisible(true);
        add_doctor_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
    }
    public void go_to_patient_page(){
        hideAllPages();
        admit_patient_page.setVisible(true);
        rNoChoiceBox.getItems().clear();
        admit_patient_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT r_no FROM rooms WHERE r_available = 1;");
            while (rs.next()) {
                rNoChoiceBox.getItems().add(rs.getInt("r_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add_doctor_btn_onClick(ActionEvent event) {
        if(doctorNameTxtField.getText().isBlank() || doctorSpecializationTxtField.getText().isBlank()){
            showAlert("Error", "Please fill in all fields.");
        } else {
            DBConnection db = new DBConnection();
            String name = doctorNameTxtField.getText();
            String specialization = doctorSpecializationTxtField.getText();
            String query = "insert into doctors(d_name, d_spelization) values (?, ?);";

            try(Connection conn = db.getConnection()){
                java.sql.PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, specialization);
                int rowsAffected = stmt.executeUpdate();
                if(rowsAffected > 0){
                    showAlert("Success", "Doctor added successfully.");
                    doctorNameTxtField.clear();
                    doctorSpecializationTxtField.clear();
                } else {
                    showAlert("Error", "Failed to add doctor.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to add doctor: " + e.getMessage());
            }
        }
    }
    public void go_to_add_ambulance_page(){
        hideAllPages();
        add_ambulance_page.setVisible(true);
        add_ambulance_btn.setStyle("-fx-background-color: rgba(255, 187, 225, 1)");
    }
    public void add_ambulance_btn_onClick(ActionEvent event){
        if(ambulanceNameTxtField.getText().isBlank() || ambulanceVnoTxtField.getText().isBlank()){
            showAlert("Error", "Please fill in all fields.");
        } else {
            DBConnection db = new DBConnection();
            String name = ambulanceNameTxtField.getText();
            String vno = ambulanceVnoTxtField.getText();
            String query = "insert into ambulances(dri_name, v_no) values (?, ?);";

            try(Connection conn = db.getConnection()){
                java.sql.PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, vno);
                int rowsAffected = stmt.executeUpdate();
                if(rowsAffected > 0){
                    showAlert("Success", "Ambulance added successfully.");
                    ambulanceNameTxtField.clear();
                    ambulanceVnoTxtField.clear();
                } else {
                    showAlert("Error", "Failed to add ambulance.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to add ambulance: " + e.getMessage());
            }
        }
    }
    private void loadMedicines() {
        DBConnection db = new DBConnection();
        medicineList.clear();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM medicines;");

            while (rs.next()) {
                medicineList.add(new Medicine(
                        rs.getInt("med_id"),
                        rs.getString("med_name"),
                        rs.getDouble("price")
                ));
            }
            medicineTable.setItems(medicineList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDoctors() {
        DBConnection db = new DBConnection();
        doctorList.clear();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctors;");

            while (rs.next()) {
                doctorList.add(new Doctor(
                        rs.getInt("d_id"),
                        rs.getString("d_name"),
                        rs.getString("d_spelization"),
                        rs.getString("d_doj")
                ));
            }
            doctorTable.setItems(doctorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPatients() {
        DBConnection db = new DBConnection();
        patientList.clear();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients;");

            while (rs.next()) {
                patientList.add(new Patient(
                        rs.getInt("p_id"),
                        rs.getString("p_name"),
                        rs.getInt("p_age"),
                        rs.getString("p_gender"),
                        rs.getString("p_disease"),
                        rs.getInt("p_fees"),
                        rs.getInt("p_rno"),
                        rs.getString("p_admitted_date")
                ));
            }
            patientTable.setItems(patientList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadRooms() {
        DBConnection db = new DBConnection();
        roomList.clear();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooms;");

            while (rs.next()) {
                roomList.add(new Room(
                        rs.getInt("r_no"),
                        rs.getString("r_type"),
                        rs.getInt("r_available")
                ));
            }
            roomTable.setItems(roomList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadAmbulances() {
        DBConnection db = new DBConnection();
        ambulanceList.clear();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ambulances;");

            while (rs.next()) {
                ambulanceList.add(new Ambulance(
                        rs.getInt("dri_id"),
                        rs.getString("dri_name"),
                        rs.getString("v_no")
                ));
            }
            ambulanceTable.setItems(ambulanceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void admit_Patient_onClick(ActionEvent event){
        if(patientNameTxtField.getText().isBlank() || ageTxtField.getText().isBlank() || diseaseTxtField.getText().isBlank() || feesTxtField.getText().isBlank() || rNoChoiceBox.getValue() == null || (!mGenderRBtn.isSelected() && !fGenderRBtn.isSelected())){
            showAlert("Error", "Please fill in all fields.");
        } else {
            DBConnection db = new DBConnection();
            String name = patientNameTxtField.getText();
            int age = Integer.parseInt(ageTxtField.getText());
            String gender = mGenderRBtn.isSelected() ? "Male" : "Female";
            String disease = diseaseTxtField.getText();
            int fees = Integer.parseInt(feesTxtField.getText());
            int rno = rNoChoiceBox.getValue();
            String query = "insert into patients(p_name, p_age, p_gender, p_disease, p_fees, p_rno) values (?, ?, ?, ?, ?, ?);";
            try (Connection conn = db.getConnection()) {
                java.sql.PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setString(3, gender);
                stmt.setString(4, disease);
                stmt.setInt(5, fees);
                stmt.setInt(6, rno);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert("Success", "Patient admitted successfully.");
                    patientNameTxtField.clear();
                    ageTxtField.clear();
                    diseaseTxtField.clear();
                    feesTxtField.clear();
                    rNoChoiceBox.setValue(null);
                    mGenderRBtn.setSelected(true);
                    fGenderRBtn.setSelected(false);
                } else {
                    showAlert("Error", "Failed to admit patient.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to admit patient: " + e.getMessage());
            }
        }
    }
}
