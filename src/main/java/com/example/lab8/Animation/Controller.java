package com.example.lab8.Animation;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public StackPane rootPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            System.out.println("work");
            for (Map.Entry<ImageView, DragonForFly> entry : DragonAnimation.hashMap.entrySet()) {
                DragonForFly dragon = entry.getValue();
                ImageView imageView = entry.getKey();
                //rootPane.getChildren().add(imageView);
                String info = "Дракон c именем - " + dragon.getName() + " принадлежит пользавтелю: " + dragon.getCreator();
                System.out.println("1");
                Tooltip tooltip = new Tooltip(info);
                Tooltip.install(imageView, tooltip);
            }
            Button button = new Button();
            rootPane.getChildren().add(button);
            Tooltip tt = new Tooltip("It;s knopka");
            Tooltip.install(button, tt);
        });
    }
}
