package com.example.lab8.Apps;

import com.example.lab8.Animation.DragonAnimation;
import com.example.lab8.Base.Color;
import com.example.lab8.Base.Dragon;
import com.example.lab8.Base.DragonType;
import com.example.lab8.Command.InvokerCommand;
import com.example.lab8.DataBase.Users;
import com.example.lab8.File.CollectionManager;
import com.example.lab8.File.Home;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Objects;

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
    public TableView<Dragon> table = new TableView<>();
    public Text username;
    public TextArea desk;
    public Button printAge;

    @FXML
    void initialize() {

        table.setFixedCellSize(30);
        table.setRowFactory(tv -> {
            TableRow<Dragon> row = new TableRow<>();
            row.setStyle("-fx-background-color: #9f6456; -fx-text-fill: white; -fx-border-color:  white ; -fx-font-family: Arial"); // Устанавливаем цвет фона строки
            return row;
        });
        username.setText(Users.getCurrentUser());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        color.setCellValueFactory(dragonStringCellDataFeatures ->
                dragonStringCellDataFeatures.getValue().getColor() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getColor().toString()));
        color.setCellFactory(TextFieldTableCell.forTableColumn()); // Здесь 'name' - название колонки, в которой нужно включить редактирование
        color.setOnEditCommit(event -> {
            try {
                Dragon dragon = event.getRowValue();
                if (!dragon.getCreator().equals(Users.getCurrentUser())) {
                    Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                    return;
                }
                String n = event.getNewValue().toUpperCase();
                Color color1 = Color.valueOf(n);
                dragon.setColor(color1);
                int index = table.getItems().indexOf(dragon); // Получаем индекс дракона в коллекции
                if (index >= 0) {
                    table.getItems().set(index, dragon); // Обновляем дракона в коллекции

                }
                collectionManager.save();
            } catch (IllegalArgumentException e){
                Edition.showAlert("Ошибка", "Введено некорректное значение для цвета", "Ошибка при изменении данных");
            }
            // Добавьте код для обновления данных в соответствующем списке или базе данных
        });
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weight.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        weight.setOnEditCommit(event -> {
            try{
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                    return;
                }
                long previousValue = dragon.getWeight(); // Сохраняем предыдущее значение

                long newValue = event.getNewValue();


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
                    Edition.showAlert("Ошибка", "Возраст не может быть отрицательным", "Ошибка при изменении данных");
                    dragon.setWeight(previousValue); // Восстанавливаем предыдущее значение
                    table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение для веса", "Ошибка при изменении данных");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }

        });
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        /*type.setCellFactory(TextFieldTableCell.forTableColumn()); // Здесь 'name' - название колонки, в которой нужно включить редактирование
        type.setOnEditCommit(event -> {
            try {
                Dragon dragon = event.getRowValue();
                if (!dragon.getCreator().equals(Users.getCurrentUser())) {
                    Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                    return;
                }
                String n = event.getNewValue().toUpperCase();
                DragonType color1 = DragonType.valueOf(n);
                dragon.setType(color1);
                int index = table.getItems().indexOf(dragon); // Получаем индекс дракона в коллекции
                if (index >= 0) {
                    table.getItems().set(index, dragon); // Обновляем дракона в коллекции

                }
                collectionManager.save();
            } catch (IllegalArgumentException e){
                Edition.showAlert("Ошибка", "Введено некорректное значение для тип", "Ошибка при изменении данных");
            }
            // Добавьте код для обновления данных в соответствующем списке или базе данных
        });*/
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        size.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        size.setOnEditCommit(event -> {
            try{
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                    return;
                }
                double previousValue = dragon.getHead().getSize(); // Сохраняем предыдущее значение

                double newValue = event.getNewValue();


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
                    Edition.showAlert("Ошибка", "Возраст не может быть отрицательным", "Ошибка при изменении данных");
                    dragon.getHead().setSize(previousValue); // Восстанавливаем предыдущее значение
                    table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение для размера", "Ошибка при изменении данных");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }

        });
        eyesCount.setCellValueFactory(new PropertyValueFactory<>("eyesCount"));
        eyesCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        eyesCount.setOnEditCommit(event -> {
            try{
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                    return;
                }
                int previousValue = dragon.getHead().getEyesCount(); // Сохраняем предыдущее значение

                int newValue = event.getNewValue();


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
                    Edition.showAlert("Ошибка", "Возраст не может быть отрицательным", "Ошибка при изменении данных");
                    dragon.getHead().setEyesCount(previousValue); // Восстанавливаем предыдущее значение
                    table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение для количества глаз", "Ошибка при изменении данных");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }

        });
        toothCount.setCellValueFactory(new PropertyValueFactory<>("toothCount"));
        toothCount.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        toothCount.setOnEditCommit(event -> {
            try{
                Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                    return;
                }
                long previousValue = dragon.getHead().getToothCount(); // Сохраняем предыдущее значение

                long newValue = event.getNewValue();


                //System.out.println(newValue);
                if (newValue > 0) {
                    dragon.getHead().setToothCount(newValue);
                    int index = table.getItems().indexOf(dragon);
                    if (index >= 0) {
                        table.getItems().set(index, dragon);
                        collectionManager.save();
                    }
                    // Добавьте код для обновления данных в соответствующем списке или базе данных
                } else {
                    Edition.showAlert("Ошибка", "Возраст не может быть отрицательным", "Ошибка при изменении данных");
                    dragon.getHead().setToothCount(previousValue); // Восстанавливаем предыдущее значение
                    table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
                }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение для количества зубов", "Ошибка при изменении данных");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }

        });
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
        table.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn()); // Здесь 'name' - название колонки, в которой нужно включить редактирование
        name.setOnEditCommit(event -> {
            Dragon dragon = event.getRowValue();
            String OldNAme = dragon.getName();
            if(!dragon.getCreator().equals(Users.getCurrentUser())){
                Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                return;
            }
            String n = event.getNewValue();
            if (!(n == null) & dragon.getCreator().equals(Users.getCurrentUser())) {
                dragon.setName(n);
                int index = table.getItems().indexOf(dragon); // Получаем индекс дракона в коллекции
                if (index >= 0) {
                    table.getItems().set(index, dragon); // Обновляем дракона в коллекции
                    collectionManager.save();
                }
                // Добавьте код для обновления данных в соответствующем списке или базе данных
            } else {
                Edition.showAlert("Ошибка", "Введено некорректное значение имени", "Ошибка при изменении данных");
                dragon.setName(OldNAme);
                table.refresh();
            }
        });

        table.setEditable(true);
        age.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        age.setOnEditCommit(event -> {
            try{
            Dragon dragon = event.getRowValue();
                if(!dragon.getCreator().equals(Users.getCurrentUser())){
                    Edition.showAlert("Ошибка", "Этот дракон принадлежит не вам", "Изменение запрещено");
                    return;
                }
            int previousValue = dragon.getAge(); // Сохраняем предыдущее значение

                int newValue = Integer.parseInt(String.valueOf(event.getNewValue()));


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
                Edition.showAlert("Ошибка", "Возраст не может быть отрицательным", "Ошибка при изменении данных");
                dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                //table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }
            } catch (NumberFormatException | NullPointerException e) {
                Edition.showAlert("Ошибка", "Введено некорректное значение для возраста", "Ошибка при изменении данных");
                //dragon.setAge(previousValue); // Восстанавливаем предыдущее значение
                table.refresh(); // Обновляем таблицу, чтобы отобразить предыдущее значение
            }

        });



        update();

    print_age.setOnAction(event ->desk.setText(collectionManager.printAscedingAge()));
    print_type.setOnAction(event -> desk.setText(collectionManager.printDescendingType()));
    clear.setOnAction(event -> {
        collectionManager.clear();
        update();
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

    }
    public  void update(){
        LinkedList<Dragon> updatedList = new LinkedList<>(CollectionManager.getDragons());
        table.setItems(FXCollections.observableArrayList(updatedList));
    }



}
