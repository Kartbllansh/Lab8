package com.example.lab8.Animation;

import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
public class Controller {
    DragonAnimation dragonAnimation = new DragonAnimation();

    @FXML
    public void initialize() {

        System.out.println("IT's WORKKK");
        //HashMap<ImageView, DragonForFly> hashMap = dragonAnimation.getHashMap();
        //System.out.println(hashMap);
        for(Map.Entry<ImageView, DragonForFly> entry : dragonAnimation.getHashMap().entrySet()){
            DragonForFly dragon = entry.getValue();
            ImageView imageView = entry.getKey();
            String info = "Дракон c именем - "+dragon.getName()+ "принадлежит пользавтелю: "+dragon.getCreator();
            Tooltip tooltip = new Tooltip(info);
            Tooltip.install(imageView, tooltip);
        }
    }
}
