package com.github.ramilyamar.subsreadfile.db;

public interface UserDao {

    /**
     *
     * @return id of created user
     */
    long createUser(String name);
}
