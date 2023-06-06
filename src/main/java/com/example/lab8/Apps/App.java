package com.example.lab8.Apps;

import com.example.lab8.Animation.DragonAnimation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        primaryStage.setTitle("Dragon Program");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/data/ico.png"));
        primaryStage.show();
    }

    public static void go() {
        launch();

    }

    }