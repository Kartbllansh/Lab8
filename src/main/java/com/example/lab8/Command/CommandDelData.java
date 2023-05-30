package com.example.lab8.Command;



import com.example.lab8.File.CollectionManager;

import java.util.Objects;
import java.util.Scanner;

public class CommandDelData implements Command{
   CollectionManager collectionManager;
    public CommandDelData(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        System.out.println("Введите пароль для данной команды");
        Scanner scanner = new Scanner(System.in);
        String ff = scanner.nextLine();
        if(Objects.equals(ff, "strela")) {
            collectionManager.clearData();
        }else {
            System.out.println("Неправильный пароль");
        }

    }
}
