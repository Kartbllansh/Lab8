package com.example.lab8.Apps;

import com.example.lab8.DataBase.MainDataBase;
import com.example.lab8.DataBase.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AutLogController {

    @FXML
    public Button enter;
    public TextField passwd;
    public TextField name;
    @FXML
    public Button info;
    public Button Esp;
    public Button sven;
    public Button russia;
    public Button sloven;
    public MenuButton lang;
    public Text passw;
    public Text username;
    public Button loginn;
    @FXML
    private Button regist;
    @FXML
    private Label textLabel;

    @FXML
    private void initialize(){

        textLabel = new Label();
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

        Edition.showAlert("Ошибка", "Нет аккаунта с таким логином", "Ошибка при авторизации");
        return;

    }
   Users.enter(name.getText(), passwd.getText());
    if(Users.aut) {
        openWindowTable();
    }
});
info.setOnAction(event -> getInfo());
    }
    @FXML
    private void showText() {
        // Установка текста в метку
        textLabel.setText("Это вылезающий текст!");
    }

    public void openWindowTable(){
        try {
            System.out.println("check");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/tableOrig.fxml"));
            Scene signUpScene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage currentStage = (Stage) regist.getScene().getWindow();
            currentStage.setScene(signUpScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getInfo(){
        Stage stage = new Stage();
        try {
            Edition.createForm(stage, "/fxml/Info.fxml", "Info", 500, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}