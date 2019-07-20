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

    /**
     * Returns encrypted password from storage by username
     *
     * @param name name of user
     * @return encrypted password or {@link Option#none} if user with this name is not found
     */
    Option<EncryptedPassword> getEncryptedPassword(String name);
}
