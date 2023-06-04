package com.example.lab8.Animation;
import com.example.lab8.Apps.Edition;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class DragonAnimation {

    private static final int FRAME_COUNT = 4;
    private static final int FRAME_DURATION = 120; // В миллисекундах
    private static final double SHIFT_AMOUNT = -10; // Сдвиг влево
    private static final double SHIFT_AMOUNT_Right = 10; // Сдвиг влево

    private static ImageView dragonImageView;
    private static int currentFrameIndex = 0;
    Point2D checkLeftWall = new Point2D(-830, 0);
    Point2D checkRightWall = new Point2D(820, 0);
    private static int direction = 1;
    public  static Timeline animation;



    public static Stage primaryStage = new Stage();
    public void startAnimation()  {
        StackPane root = new StackPane();

        Image backgroundImage = new Image("/image/Без имени-1.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        root.getChildren().add(backgroundImageView);

        dragonImageView = new ImageView();
        animation = new Timeline(
                new KeyFrame(Duration.millis(FRAME_DURATION), event -> {
                    if(direction==-1) {
                        changeFrameLeft();
                    } else{
                        changeFrameRight();
                    }
                })
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();


        root.getChildren().add(dragonImageView);
        StackPane.setAlignment(dragonImageView, Pos.CENTER);
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setTitle("Dragon Animation");
        primaryStage.show();
    }
    private void fly(){
        Random random = new Random();
        boolean shouldFlyUp = random.nextBoolean();
        // Генерируем случайное смещение для координаты Y
        double shiftY = shouldFlyUp ? -10 : 10;

        // Изменяем координату Y дракона
        dragonImageView.setTranslateY(dragonImageView.getTranslateY() + shiftY);
    }




    private void changeFrameLeft() {
        currentFrameIndex = (currentFrameIndex + 1) % FRAME_COUNT;
        Image frameImage = new Image("/animation/orange/dragon_frame_" + currentFrameIndex + ".png");
        dragonImageView.setImage(frameImage);
        dragonImageView.setTranslateX(dragonImageView.getTranslateX() + SHIFT_AMOUNT);
        if (getDragonPosition().getX() <= checkLeftWall.getX()) {
            direction = 1; // Изменение направления на движение вправо
        }
        //fly();
    }
    private void changeFrameRight() {
        currentFrameIndex = (currentFrameIndex + 1) % FRAME_COUNT;
        Image frameImage = new Image("/animation/orange/dragon_frame_" + currentFrameIndex + "_rever.png");
        dragonImageView.setImage(frameImage);
        dragonImageView.setTranslateX(dragonImageView.getTranslateX() + SHIFT_AMOUNT_Right);
        if (getDragonPosition().getX() >= checkRightWall.getX()) {
            direction = -1; // Изменение направления на движение влево
        }
        //fly();
    }
    public Point2D getDragonPosition() {
        double x = dragonImageView.getTranslateX();
        double y = dragonImageView.getTranslateY();
        return new Point2D(x, y);
    }

    public int checkTouch(){
        if(getDragonPosition().equals(checkLeftWall)){
         return -1;
        } else if (getDragonPosition().equals(checkRightWall)) {
         return 1;
        } else {
            return 0;
        }
    }
    public static void IfCloseWindow(){
        currentFrameIndex = 0;
        dragonImageView.setTranslateX(0);
        dragonImageView.setTranslateY(0);
        direction = 1;
        //Image frameImage = new Image("/animation/white/dragon_frame_" + currentFrameIndex + ".png");
        //dragonImageView.setImage(frameImage);
        animation.stop();

    }
}


