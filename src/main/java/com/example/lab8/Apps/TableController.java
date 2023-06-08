package com.example.lab8.Apps;

import com.example.lab8.Animation.DragonAnimation;
import com.example.lab8.Base.Color;
import com.example.lab8.Base.Dragon;
import com.example.lab8.Base.DragonType;
import com.example.lab8.DataBase.Users;
import com.example.lab8.File.CollectionManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;


public class TableController {
    public MenuButton print;
    public MenuItem print_type;
    public MenuItem print_age;
    public Button clear;
    public Button info;
    public Button help;
    public Button save;
    public Button logout;
    public Button remove;
    public Button add;
    public Button exit;
    public Button map;

    CollectionManager collectionManager = new CollectionManager();
    DragonAnimation dragonAnimation = new DragonAnimation();
    public TableColumn<Dragon, Float> x;
    public TableColumn<Dragon, Float> y;

    public TableColumn<Dragon, Integer> id;
    public TableColumn<Dragon, String> name;
    public TableColumn<Dragon, String> color;
    public TableColumn<Dragon, Long> weight;
    public TableColumn<Dragon, Integer> age;
    public TableColumn<Dragon, String> type;
    public TableColumn<Dragon, Double> size;
    public TableColumn<Dragon, Integer> eyesCount;
    @FXML
    public TableColumn<Dragon, Long> toothCount;
    public TableColumn<Dragon, ZonedDateTime> creationDate;
    public TableColumn<Dragon, String> creator;
    public  TableView<Dragon> table = new TableView<>();
    public Text username;
    public TextArea desk;
    public Button printAge;

    @FXML
    void initialize() {

        //update();
        table.setFixedCellSize(30);
        table.setRowFactory(tv -> {
            TableRow<Dragon> row = new TableRow<>();
            row.setStyle("-fx-background-color: #9f6456; -fx-text-fill: white; -fx-border-color:  white ; -fx-font-family: Arial"); // Устанавливаем цвет фона строки
            return row;
        });
        username.setText(Users.getCurrentUser());
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn()); // Здесь 'name' - название колонки, в которой нужно включить редактирование
        name.setOnEditCommit(event -> {
            boolean a = true;
            Dragon dragon = event.getRowValue();
            String OldNAme = dragon.getName();
            if (!dragon.getCreator().equals(Users.getCurrentUser())) {
                Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                a = false;
            }
            if (a) {
                String n = event.getNewValue();
                if (!(n.equals("")) & dragon.getCreator().equals(Users.getCurrentUser())) {
                    dragon.setName(n);
                    int index = table.getItems().indexOf(dragon); // Получаем индекс дракона в коллекции
                    if (index >= 0) {
                        table.getItems().set(index, dragon); // Обновляем дракона в коллекции
                        collectionManager.save();
                    }
                    // Добавьте код для обновления данных в соответствующем списке или базе данных
                } else {
                    Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                    dragon.setName(OldNAme);
                    table.refresh();
                }
            }
            table.refresh();
        });
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        color.setCellValueFactory(dragonStringCellDataFeatures ->
                dragonStringCellDataFeatures.getValue().getColor() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getColor().toString()));
      color.setCellFactory(TextFieldTableCell.forTableColumn()); // Здесь 'name' - название колонки, в которой нужно включить редактирование
        color.setOnEditCommit(event -> {
            try {
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if (!dragon.getCreator().equals(Users.getCurrentUser())) {
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if (a) {
                    String n = event.getNewValue().toUpperCase();
                    Color color1 = Color.valueOf(n);
                    dragon.setColor(color1);
                    int index = table.getItems().indexOf(dragon); // Получаем индекс дракона в коллекции
                    if (index >= 0) {
                        table.getItems().set(index, dragon); // Обновляем дракона в коллекции
                        table.refresh();

                    }
                    collectionManager.save();
                }
            }catch (IllegalArgumentException e){
                Edition.showAlert("Ошибка", "Введено некорректное значение для цвета", "Ошибка при изменении данных");
                table.refresh();
            }
            // Добавьте код для обновления данных в соответствующем списке или базе данных
            table.refresh();
        });
        x.setCellValueFactory(new PropertyValueFactory<>("x"));
     x.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        x.setOnEditCommit(event -> {
            System.out.println("sssss");
            try{
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if(a) {
                    float previousValue = dragon.getCoordinates().getX(); // Сохраняем предыдущее значение
                    String i = event.getNewValue().toString();
                    System.out.println(i);
                    if (!i.matches("\\d*")) {
                        Edition.showAlert("Ошибка", "Неправильно введено поле", "");
                        a = false;
                    }
                    if (a) {
                        float newValue = Float.parseFloat(event.getNewValue().toString());


                        //System.out.println(newValue);
                        if (newValue > 0) {
                            dragon.getCoordinates().setX(newValue);
                            int index = table.getItems().indexOf(dragon);
                            if (index >= 0) {
                                table.getItems().set(index, dragon);
                                collectionManager.save();
                            }
                            // Добавьте код для обновления данных в соответствующем списке или базе данных
                        } else {
                            Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                            dragon.getCoordinates().setX(previousValue); // Восстанавливаем предыдущее значение
                            table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                        }
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
            table.refresh();

        });
        y.setCellValueFactory(new PropertyValueFactory<>("y"));
       y.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        y.setOnEditCommit(event -> {
            System.out.println("sssss");
            try{
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if(a) {
                    float previousValue = dragon.getCoordinates().getY(); // Сохраняем предыдущее значение
                    String i = event.getNewValue().toString();
                    System.out.println(i);
                    if (!i.matches("\\d*")) {
                        Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                        a = false;
                    }
                    if (a) {
                        float newValue = Float.parseFloat(event.getNewValue().toString());


                        //System.out.println(newValue);
                        if (newValue > 0) {
                            dragon.getCoordinates().setY(newValue);
                            int index = table.getItems().indexOf(dragon);
                            if (index >= 0) {
                                table.getItems().set(index, dragon);
                                collectionManager.save();
                            }
                            // Добавьте код для обновления данных в соответствующем списке или базе данных
                        } else {
                            Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                            dragon.getCoordinates().setX(previousValue); // Восстанавливаем предыдущее значение
                            table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                        }
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
            table.refresh();

        });
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weight.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        weight.setOnEditCommit(event -> {
            System.out.println("sssss");
            try{
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if(a) {
                    long previousValue = dragon.getWeight(); // Сохраняем предыдущее значение
                    String i = event.getNewValue().toString();
                    System.out.println(i);
                    if (!i.matches("\\d*")) {
                        Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                        a = false;
                    }
                    if (a) {
                        long newValue = Long.parseLong(event.getNewValue().toString());


                        //System.out.println(newValue);
                        if (newValue > 0) {
                            dragon.setWeight(newValue);
                            int index = table.getItems().indexOf(dragon);
                            if (index >= 0) {
                                table.getItems().set(index, dragon);
                                collectionManager.save();
                            }
                            // Добавьте код для обновления данных в соответствующем списке или базе данных
                        } else {
                            Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                            dragon.setWeight(previousValue); // Восстанавливаем предыдущее значение
                            table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                        }
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
            table.refresh();

        });
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
      age.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        age.setOnEditCommit(event -> {
            System.out.println("sssss");
            try{
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if(a) {
                    int previousValue = dragon.getAge(); // Сохраняем предыдущее значение
                    String i = event.getNewValue().toString();
                    System.out.println(i);
                    if (!i.matches("\\d*")) {
                        Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                        a = false;
                    }
                    if (a) {
                        int newValue = Integer.parseInt(event.getNewValue().toString());


                        //System.out.println(newValue);
                        if (newValue > 0) {
                            dragon.setAge(newValue);
                            int index = table.getItems().indexOf(dragon);
                            if (index >= 0) {
                                table.getItems().set(index, dragon);
                                collectionManager.save();
                            }
                            // Добавьте код для обновления данных в соответствующем списке или базе данных
                        } else {
                            Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                            dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                            table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                        }
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
        table.refresh();
        });
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
       type.setCellValueFactory(dragonStringCellDataFeatures ->
                dragonStringCellDataFeatures.getValue().getType() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getType().toString()));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(event -> {
            try {
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if (!dragon.getCreator().equals(Users.getCurrentUser())) {
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if (a) {
                    String n = event.getNewValue().toUpperCase();
                    DragonType color1 = DragonType.valueOf(n);
                    dragon.setType(color1);
                    int index = table.getItems().indexOf(dragon); // Получаем индекс дракона в коллекции
                    if (index >= 0) {
                        table.getItems().set(index, dragon); // Обновляем дракона в коллекции
                        table.refresh();
                    }
                    collectionManager.save();
                }
            }catch (IllegalArgumentException e){
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                table.refresh();
            }
            table.refresh();
            // Добавьте код для обновления данных в соответствующем списке или базе данных
        });
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
       size.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        size.setOnEditCommit(event -> {
            System.out.println("sssss");
            try{
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if(a) {
                    double previousValue = dragon.getHead().getSize(); // Сохраняем предыдущее значение
                    String i = event.getNewValue().toString();
                    System.out.println(i);
                    if (!i.matches("\\d*")) {
                        Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                        a = false;
                    }
                    if (a) {
                        double newValue = Double.parseDouble(event.getNewValue().toString());


                        //System.out.println(newValue);
                        if (newValue > 0) {
                            dragon.getHead().setSize(newValue);
                            int index = table.getItems().indexOf(dragon);
                            if (index >= 0) {
                                table.getItems().set(index, dragon);
                                collectionManager.save();
                            }
                            // Добавьте код для обновления данных в соответствующем списке или базе данных
                        } else {
                            Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                            dragon.getHead().setSize(previousValue); // Восстанавливаем предыдущее значение
                            table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                        }
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
            table.refresh();
        });
        eyesCount.setCellValueFactory(new PropertyValueFactory<>("eyesCount"));
        eyesCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        eyesCount.setOnEditCommit(event -> {
            System.out.println("sssss");
            try{
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if(a) {
                    int previousValue = dragon.getHead().getEyesCount(); // Сохраняем предыдущее значение
                    String i = event.getNewValue().toString();
                    System.out.println(i);
                    if (!i.matches("\\d*")) {
                        Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                        a = false;
                    }
                    if (a) {
                        int newValue = Integer.parseInt(event.getNewValue().toString());


                        //System.out.println(newValue);
                        if (newValue > 0) {
                            dragon.getHead().setEyesCount(newValue);
                            int index = table.getItems().indexOf(dragon);
                            if (index >= 0) {
                                table.getItems().set(index, dragon);
                                collectionManager.save();
                            }
                            // Добавьте код для обновления данных в соответствующем списке или базе данных
                        } else {
                            Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                            dragon.getHead().setEyesCount(previousValue); // Восстанавливаем предыдущее значение
                            table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                        }
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
            table.refresh();
        });
        toothCount.setCellValueFactory(new PropertyValueFactory<>("toothCount"));
        toothCount.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        toothCount.setOnEditCommit(event -> {
            System.out.println("sssss");
            try{
                boolean a = true;
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Объект принадлежит другому пользователю ", "");
                    a = false;
                }
                if(a) {
                    long previousValue = dragon.getHead().getToothCount(); // Сохраняем предыдущее значение
                    String i = event.getNewValue().toString();
                    System.out.println(i);
                    if (!i.matches("\\d*")) {
                        Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                        a = false;
                    }
                    if (a) {
                        long newValue = Long.parseLong(event.getNewValue().toString());

                        if (newValue > 0) {
                            dragon.getHead().setToothCount(newValue);
                            int index = table.getItems().indexOf(dragon);
                            if (index >= 0) {
                                table.getItems().set(index, dragon);
                                collectionManager.save();
                            }
                            // Добавьте код для обновления данных в соответствующем списке или базе данных
                        } else {
                            Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                            dragon.getHead().setToothCount(previousValue); // Восстанавливаем предыдущее значение
                            table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                        }
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение", "");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
            table.refresh();

        });
        update();
        table.setEditable(true);
    print_age.setOnAction(event ->desk.setText(collectionManager.printAscedingAge()));
    print_type.setOnAction(event -> desk.setText(collectionManager.printDescendingType()));
    clear.setOnAction(event -> {
        collectionManager.clear();
        update();
        //table.refresh();
    });
    info.setOnAction(event -> desk.setText(collectionManager.getInfo()));
    map.setOnAction(event -> {

        dragonAnimation.startAnimation();
    });
    /*DragonAnimation.primaryStage.setOnCloseRequest(event -> {
        DragonAnimation.IfCloseWindow();
        DragonAnimation.mediaPlayer.stop();

    });*/
    help.setOnAction(event -> {
        Stage stage = new Stage();
        try {
            Edition.createForm(stage, "/fxml/help.fxml", "Help", 500, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    save.setOnAction(event -> collectionManager.save());
    exit.setOnAction(event -> System.exit(0));
    add.setOnAction(event -> {
        Stage stage = new Stage();
        try {
            Edition.createForm(stage,"/fxml/add.fxml", "Add Command", 800, 600);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    });
    logout.setOnAction(event -> {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SIGNUP.fxml"));
            Scene signUpScene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage currentStage = (Stage) save.getScene().getWindow();
            currentStage.setScene(signUpScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    remove.setOnAction(event -> {
        Stage stage = new Stage();
        try {
            Edition.createForm(stage, "/fxml/remove.fxml", "Remove Command", 500, 300);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    update();
    }
    public  void update(){

       LinkedList<Dragon> updatedList = new LinkedList<>(CollectionManager.getDragons());
       System.out.println(updatedList.toString()+",kdmddjjdjdjdjdjdjd");
        table.setItems(FXCollections.observableArrayList(updatedList));
       // table.setItems(FXCollections.observableList(new ArrayList<>(CollectionManager.getDragons())));
    }
    }



