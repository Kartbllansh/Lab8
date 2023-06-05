package com.example.lab8.Apps;

import com.example.lab8.Animation.DragonAnimation;
import com.example.lab8.Base.Dragon;
import com.example.lab8.Command.InvokerCommand;
import com.example.lab8.DataBase.Users;
import com.example.lab8.File.CollectionManager;
import com.example.lab8.File.Home;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.ZonedDateTime;

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
            row.setStyle("-fx-background-color: #9f6456; -fx-text-fill: white; -fx-border-color: transparent transparent black transparent; -fx-font-family: Arial"); // Устанавливаем цвет фона строки
            return row;
        });
        username.setText(Users.getCurrentUser());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        eyesCount.setCellValueFactory(new PropertyValueFactory<>("eyesCount"));
        toothCount.setCellValueFactory(new PropertyValueFactory<>("toothCount"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
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
    DragonAnimation.primaryStage.setOnCloseRequest(event -> {
        DragonAnimation.IfCloseWindow();
        DragonAnimation.mediaPlayer.stop();

    });
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
            Edition.createForm(stage, "/fxml/add.fxml", "Add Command", 800, 600);

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
        ObservableList<Dragon> list = FXCollections.observableArrayList(CollectionManager.getDragons());
        table.setItems(list);
    }


}
