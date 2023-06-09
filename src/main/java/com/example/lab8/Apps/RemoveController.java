package com.example.lab8.Apps;

import com.example.lab8.File.CollectionManager;
import com.example.lab8.Languagesss.CurrentLanguage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveController implements Initializable {
    public Text idF;
    public Text typeF;
    public Text sizeF;
    CollectionManager collectionManager = new CollectionManager();
    TableController tableController = new TableController();
    public Button remove_id;
    public Button remove_great;
    public Button remove_type;
    public TextField fieldId;
    public TextField fieldType;
    public TextField fieldAge;
    @Override
    public void initialize(URL location, ResourceBundle resources){
       remove_id.setOnAction(event -> {
           try{
         collectionManager.removeById(Long.parseLong(fieldId.getText()));
         collectionManager.save();
         tableController.update();
           } catch  (IndexOutOfBoundsException ex) {
               Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "");
               //System.err.println("Не указаны аргументы команды.");
           } catch (NumberFormatException ex) {
               Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "");
           }
       });
       remove_type.setOnAction(event -> {
           try{
           collectionManager.removeByType(fieldType.getText());
           collectionManager.save();
               tableController.update();
           } catch ( IndexOutOfBoundsException ex) { //
               System.err.println("Не указаны аргументы команды.");
               Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "");

           } catch (NumberFormatException ex) {
               System.err.println("Формат аргумента не соответствует целочисленному");
               Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argForm"), "");
           } catch (IllegalArgumentException e){
               System.err.println("Не существует такого типа");
               Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("notExistType"), "");
           }
       });
       remove_great.setOnAction(event -> {
           try{
        collectionManager.gread(Double.parseDouble(fieldAge.getText()));
        collectionManager.save();
               tableController.update();
           } catch  ( IndexOutOfBoundsException ex) { //IndexOutOfBoundsException
               System.err.println("Не указаны аргументы команды.");
               Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argForm"), "");
           } catch (NumberFormatException ex) {
               System.err.println("Формат аргумента не соответствует целочисленному");
               Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argForm"), "");
           }
       });
       appLang();
    }
    public void appLang(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        remove_id.setText(currentLanguage.getString("removeIdB"));
        remove_great.setText(currentLanguage.getString("remomeGreatB"));
        remove_type.setText(currentLanguage.getString("RemoveTypeB"));
        idF.setText(currentLanguage.getString("fId"));
        typeF.setText(currentLanguage.getString("fType"));
        sizeF.setText(currentLanguage.getString("fSize"));
    }
}
