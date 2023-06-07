package com.example.lab8.Animation;

import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class Controller {
    @FXML
    private ImageView image1;
    @FXML
    void initialize() {
        Tooltip tooltip = new Tooltip("ccccc");
        Tooltip.install(image1, tooltip);
    }
}
