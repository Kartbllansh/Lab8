package com.example.lab8.Command;

/**
 * Interface
 */
public interface CommandWithArguments extends Command{
    /**
     * Метод для чтения аргументов
     *
     * @param arg the arg
     */
    void getArguments(String[] arg);
}
