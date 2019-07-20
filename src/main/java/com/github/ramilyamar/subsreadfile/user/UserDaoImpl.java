package com.github.ramilyamar.subsreadfile.user;

import com.github.ramilyamar.subsreadfile.db.Database;
import io.vavr.control.Option;

public class UserDaoImpl implements UserDao {

    private Database database;

    public UserDaoImpl(Database database) {
        this.database = database;
    }

    private static final String INSERT_SQL = "insert into users (name, password, salt) values (?, ?, ?)";

    @Override
    public long createUser(String name, EncryptedPassword encryptedPassword) {
        return database.insert(INSERT_SQL, name, encryptedPassword.getPassword(), encryptedPassword.getSalt());
    }

    @Override
    public Option<String> getUserNameById(long id) {
        return database.getString("SELECT name from users where id = ?", String.valueOf(id));
    }
}
