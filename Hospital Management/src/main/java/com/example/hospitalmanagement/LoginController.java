package com.example.hospitalmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public final String hospitalName = "Java Hospital";
    @FXML
    private TextField username_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private TextField username_field_register;
    @FXML
    private PasswordField password_field_register;
    @FXML
    private PasswordField confirm_password_field_register;
    @FXML
    private Label alert_txt;
    @FXML
    private Label alert_txt_register;
    @FXML
    private AnchorPane login_pane;
    @FXML
    private AnchorPane register_pane;
    @FXML
    private Button v_login_btn;
    @FXML
    private Button v_register_btn;
    @FXML
    private Button v_exit_btn;


    public void login_btn(ActionEvent event){
        if(username_field.getText().isBlank() || password_field.getText().isBlank()){
            alert_txt.setText("Please fill in all fields.");
        } else {
            alert_txt.setText("");
            hadleLogin(event);
        }
    }
    public void register_btn(){
        if(username_field_register.getText().isBlank() || password_field_register.getText().isBlank() || confirm_password_field_register.getText().isBlank()){
            alert_txt_register.setText("Please fill in all fields.");
        } else if(!password_field_register.getText().equals(confirm_password_field_register.getText())){
            alert_txt_register.setText("Passwords do not match.");
        } else {
            alert_txt_register.setText("");
            hadleRegister();
        }
    }
    public void exit_btn(){
        System.exit(0);
    }
    public void go_to_register(){
        clear_all_fields();
        login_pane.setVisible(false);
        register_pane.setVisible(true);
    }
    public void go_to_login(){
        clear_all_fields();
        login_pane.setVisible(true);
        register_pane.setVisible(false);
    }
    private void clear_all_fields(){
        username_field.clear();
        username_field_register.clear();
        password_field.clear();
        password_field_register.clear();
        confirm_password_field_register.clear();
        alert_txt.setText("");
        alert_txt_register.setText("");
    }
    private void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void hadleLogin(ActionEvent event){
        DBConnection db = new DBConnection();
        String username = username_field.getText();
        String password = password_field.getText();
        String query = "SELECT user_type FROM users WHERE username = ? AND password = ?";
        try(Connection conn = db.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int userType = rs.getInt("user_type");
                go_to_dashboard(userType, event);
            }
            else{
                alert_txt.setText("Invalid username or password.");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            showAlert("Error", "Database connection error!");
        }
    }
    private void hadleRegister(){
        DBConnection db = new DBConnection();
        String username = username_field_register.getText();
        String password = password_field_register.getText();
        String confirmPassword = confirm_password_field_register.getText();
        String query = "insert into users(username, password) values(?, ?);";
        try(Connection conn = db.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                showAlert("Success", "User registered successfully!\nYou can now log in.");
                clear_all_fields();
                go_to_login();
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                showAlert("Error", "Username already exists!");
            } else {
                e.printStackTrace();
                showAlert("Error", "Database error: " + e.getMessage());
            }
        }
    }
    private void go_to_dashboard(int userType, ActionEvent event){
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = fxmlLoader.load();
            DashboardController dashboardController = fxmlLoader.getController();
            dashboardController.user_type = userType;
            dashboardController.initialize();
            Scene scene = new Scene(root, 1250, 900);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle(hospitalName);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load dashboard: " + e.getMessage());
        }
    }
}
