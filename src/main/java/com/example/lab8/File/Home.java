package com.example.lab8.File;


import com.example.lab8.Command.InvokerCommand;
import com.example.lab8.DataBase.MainDataBase;
import com.example.lab8.DataBase.SetCollection;
import com.example.lab8.File.CollectionManager;
import com.example.lab8.IO.IOUser;

import java.util.NoSuchElementException;

public class Home {
    CollectionManager collection;
    IOUser ioUser;
    InvokerCommand invokerCommand;


    //уведомление что вы гость
    public void start(){
        collection = new CollectionManager();
        ioUser = new IOUser();
        invokerCommand = new InvokerCommand(collection, ioUser );
        MainDataBase.CreateTables();
        SetCollection.getDragonsFromDB();
        try {
            cycle();
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        }
    }

    /**
     * Переменная подсказка для первого запуска
     */
    int clue = 0;

    /**
     * Метод, выполняющий циклическое чтение команд из строки ввода
     * @see IOUser#printText(String)
     * @see IOUser#readCommand()
     * @see InvokerCommand#execute(String)
     */
    public void cycle() {
        ioUser.printText("Программа была запущена.\n");
        while (true) {
            ioUser.printText("\nВведите название команды:\n");
            if(clue ==0){
                System.out.println("Введите >help< чтобы увидеть список комvанд");
                clue = 3;
            }
            System.out.print(">>");
            String line = ioUser.readCommand();
            invokerCommand.execute(line);

        }
    }
}



