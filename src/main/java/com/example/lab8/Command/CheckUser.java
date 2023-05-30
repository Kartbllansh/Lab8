package com.example.lab8.Command;


import com.example.lab8.DataBase.Users;

public class CheckUser {
    public static boolean checkUsers(){
        return Users.getCurrentUser() != null;
    }
}
