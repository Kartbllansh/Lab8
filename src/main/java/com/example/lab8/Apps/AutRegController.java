package com.example.lab8.Apps;

import com.example.lab8.DataBase.MainDataBase;
import com.example.lab8.DataBase.Users;
import com.example.lab8.Languagesss.CurrentLanguage;
import com.example.lab8.Languagesss.Language;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AutRegController implements Initializable {
    @FXML
    public Button enter;
    @FXML
    public TextField dPasswd;
    @FXML
    public TextField psswd ;
    @FXML
    public TextField name;
    public Button Esp;
    public Button sven;
    public Button russia;
    public Button sloven;
    public MenuButton lang;
    public Text passwd;
    public Text username;
    public Button now;
    public Text ps;
    @FXML
    private Button login;
@Override
    public void initialize(URL location, ResourceBundle resources){
    setLang();
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
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), CurrentLanguage.getCurrentLanguage().getString("yetExist")+ name.getText(), CurrentLanguage.getCurrentLanguage().getString("LoginErr") );
            //System.out.println("Аккаунт с логином ( "+ name.getText()+" ) уже есть" );
            return;
        }

            Users.registration(name.getText(), psswd.getText(), dPasswd.getText());
        if(Users.reg) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("succ"), CurrentLanguage.getCurrentLanguage().getString("succReg") + name.getText(), "");
            System.out.println("Успешная регистрация");
        }
    });
    Esp.setOnAction(event -> {
        CurrentLanguage.setCurrentLanguage(Language.esp);
        CurrentLanguage.setCurrentLanguageString("esp");
        setLang();
    });
    russia.setOnAction(event -> {
        CurrentLanguage.setCurrentLanguage(Language.ru);
        CurrentLanguage.setCurrentLanguageString("ru");
        setLang();
    });
    sloven.setOnAction(event -> {
        CurrentLanguage.setCurrentLanguage(Language.sl);
        CurrentLanguage.setCurrentLanguageString("sl");
        setLang();
    });
    sven.setOnAction(event -> {
        CurrentLanguage.setCurrentLanguage(Language.swe);
        CurrentLanguage.setCurrentLanguageString("swe");
        setLang();
    });
}
    public void setLang(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        enter.setText(currentLanguage.getString("regist"));
        //dPasswd.setText(currentLanguage.getString("Password"));
        passwd.setText(currentLanguage.getString("Password"));
        ps.setText(currentLanguage.getString("Password"));
        lang.setText(currentLanguage.getString("Languages"));
        username.setText(currentLanguage.getString("Username"));
        //psswd.setText(currentLanguage.getString("Password"));
        //name.setText(currentLanguage.getString("Username"));
    }
}
