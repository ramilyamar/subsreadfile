package com.github.ramilyamar.subsreadfile.db;

public class MigrationUtil {
    public static void createTables(Database database) {
// TODO: 11.01.2020 use some production-ready migration tool
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
                "wordId INTEGER NOT NULL, " +
                "fileId INTEGER NOT NULL, " +
                "PRIMARY KEY (wordId, fileId), " +
                "FOREIGN KEY (wordId) REFERENCES words(id), " +
                "FOREIGN KEY (fileId) REFERENCES files(id)" +
                ")");
    }
}
