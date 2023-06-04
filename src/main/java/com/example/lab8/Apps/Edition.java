package com.example.lab8.Apps;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Edition {
   public static void showAlert(String title, String text, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #787878");
        dialogPane.setStyle("-fx-text-fill-color: Black");
        alert.showAndWait();
    }
    public static void showAlertHelp(String title, String text, String header) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #787878");
        dialogPane.setStyle("-fx-text-fill-color: Black");
        alert.showAndWait();
    }

    public static void createForm(Stage stage, String url, String title, double x, double y) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load(), x, y);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
