package com.example.lab8.Command;


import com.example.lab8.DataBase.Users;

public class CommandAutorizate implements Command{
    private final Users users;

    public CommandAutorizate(Users users) {
        this.users = users;
    }

    @Override
    public void execute() {
    users.startAuthentication();
    }

    @Override
    public String getDescription() {
        return "Команда позволяет авторизоваться в программе";
    }
}
