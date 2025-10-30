package com.example.hospitalmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApp extends Application {
    private String hospitalName = "Java Hospital";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle(hospitalName);
        stage.setScene(scene);
        stage.show();
    }
}
