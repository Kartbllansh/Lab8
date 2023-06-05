package com.example.lab8.Animation;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

public class DragonAnimated {
    private ImageView imageView;
    private Timeline animation;
    private int direction;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Timeline getAnimation() {
        return animation;
    }

    public void setAnimation(Timeline animation) {
        this.animation = animation;
    }

    public DragonAnimated(ImageView imageView, Timeline animation, int direction) {
        this.imageView = imageView;
        this.animation = animation;
        this.direction =direction;
    }
}
