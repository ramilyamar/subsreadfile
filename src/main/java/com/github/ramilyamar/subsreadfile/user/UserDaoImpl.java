package com.github.ramilyamar.subsreadfile.user;

import com.github.ramilyamar.subsreadfile.db.Database;
import io.vavr.control.Option;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private Database database;

    public UserDaoImpl(Database database) {
        this.database = database;
    }

    private static final String INSERT_SQL = "INSERT INTO users (name, password, salt, role) VALUES (?, ?, ?, ?)";

    @Override
    public long createUser(String name, EncryptedPassword encryptedPassword) {
        return database.insertAndGetId(INSERT_SQL, name, encryptedPassword.getPassword(),
                encryptedPassword.getSalt(), Role.USER.getId());
    }

    @Override
    public Option<String> getUserNameById(long id) {
        return database.getString("SELECT name FROM users WHERE id = ?", String.valueOf(id));
    }

    @Override
    public Option<UserInfo> getUserInfoByName(String name) {
        String sql = "SELECT id, password, salt, role FROM users WHERE name = '" + name + "'";
        ResultSet resultSet = database.executeQuery(sql);
        try {
            if (!resultSet.next()) {
                return Option.none();
            }
            long id = resultSet.getLong("id");
            EncryptedPassword encryptedPassword = new EncryptedPassword(
                    resultSet.getString("password"),
                    resultSet.getString("salt")
            );
            Role role = Role.fromInt(resultSet.getInt("role"))
                    .getOrElseThrow(() -> new RuntimeException("Не найдена роль"));
            return Option.of(new UserInfo(id, role, encryptedPassword));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
