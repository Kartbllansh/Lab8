package com.example.lab8.Apps;

import com.example.lab8.DataBase.MainDataBase;
import com.example.lab8.DataBase.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AutRegController {
    @FXML
    public Button enter;
    @FXML
    public TextField dPasswd;
    @FXML
    public TextField psswd ;
    @FXML
    public TextField name;
    @FXML
    private Button login;
@FXML
    private void initialize(){
    login.setOnAction(event -> {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
            Scene signUpScene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage currentStage = (Stage) login.getScene().getWindow();
            currentStage.setScene(signUpScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    enter.setOnAction(event -> {
        boolean why = MainDataBase.checkLogin(name.getText());
        if(why) {
            Edition.showAlert("Ошибка", "Аккаунт с логином ( "+ name.getText()+" ) уже есть", "Ошибка при авторизации" );
            //System.out.println("Аккаунт с логином ( "+ name.getText()+" ) уже есть" );
            return;
        }

            Users.registration(name.getText(), psswd.getText(), dPasswd.getText());
        if(Users.reg) {
            Edition.showAlert("Выполнено", "Успешная регистрация аккаунта" + name.getText(), "Перейдите в LogIn и войдите в аккаунт");
            System.out.println("Успешная регистрация");
        }
    });
}
}
