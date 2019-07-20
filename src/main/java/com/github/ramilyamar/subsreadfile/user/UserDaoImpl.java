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

    @Override
    public Option<EncryptedPassword> getEncryptedPassword(String name) {
        Option<String> optionPassword = getPassword(name);
        return optionPassword.flatMap(password -> {
                    Option<String> optionSalt = getSalt(name);
                    return optionSalt.map(salt ->
                            new EncryptedPassword(password, salt)
                    );
                }
        );
    }

    private Option<String> getSalt(String name) {
        return database.getString("SELECT salt from users where name = ?", String.valueOf(name));
    }

    private Option<String> getPassword(String name) {
        return database.getString("SELECT password from users where name = ?", String.valueOf(name));
    }
}
