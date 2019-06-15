package com.github.ramilyamar.subsreadfile.db;

public class UserDaoImpl implements UserDao {

    private Database database;

    public UserDaoImpl(Database database) {
        this.database = database;
    }

    private static final String INSERT_SQL = "insert into users (name, password) values (?, ?)";

    @Override
    public long createUser(String name) {
        return database.insert(INSERT_SQL, name, "kjnhubgytghuj");
    }
}
