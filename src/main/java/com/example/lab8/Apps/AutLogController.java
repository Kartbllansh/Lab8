package com.example.lab8.Apps;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AutLogController {

    @FXML
    private Button regist;
    @FXML
    private void initialize(){
regist.setOnAction(event -> {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SIGNUP.fxml"));
        Scene signUpScene = new Scene(fxmlLoader.load(), 1280, 720);

        Stage currentStage = (Stage) regist.getScene().getWindow();
        currentStage.setScene(signUpScene);
    } catch (IOException e) {
        e.printStackTrace();
    }

});
    }


}