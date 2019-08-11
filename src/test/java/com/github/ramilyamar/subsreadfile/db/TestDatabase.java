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
    }

    public static TestDatabase getInstance() {
        return instance;
    }
}
