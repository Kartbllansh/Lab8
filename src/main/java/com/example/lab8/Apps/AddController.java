package com.example.lab8.Apps;

import com.example.lab8.Base.*;
import com.example.lab8.DataBase.Users;
import com.example.lab8.File.CollectionManager;
import com.example.lab8.File.VotTvoyId;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class AddController {
    private int des=0;
    public RadioButton update;
    public TextField idField;
    CollectionManager collectionManager = new CollectionManager();
    TableController tableController = new TableController();
    public Button start;
    boolean check = true;
    public RadioButton air;
    public RadioButton water;
    public RadioButton underground;
    public RadioButton white;
    public RadioButton yellow;
    public RadioButton orange;
    public TextField nameField;
    public TextField ageField;
    public TextField weightField;
    public TextField yField;
    public TextField eyesField;
    public TextField toothField;
    public TextField sizeField;
    public TextField xField;
    public RadioButton add;
    public RadioButton addMin;
    @FXML
    private void initialize(){
        ToggleGroup clr = new ToggleGroup();
        ToggleGroup tp = new ToggleGroup();
        ToggleGroup dcs = new ToggleGroup();
        yellow.setToggleGroup(clr);
        white.setToggleGroup(clr);
        orange.setToggleGroup(clr);
        water.setToggleGroup(tp);
        air.setToggleGroup(tp);
        underground.setToggleGroup(tp);
        add.setToggleGroup(dcs);
        addMin.setToggleGroup(dcs);


          Dragon change = new Dragon();

          water.setOnAction(event1 -> change.setType(DragonType.WATER));
          air.setOnAction(event1 -> change.setType(DragonType.AIR));
          underground.setOnAction(event1 -> change.setType(DragonType.UNDERGROUND));
          orange.setOnAction(event1 -> change.setColor(Color.ORANGE));
          yellow.setOnAction(event1 -> change.setColor(Color.YELLOW));
          white.setOnAction(event1 -> change.setColor(Color.WHITE));
          add.setOnAction(event1 -> {
              System.out.println(change);
            des=2;
          });
          addMin.setOnAction(event1 -> {
              des=1;

          });
          update.setOnAction(event1 -> {
              des=3;
          });
        start.setOnAction(event -> {
            allCheck();
            change.setCreationDate(ZonedDateTime.now());
            change.setCreator(Users.getCurrentUser());
            VotTvoyId.votDragon(change);
            change.setAge(Integer.parseInt(ageField.getText()));
            change.setName(nameField.getText());
            change.setWeight(Long.parseLong(weightField.getText()));
            DragonHead dragonHead = new DragonHead(Double.parseDouble(sizeField.getText()),Integer.parseInt(eyesField.getText()), Long.parseLong(toothField.getText()));
            change.setHead(dragonHead);
            Coordinates coordinates = new Coordinates(Float.parseFloat(xField.getText()), Float.parseFloat(yField.getText()));
            change.setCoordinates(coordinates);
            if(des==1){
                collectionManager.addIfMin(change);
                collectionManager.save();
                tableController.update();
                Edition.showAlert("Успешно", "Успешно", "Успешно");
            } else if (des==2) {

             CollectionManager.getDragons().add(change) ;
             collectionManager.save();
                tableController.update();
                Edition.showAlert("Успешно", "Успешно", "Успешно");
            } else if (des==3) {
                try{
                    collectionManager.updateId(Long.parseLong(idField.getText()));
                    collectionManager.save();
                    tableController.update();
                    Edition.showAlert("Успешно", "Успешно", "Успешно");
                } catch  (IndexOutOfBoundsException ex) {
                    Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с аргументом ID");
                    //System.err.println("Не указаны аргументы команды.");

                } catch (NumberFormatException ex) {
                    Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с аргументом ID");
                }

            } else {
                Edition.showAlert("Ошибка", "Выберите команду", "Не выбрана команда");
            }
        });



    }

    private void checkName(){
    String name = nameField.getText();
        if (!name.matches("[-a-zA-Zа-яА-ЯЁё]+")) {
            System.out.println("Неправильный формат");
            Edition.showAlert("Ошибка", "Неправильно записано имя", "Неправильный аргумент");
            check = false;
        }
    }
    private void checkAge(){
        try{
        String age = ageField.getText();
        int a = Integer.parseInt(age);
        if(a<=0){
            Edition.showAlert("Ошибка", "Неправильно записан возраст", "Неправильный аргумент");
            check=false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с аргуметом с возрастом");
            //System.err.println("Не указаны аргументы команды.");
            check = false;
        } catch (NumberFormatException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с аргуметом с возрастом");
            check = false;
        }

    }
    private void checkWeight(){
        try{
        String weight = weightField.getText();
        long a = Long.parseLong(weight);
        if(a<=0){
            Edition.showAlert("Ошибка", "Неправильно записан вес", "Неправильный аргумент");
            check = false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с аргуметом у weight");
            //System.err.println("Не указаны аргументы команды.");
            check=false;
        } catch (NumberFormatException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с аргуметом у weight");
            check = false;
        }

    }
    private void checkCoordinate(){
        try{
        String x = xField.getText();
        String y = yField.getText();
        float a = Float.parseFloat(x);
        float b = Float.parseFloat(y);
        if(a<-474 | b<-474){
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью координат");
            check=false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с записью координат");
            check =false;
            //System.err.println("Не указаны аргументы команды.");
        } catch (NumberFormatException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью координат");
            check = false;
        }
    }
    private void checkSize(){
        try{
        String size = sizeField.getText();
        double a = Double.parseDouble(size);
        if(a<0){
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью size");
            check =false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с записью size");
            check =false;
            //System.err.println("Не указаны аргументы команды.");
        } catch (NumberFormatException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью size");
            check = false;
        }
    }
    private void checkEyes(){
        try{
        String eyes = eyesField.getText();
        int a = Integer.parseInt(eyes);
        if(a<0){
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью eyesCount");
            check = false;
        }
    } catch  (IndexOutOfBoundsException ex) {
        Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с записью eyesCount");
        check =false;
        //System.err.println("Не указаны аргументы команды.");
    } catch (NumberFormatException ex) {
        Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью eyesCount");
        check = false;
    }
    }
    private void checkTooth(){
        try{
            String eyes = toothField.getText();
            long a = Long.parseLong(eyes);
            if(a<0){
                Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью toothCount");
                check = false;
            }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Не указаны аргументы команды", "Ошибка с записью toothCount");
            check =false;
            //System.err.println("Не указаны аргументы команды.");
        } catch (NumberFormatException ex) {
            Edition.showAlert("Ошибка с аргуметом", "Требуется ввести число, чтобы команда работала", "Ошибка с записью toothCount");
            check =false;
        }
    }
    public void allCheck(){
        check = true;
        checkName();
        if(check){
           checkAge();
        }
        if(check){
            checkWeight();
        }
        if(check){
            checkCoordinate();
        }
        if(check){
            checkEyes();
        }
        if(check){
            checkTooth();
        }
        if(check){
            checkSize();
        }

    }
    private void add(){

    }
}
