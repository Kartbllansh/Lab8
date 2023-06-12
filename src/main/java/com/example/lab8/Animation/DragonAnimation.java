package com.example.lab8.Animation;
import com.example.lab8.Apps.Edition;
import com.example.lab8.Client;
import com.example.lab8.File.CollectionManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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
    private int direction = 1;
    public  static Timeline animation;
    public static MediaPlayer mediaPlayer;
    public HashMap<ImageView, DragonForFly> hashMap = new HashMap<>();
    public HashMap<ImageView, DragonForFly> getHashMap(){
        return hashMap;
    }

    public static Stage primaryStage = new Stage();
    public void startAnimation()  {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/image/animation.fxml"));
        LinkedList<DragonAnimated> dragonAnimateds = new LinkedList<>();
        StackPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        //Image backgroundImage = new Image("/image/Безимени-1.jpg");
        //ImageView backgroundImageView = new ImageView(backgroundImage);
       // root.getChildren().add(backgroundImageView);
        for(DragonForFly dragonForFly : DragonForFly.getNewColl(CollectionManager.getDragons())) {
            i++;
            String color = dragonForFly.getColor();
            double size = dragonForFly.getSize();
            Random random = new Random();

            // Генерация случайного числа -1 или 1
            int randomValue = random.nextInt(2) * 2 - 1;
           // DragonAnimated dragDir = new DragonAnimated(randomValue);
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
            DragonAnimated dragonAnimated = new DragonAnimated(ff, timeline);
            dragonAnimateds.add(dragonAnimated);
            //double initialY = random.nextDouble() * (root.getHeight() - dragonAnimated.getImageView().getHeight());
            hashMap.put(ff, dragonForFly);
            // Установка начальной позиции дракона
            dragonAnimated.getImageView().setTranslateX(dragonForFly.getX());
            dragonAnimated.getImageView().setTranslateY(dragonForFly.getY());
            dragonAnimated.getAnimation().setCycleCount(Animation.INDEFINITE);
            dragonAnimated.getAnimation().play();
            root.getChildren().add(dragonAnimated.getImageView());
            StackPane.setAlignment(dragonAnimated.getImageView(), Pos.CENTER);
            System.out.println(hashMap);
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
        String musicPath = Objects.requireNonNull(getClass().getResource("/music/Bomba.mp3")).toExternalForm();
        Media media = new Media(musicPath);
       mediaPlayer = new MediaPlayer(media);



        // Воспроизведение музыки при запуске окна
        mediaPlayer.play();
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setTitle("Dragon Animation");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            // Остановка анимации и сброс текущего кадра для каждого DragonAnimated
            for (DragonAnimated dragonAnimated : dragonAnimateds) {
                dragonAnimated.getAnimation().stop();

            }

            // Остановка воспроизведения музыки
            mediaPlayer.stop();
            currentFrameIndex = 0;
        });







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


