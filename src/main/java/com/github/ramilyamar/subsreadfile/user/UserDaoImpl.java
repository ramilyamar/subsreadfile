package com.github.ramilyamar.subsreadfile.user;

import com.github.ramilyamar.subsreadfile.db.Database;
import io.vavr.control.Option;

public class UserDaoImpl implements UserDao {

    private static final String GET_ROLE_SQL = "SELECT role from users where name = ?";
    private Database database;

    public UserDaoImpl(Database database) {
        this.database = database;
    }

    private static final String INSERT_SQL = "insert into users (name, password, salt, role) values (?, ?, ?, ?)";

    @Override
    public long createUser(String name, EncryptedPassword encryptedPassword) {
        return database.insert(INSERT_SQL, name, encryptedPassword.getPassword(),
                encryptedPassword.getSalt(), Role.USER.getId());
    }

    @Override
    public Option<String> getUserNameById(long id) {
        return database.getString("SELECT name from users where id = ?", String.valueOf(id));
    }

    @Override
    public Option<UserInfo> getUserInfoByName(String name) {
        return getRole(name).flatMap(role ->
                getEncryptedPassword(name).map(encryptedPassword ->
                        new UserInfo(role, encryptedPassword)
                )
        );
    }

    /**
     * Returns encrypted password from storage by username
     *
     * @param name name of user
     * @return encrypted password or {@link Option#none} if user with this name is not found
     */
    private Option<EncryptedPassword> getEncryptedPassword(String name) {
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

    private Option<Role> getRole(String name) {
        Option<Integer> roleId = database.getInt(GET_ROLE_SQL, String.valueOf(name));
        return roleId.flatMap(Role::fromInt);
    }
}
