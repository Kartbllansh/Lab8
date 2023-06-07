package com.example.lab8.Apps;

import com.example.lab8.File.CollectionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RemoveController {
    CollectionManager collectionManager = new CollectionManager();
    TableController tableController = new TableController();
    public Button remove_id;
    public Button remove_great;
    public Button remove_type;
    public TextField fieldId;
    public TextField fieldType;
    public TextField fieldAge;
    @FXML
    private void initialize(){
       remove_id.setOnAction(event -> {
           try{
         collectionManager.removeById(Long.parseLong(fieldId.getText()));
         collectionManager.save();
         tableController.update();
           } catch  (IndexOutOfBoundsException ex) {
               Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "");
               //System.err.println("Не указаны аргументы команды.");
           } catch (NumberFormatException ex) {
               Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "");
           }
       });
       remove_type.setOnAction(event -> {
           try{
           collectionManager.removeByType(fieldType.getText());
           collectionManager.save();
               tableController.update();
           } catch ( IndexOutOfBoundsException ex) { //
               System.err.println("Не указаны аргументы команды.");
               Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды.", "");

           } catch (NumberFormatException ex) {
               System.err.println("Формат аргумента не соответствует целочисленному");
               Edition.showAlert("Ошибка с аргуметом", "Формат аргумента не соответствует", "");
           } catch (IllegalArgumentException e){
               System.err.println("Не существует такого типа");
               Edition.showAlert("Ошибка с аргуметом", "Не существует такого типа", "");
           }
       });
       remove_great.setOnAction(event -> {
           try{
        collectionManager.gread(Double.parseDouble(fieldAge.getText()));
        collectionManager.save();
               tableController.update();
           } catch  ( IndexOutOfBoundsException ex) { //IndexOutOfBoundsException
               System.err.println("Не указаны аргументы команды.");
               Edition.showAlert("Ошибка с аргуметом", "Формат аргумента не соответствует", "");
           } catch (NumberFormatException ex) {
               System.err.println("Формат аргумента не соответствует целочисленному");
               Edition.showAlert("Ошибка с аргуметом", "Формат аргумента не соответствует", "");
           }
       });
    }
}
