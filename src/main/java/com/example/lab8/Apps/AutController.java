package com.example.lab8.Apps;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AutController {

    @FXML
    private Button regist;
    @FXML
    private void initialize(){
      regist.setOnAction(this::handleButtonClick);
    }
    @FXML
    private void handleButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/SIGNUP.fxml"));
            Scene signUp = new Scene(fxmlLoader.load(), 1280, 720);
             Stage stage = (Stage) regist.getScene().getWindow();
            stage.setScene(signUp);




        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}