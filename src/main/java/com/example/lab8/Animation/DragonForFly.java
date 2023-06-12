package com.example.lab8.Animation;

import com.example.lab8.Base.Color;
import com.example.lab8.Base.Dragon;
import com.example.lab8.Base.DragonHead;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

public class DragonForFly {
    private double x;
    private String color;
    private Double size;
    private double y;

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
    public String name;
    public String creator;


    public DragonForFly(double x, String color, Double size, double y, ImageView dragonImageView, int currentFrameIndex, String name, String creator) {
        this.color = color;
        this.size = size;
        this.y = y;
        this.x = x;
        this.dragonImageView = dragonImageView;
        this.currentFrameIndex = currentFrameIndex;
        this.name = name;
        this.creator = creator;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public static LinkedList<DragonForFly> getNewColl(LinkedList<Dragon> baselist) {
        LinkedList<DragonForFly> newColl = new LinkedList<>();
        for (Dragon dragon : baselist) {
            String color = dragon.getColor().toString().toLowerCase();
            double sizes = dragon.getHead().getSize();
            double size = kfSize(sizes);
            float x = dragon.getCoordinates().getX();
            float y = dragon.getCoordinates().getY();
            double yy = kfY(y);
            double xx = kfX(x);
            String name = dragon.getName();
            String creator = dragon.getCreator();

            DragonForFly dragonForFly = new DragonForFly(xx, color, size, yy, null, 0, name, creator);
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
    private static double kfY(float size){
        if(size<-200){
            return -250;
        } else if (size>=-200 & size<-50) {
            return -105;
        } else if (size>=-50 & size<200) {
            return 0;
        } else if (size>=200 & size<1000) {
            return 105;
        } else {
            return 250;
        }
    }
    private static double kfX(float size){
        if(size<-200){
            return -500;
        } else if (size>=-200 & size<-50) {
            return -250;
        } else if (size>=-50 & size<200) {
            return 0;
        } else if (size>=200 & size<1000) {
            return 250;
        } else {
            return 500;
        }
    }
    public void setDragonImageView(ImageView imageView) {
        this.dragonImageView = imageView;
    }
}
