package com.github.ramilyamar.subsreadfile.db;

import lombok.Getter;

public class TestDatabase {

    private static TestDatabase instance = new TestDatabase();

    @Getter
    private Database database;

    private TestDatabase() {
        database = new DatabaseImpl(DbProperties.read());
        MigrationUtil.createTables(database);
    }

    public static TestDatabase getInstance() {
        return instance;
    }
}
