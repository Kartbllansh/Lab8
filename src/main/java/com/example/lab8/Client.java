package com.example.lab8;

import com.example.lab8.Apps.App;
import com.example.lab8.DataBase.SetCollection;

public class Client {
    public static void main(String[] args){
        SetCollection.getDragonsFromDB();
        App.go();
    }
}
