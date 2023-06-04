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
         tableController.update();
           } catch  (IndexOutOfBoundsException ex) {
               Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с аргуметом");
               //System.err.println("Не указаны аргументы команды.");
           } catch (NumberFormatException ex) {
               Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с аргуметом");
           }
       });
       remove_type.setOnAction(event -> {
           try{
           collectionManager.removeByType(fieldType.getText());
               tableController.update();
           } catch ( IndexOutOfBoundsException ex) { //
               System.err.println("Не указаны аргументы команды.");
               Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды.", "Ошибка с аргуметом");

           } catch (NumberFormatException ex) {
               System.err.println("Формат аргумента не соответствует целочисленному");
               Edition.showAlert("Ошибка с аргуметом", "Формат аргумента не соответствует", "Ошибка с аргуметом");
           } catch (IllegalArgumentException e){
               System.err.println("Не существует такого типа");
               Edition.showAlert("Ошибка с аргуметом", "Не существует такого типа", "Ошибка с аргуметом");
           }
       });
       remove_great.setOnAction(event -> {
           try{
        collectionManager.gread(Double.parseDouble(fieldAge.getText()));
               tableController.update();
           } catch  ( IndexOutOfBoundsException ex) { //IndexOutOfBoundsException
               System.err.println("Не указаны аргументы команды.");
               Edition.showAlert("Ошибка с аргуметом", "Формат аргумента не соответствует", "Ошибка с аргуметом");
           } catch (NumberFormatException ex) {
               System.err.println("Формат аргумента не соответствует целочисленному");
               Edition.showAlert("Ошибка с аргуметом", "Формат аргумента не соответствует", "Ошибка с аргуметом");
           }
       });
    }
}
