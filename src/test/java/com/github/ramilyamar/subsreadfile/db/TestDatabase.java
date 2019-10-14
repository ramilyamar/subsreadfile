package com.github.ramilyamar.subsreadfile.db;

import lombok.Getter;

public class TestDatabase {

    private static TestDatabase instance = new TestDatabase();

    @Getter
    private Database database;

    private TestDatabase() {
        database = new Database();
        database.executeUpdate("create table IF NOT EXISTS users " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "salt VARCHAR(30) NOT NULL, " +
                "role TINYINT NOT NULL, " +
                "PRIMARY KEY (id))");

        database.executeUpdate("create table IF NOT EXISTS files " +
                "(" +
                "id INTEGER NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "userId INTEGER NOT NULL, " +
                "movieName VARCHAR(255), " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (userId) REFERENCES users(id)" +
                ")");

        database.executeUpdate("create table IF NOT EXISTS words " +
                "(" +
                "id INTEGER NOT NULL AUTO_INCREMENT, " +
                "word VARCHAR(255) NOT NULL, " +
                "translations VARCHAR(255) NOT NULL, " +
                "userId INTEGER NOT NULL, " +
                "learningStatus TINYINT NOT NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (userId) REFERENCES users(id)" +
                ")");

        database.executeUpdate("create table IF NOT EXISTS movieWordLink " +
                "(" +
                "wordId INTEGER NOT NULL AUTO_INCREMENT, " +
                "fileId INTEGER NOT NULL, " +
                "PRIMARY KEY (wordId), " +
                "FOREIGN KEY (fileId) REFERENCES words(id)" +
                ")");
    }

    public static TestDatabase getInstance() {
        return instance;
    }
}
