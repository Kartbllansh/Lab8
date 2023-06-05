package com.example.lab8.Animation;
import com.example.lab8.Apps.Edition;
import com.example.lab8.File.CollectionManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
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

    @FXML
    void initialize() {

    }

    public static Stage primaryStage = new Stage();
    public void startAnimation()  {
        StackPane root = new StackPane();

        Image backgroundImage = new Image("/image/Без имени-1.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        root.getChildren().add(backgroundImageView);
        for(DragonForFly dragonForFly : DragonForFly.getNewColl(CollectionManager.creatorCollection())) {
            String color = dragonForFly.getColor();
            double size = dragonForFly.getSize();
            Random random = new Random();

            // Генерация случайного числа -1 или 1
            int randomValue = random.nextInt(2) * 2 - 1;
            ImageView ff = new ImageView();
            Timeline timeline =  new Timeline(
                    new KeyFrame(Duration.millis(FRAME_DURATION), event -> {
                        if (direction == -1) {
                            changeFrameLeft(ff, color, size);
                        } else {
                            changeFrameRight(ff, color, size);
                        }
                    })
            );
            DragonAnimated dragonAnimated = new DragonAnimated(ff, timeline, randomValue);
            dragonAnimated.getAnimation().setCycleCount(Animation.INDEFINITE);
            dragonAnimated.getAnimation().play();
            root.getChildren().add(dragonAnimated.getImageView());
            StackPane.setAlignment(dragonAnimated.getImageView(), Pos.CENTER);
        }
            /*dragonImageView = new ImageView();
            animation = new Timeline(
                    new KeyFrame(Duration.millis(FRAME_DURATION), event -> {
                        if (direction == -1) {
                            changeFrameLeft();
                        } else {
                            changeFrameRight();
                        }
                    })
            );*/
            /*animation.setCycleCount(Animation.INDEFINITE);
            animation.play();*/


           /* root.getChildren().add(dragonImageView);
            StackPane.setAlignment(dragonImageView, Pos.CENTER);*/
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setTitle("Dragon Animation");
        primaryStage.show();
    }
    private void fly(ImageView a){
        Random random = new Random();
        boolean shouldFlyUp = random.nextBoolean();
        // Генерируем случайное смещение для координаты Y
        double shiftY = shouldFlyUp ? -5 : 5;

        // Изменяем координату Y дракона
        a.setTranslateY(a.getTranslateY() + shiftY);
    }




    private void changeFrameLeft(ImageView a, String color, double size) {
        currentFrameIndex = (currentFrameIndex + 1) % FRAME_COUNT;
        Image frameImage = new Image("/animation/"+color+"/dragon_frame_" + currentFrameIndex + ".png");
        double newWidth = frameImage.getWidth() * size;
        double newHeight = frameImage.getHeight() * size;

        Image scaledImage = new Image(frameImage.getUrl(), newWidth, newHeight, false, true);
        a.setImage(scaledImage);
        a.setTranslateX(a.getTranslateX() + SHIFT_AMOUNT);
        if (getDragonPosition(a).getX() <= checkLeftWall.getX()) {
            direction = 1; // Изменение направления на движение вправо
        }
        fly(a);
    }
    private void changeFrameRight(ImageView a, String color, double size) {
        currentFrameIndex = (currentFrameIndex + 1) % FRAME_COUNT;
        Image frameImage = new Image("/animation/"+color+"/dragon_frame_" + currentFrameIndex + "_rever.png");
        double newWidth = frameImage.getWidth() * size;
        double newHeight = frameImage.getHeight() * size;

        Image scaledImage = new Image(frameImage.getUrl(), newWidth, newHeight, false, true);
        a.setImage(scaledImage);
       // a.setImage(frameImage);
        a.setTranslateX(a.getTranslateX() + SHIFT_AMOUNT_Right);
        if (getDragonPosition(a).getX() >= checkRightWall.getX()) {
            direction = -1; // Изменение направления на движение влево
        }
        fly(a);
    }
    public Point2D getDragonPosition(ImageView a) {
        double x = a.getTranslateX();
        double y = a.getTranslateY();
        return new Point2D(x, y);
    }

    /*public int checkTouch(){
        if(getDragonPosition().equals(checkLeftWall)){
         return -1;
        } else if (getDragonPosition().equals(checkRightWall)) {
         return 1;
        } else {
            return 0;
        }
    }*/
    public static void IfCloseWindow(){
        currentFrameIndex = 0;

        //direction = 1;


    }
}


