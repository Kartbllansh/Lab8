package com.example.lab8.Animation;

import com.example.lab8.Base.Color;
import com.example.lab8.Base.Dragon;
import com.example.lab8.Base.DragonHead;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

public class DragonForFly {
    private long id;
    private String color;
    private Double size;
    private int group;

    public String getColor() {
        return color;
    }

    public Double getSize() {
        return size;
    }

    public ImageView getDragonImageView() {
        return dragonImageView;
    }

    private  ImageView dragonImageView;

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    private int currentFrameIndex = 0;


    public DragonForFly(long id, String color, Double size, int group, ImageView dragonImageView, int currentFrameIndex) {
        this.color = color;
        this.size = size;
        this.group = group;
        this.id = id;
        this.dragonImageView = dragonImageView;
        this.currentFrameIndex = currentFrameIndex;
    }

    public static LinkedList<DragonForFly> getNewColl(LinkedList<Dragon> baselist) {
        LinkedList<DragonForFly> newColl = new LinkedList<>();
        for (Dragon dragon : baselist) {
            long id = dragon.getId();
            String color = dragon.getColor().toString().toLowerCase();
            double sizes = dragon.getHead().getSize();
            double size = kfSize(sizes);
            float x = dragon.getCoordinates().getX();
            float y = dragon.getCoordinates().getY();
            int group;
            if (x < 410 & y < 340) {
                group = 4;
            } else if (x > 410 & y < 340) {
                group = 3;
            } else if (x > 410 & y > 340) {
                group = 1;

            } else {
                group = 1;
            }
            DragonForFly dragonForFly = new DragonForFly(id, color, size, group, null, 0);
            newColl.add(dragonForFly);
        }
        return newColl;
    }

    private static double kfSize(double size){
        if(size<10){
            return 0.6;
        } else if (size>=10 & size<70) {
            return 0.8;
        } else if (size>=70 & size<150) {
        return 1;
        } else if (size>150 & size<300) {
        return 1.2;
        } else {
            return 1.5;
        }
    }
    public void setDragonImageView(ImageView imageView) {
        this.dragonImageView = imageView;
    }
}
