package com.github.ramilyamar.subsreadfile.user;

import io.vavr.control.Option;

public interface UserDao {

    /**
     * Creates and saves user to storage
     *
     * @param name name of user to create
     * @param encryptedPassword encrypted password
     * @return id of created user
     *
     * @see PasswordUtils
     */
    long createUser(String name, EncryptedPassword encryptedPassword);

    /**
     * Returns name of user by id
     *
     * @param id id of user
     * @return name of user by id or {@link Option#none} if user with this id is not found
     */
    Option<String> getUserNameById(long id);
}
