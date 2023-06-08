package com.example.lab8.Apps;

import com.example.lab8.Languagesss.CurrentLanguage;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    public Text add;
    public Text addIf;
    public Text printAge;
    public Text exit;
    public Text logout;
    public Text help;
    public Text update;
    public Text info;
    public Text printType;
    public Text clear;
    public Text removeType;
    public Text removeGreat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       appLang();
    }
    public void appLang(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        add.setText(currentLanguage.getString("addh"));
        addIf.setText(currentLanguage.getString("add_if_minh"));
        removeGreat.setText(currentLanguage.getString("remove_greaterh"));
        removeType.setText(currentLanguage.getString("remove_by_typeh"));
        update.setText(currentLanguage.getString("update_idh"));
        info.setText(currentLanguage.getString("infoh"));
        logout.setText(currentLanguage.getString("logouth"));
        exit.setText(currentLanguage.getString("exith"));
        help.setText(currentLanguage.getString("helph"));
        clear.setText(currentLanguage.getString("clearh"));
        printAge.setText(currentLanguage.getString("print_ageh"));
        printType.setText(currentLanguage.getString("print_typeh"));
    }
}
