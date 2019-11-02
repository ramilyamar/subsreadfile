package com.github.ramilyamar.subsreadfile.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegCommand {

    private final UserDao userDao;

    /**
     * Creates and saves user to storage during registration.
     *
     * @param tokens parts of command of user in the command line
     * @return id of created user
     */
    public long execute(String[] tokens) {
        return userDao.createUser(tokens[1], PasswordUtils.encryptPassword(tokens[2]));
    }
}
