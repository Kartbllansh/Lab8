package com.example.lab8.Apps;

import com.example.lab8.Base.*;
import com.example.lab8.DataBase.Users;
import com.example.lab8.File.CollectionManager;
import com.example.lab8.File.VotTvoyId;
import com.example.lab8.Languagesss.CurrentLanguage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AddController implements Initializable {
    public Text weight;
    public Text name;
    public Text age;
    public Text type;
    public Text color;
    public Text tooth;
    public Text eyes;
    public Text size;
    public Text x;
    public Text y;
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
    int a =1;
    @Override
    public void initialize(URL location, ResourceBundle resources){


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
              //System.out.println(change);
            des=2;
          });
          addMin.setOnAction(event1 -> {
              des=1;

          });
          update.setOnAction(event1 -> {
              des=3;
          });
        start.setOnAction(event -> {
            if(a==1) {

                if (change.getColor() == null) {
                    Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("notColor"), "");
                    return;
                }
                if (change.getType() == null) {
                    Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("notType"), "");
                    return;
                }
                allCheck();
                a=2;
                change.setCreationDate(ZonedDateTime.now());
                change.setCreator(Users.getCurrentUser());
                VotTvoyId.votDragon(change);
                change.setAge(Integer.parseInt(ageField.getText()));
                change.setName(nameField.getText());
                change.setWeight(Long.parseLong(weightField.getText()));
                DragonHead dragonHead = new DragonHead(Double.parseDouble(sizeField.getText()), Integer.parseInt(eyesField.getText()), Long.parseLong(toothField.getText()));
                change.setHead(dragonHead);
                Coordinates coordinates = new Coordinates(Float.parseFloat(xField.getText()), Float.parseFloat(yField.getText()));
                change.setCoordinates(coordinates);
                a = 2;
                if (des == 1) {
                    collectionManager.addIfMin(change);
                    collectionManager.idget();
                    collectionManager.save();
                    tableController.update();
                    Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("succ"), CurrentLanguage.getCurrentLanguage().getString("succ"), "");
                } else if (des == 2) {

                    collectionManager.add(change);
                    ;
                    collectionManager.idget();
                    collectionManager.show();
                    collectionManager.save();
                    tableController.update();
                    //tableController.ref();
                    Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("succ"), CurrentLanguage.getCurrentLanguage().getString("succ"), "");
                } else if (des == 3) {
                    try {
                        collectionManager.updateId(Long.parseLong(idField.getText()));
                        collectionManager.idget();
                        collectionManager.save();
                        tableController.update();
                        //tableController.refresh();
                        Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("succ"), CurrentLanguage.getCurrentLanguage().getString("succ"), "");
                    } catch (IndexOutOfBoundsException ex) {
                        Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "");
                        //System.err.println("Не указаны аргументы команды.");

                    } catch (NumberFormatException ex) {
                        Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "");
                    }

                } else {
                    Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), CurrentLanguage.getCurrentLanguage().getString("choose"), "");
                }
            } else {
                Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), CurrentLanguage.getCurrentLanguage().getString("window"), "");

            }
        });


        appLang();
    }

    private void checkName(){
    String name = nameField.getText();
        if (!name.matches("[-a-zA-Zа-яА-ЯЁё]+")) {
            System.out.println("Неправильный формат");
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "name");
            check = false;
        }
    }
    private void checkAge(){
        try{
        String age = ageField.getText();
        int a = Integer.parseInt(age);
        if(a<=0){
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "age");
            check=false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "age");
            //System.err.println("Не указаны аргументы команды.");
            check = false;
        } catch (NumberFormatException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "age");
            check = false;
        }

    }
    private void checkWeight(){
        try{
        String weight = weightField.getText();
        long a = Long.parseLong(weight);
        if(a<=0){
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "weight");
            check = false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "weight");
            //System.err.println("Не указаны аргументы команды.");
            check=false;
        } catch (NumberFormatException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "weight");
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
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "Coordinate");
            check=false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "Coordinate");
            check =false;
            //System.err.println("Не указаны аргументы команды.");
        } catch (NumberFormatException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "Coordinate");
            check = false;
        }
    }
    private void checkSize(){
        try{
        String size = sizeField.getText();
        double a = Double.parseDouble(size);
        if(a<0){
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "size");
            check =false;
        }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "size");
            check =false;
            //System.err.println("Не указаны аргументы команды.");
        } catch (NumberFormatException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "size");
            check = false;
        }
    }
    private void checkEyes(){
        try{
        String eyes = eyesField.getText();
        int a = Integer.parseInt(eyes);
        if(a<0){
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "eyesCount");
            check = false;
        }
    } catch  (IndexOutOfBoundsException ex) {
        Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "eyesCount");
        check =false;
        //System.err.println("Не указаны аргументы команды.");
    } catch (NumberFormatException ex) {
        Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "eyesCount");
        check = false;
    }
    }
    private void checkTooth(){
        try{
            String eyes = toothField.getText();
            long a = Long.parseLong(eyes);
            if(a<0){
                Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber") , "toothCount");
                check = false;
            }
        } catch  (IndexOutOfBoundsException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("argComm"), "toothCount");
            check =false;
            //System.err.println("Не указаны аргументы команды.");
        } catch (NumberFormatException ex) {
            Edition.showAlert(CurrentLanguage.getCurrentLanguage().getString("errArg"), CurrentLanguage.getCurrentLanguage().getString("needNumber"), "toothCount");
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
   public void appLang(){
       ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        weight.setText(currentLanguage.getString("weightA"));
        name.setText(currentLanguage.getString("nameA"));
        age.setText(currentLanguage.getString("ageA"));
        x.setText(currentLanguage.getString("xA"));
        y.setText(currentLanguage.getString("yA"));
        color.setText(currentLanguage.getString("colorA"));
        type.setText(currentLanguage.getString("typeA"));
        tooth.setText(currentLanguage.getString("toothA"));
        eyes.setText(currentLanguage.getString("eyesA"));
        size.setText(currentLanguage.getString("sizeA"));
        yellow.setText(currentLanguage.getString("yellow"));
        orange.setText(currentLanguage.getString("orange"));
        white.setText(currentLanguage.getString("white"));
        air.setText(currentLanguage.getString("air"));
        water.setText(currentLanguage.getString("water"));
        underground.setText(currentLanguage.getString("underground"));
        update.setText(currentLanguage.getString("updateIdA"));
        add.setText(currentLanguage.getString("addA"));
        addMin.setText(currentLanguage.getString("addminA"));
   }
}
