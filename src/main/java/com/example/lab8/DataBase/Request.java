package com.example.lab8.DataBase;

/**
 * Енам с основнымми запросами
 */
public enum Request {

    /**
     * The Istabledragons.
     */
    ISTABLEDRAGONS("SELECT * FROM DRAGONS;"),
    /**
     * The Createdragons.
     */
    CREATEDRAGONS("CREATE TABLE IF NOT EXISTS DRAGONS ( id BIGINT PRIMARY KEY, name TEXT NOT NULL, age BIGINT CHECK(age>0), weight BIGINT CHECK(weight>0), color TEXT NOT NULL, type TEXT NOT NULL, creationDate TEXT NOT NULL, x FLOAT CHECK(x>-474) NOT NULL, y FLOAT NOT NULL, size DOUBLE CHECK(size>0) NOT NULL, eyesCount BIGINT CHECK(eyesCount>=0) NOT NULL, toothCount BIGINT CHECK(toothCount>=0) NOT NULL, creator text NOT NULL )"),
    /**
     * The Istableuser.
     */
    ISTABLEUSER("SELECT * FROM USERS"),
    /**
     * The Createdsequence.
     */
    CREATEDSEQUENCE("CREATE SEQUENCE IF NOT EXISTS DRAGONSEQ START WITH 1 OWNED BY DRAGONS.id"),
    /**
     * The Searchlogin.
     */
    SEARCHLOGIN("SELECT login FROM USERS"),
    /**
     * The Createdusers.
     */
    CREATEDUSERS("CREATE TABLE IF NOT EXISTS USERS( login text NOT NULL, hash text NOT NULL, salt text NOT NULL, dataAut DATETIME NOT NULL)");

    /**
     * The String value.
     */
    final String stringValue;

    Request(String value) {
        stringValue = value;
    }

    /**
     * Gets string value.
     *
     * @return the string value
     */
    public String getStringValue() {
        return stringValue;
    }


}
