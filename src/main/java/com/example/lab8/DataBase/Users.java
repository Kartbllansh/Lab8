package com.example.lab8.DataBase;

import com.example.lab8.Apps.Edition;
import com.example.lab8.Command.InvokerCommand;
import com.example.lab8.File.CollectionManager;
import com.example.lab8.MyException.NotIdException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Класс, работающий с пользователями
 */
public class Users {

    public static boolean aut = true;
    public static boolean reg = true;
    /**
     * Метод, устанавливающий пользователя работающего с коллекцией в данный момент
     *
     * @param currentUser the current user
     */
    public static void setCurrentUser(String currentUser) {
        Users.currentUser = currentUser;
    }

    /**
     * Поле, хранящее ссылку на объект
     */
    public static ZonedDateTime time;
    private static CollectionManager collectionManager = new CollectionManager();
    /**
     * Поле, хранящее информацию о пользователе
     */
    private static String currentUser = null;

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public static String getCurrentUser() {
        return currentUser;
    }


    /**
     * Метод, запускающий авторизацию в программе
     */
    public void startAuthentication(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя");
        String login = scanner.nextLine();
        //System.out.println(MainDataBase.compareLogin());
        boolean decision = MainDataBase.checkLogin(login);
        if(decision){
            System.out.println("Пользователь с таким логином существует");
            System.out.println("Войдите в аккаунт "+login);
            //


        } else {
            System.out.println("Пользователя с таким логином не существует");
            System.out.println("Зарегистрируйте аккаунт "+login);

        }

    }
    public static int count = 5;

    /**
     * Метод, позволяющий войти в аккаунт
     *
     * @param login   the login

     */
    public static void enter(String login, String passwd){

        try {

        if(count==0){
        System.exit(0);
        }
        while(count > 0){
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            ResultSet resultSetSalt = MainDataBase.requestSQLWith("SELECT salt FROM USERS WHERE login = ?", login);
            assert resultSetSalt != null;
            resultSetSalt.next();
            String salt = resultSetSalt.getString(1);
            ResultSet resultSetHash = MainDataBase.requestSQLWith("SELECT hash FROM USERS WHERE login = ?", login);
            assert resultSetHash != null;
            resultSetHash.next();
            byte[] hash = md.digest(("*63&^mVLC(#" + passwd.trim() + salt).getBytes(StandardCharsets.UTF_8));
            if(Arrays.toString(hash).equals(resultSetHash.getString(1))){
                //count = -10;
                aut=true;
                System.out.println("Добро пожаловать, "+login);
                collectionManager.show();
                currentUser = login;
                time = ZonedDateTime.now();
                count =5;
                return;

            } else {
                Edition.showAlert("Ошибка", "Неверный пароль. Осталось попыток: "+(--count), "Ошибка при авторизации" );
                //System.out.println("Неверный пароль. Осталось попыток: "+(--count));
                aut = false;
                break;

            }

        }

        } catch (SQLException | NullPointerException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Метод, позволяющий зарегестрировать аккаунт
     *
     * @param login   the login

     */
    public static void registration(String login, String password, String dPaswwrd) {

            if (!password.equals(dPaswwrd)) {
                Edition.showAlert("Ошибка", "Введенные пароли не совпадают", "Ошибка при авторизации");
                //System.out.println("Пароли не совпадают. Повторите попытку. Их осталось ");
                reg = false;
                return;
            }
        if (reg) {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("SHA-512");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            String salt = SaltGenerate.saltGetter();
            byte[] hash = md.digest(("*63&^mVLC(#" + password + salt).getBytes(StandardCharsets.UTF_8));
            //MainDataBase.requestSQLWithout("INSERT INTO USERS (login, hash, salt) VALUES (?, ?, ?)", login, Arrays.toString(hash), salt);
            MainDataBase.requestSQLWithout("INSERT INTO USERS (login, hash, salt) VALUES ('" + login + "', '" + Arrays.toString(hash) + "', '" + salt + "')");
            System.out.println("Вы успешно прошли регистрацию");
            currentUser = login;

        }
    }
}
