package com.example.lab8;

import com.example.lab8.Animation.DragonAnimation;
import com.example.lab8.Apps.App;
import com.example.lab8.DataBase.SetCollection;
import javafx.application.Application;

public class Client {
    public static void main(String[] args){
        SetCollection.getDragonsFromDB();
        App.go();
    }
}
