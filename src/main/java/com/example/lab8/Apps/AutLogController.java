package com.example.lab8.Apps;

import com.example.lab8.DataBase.MainDataBase;
import com.example.lab8.DataBase.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AutLogController {

    @FXML
    public Button enter;
    public TextField passwd;
    public TextField name;
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
enter.setOnAction(event -> {
    boolean why = MainDataBase.checkLogin(name.getText());
    if(!why) {
        System.out.println("Нет аккаунта с логином - " + name.getText());
        return;
    }
   Users.enter(name.getText(), passwd.getText());
});
    }


}