package com.github.ramilyamar.subsreadfile.user;

import lombok.AllArgsConstructor;

/**
 * The {@code RegCommand} class provides method for creating and saving user to storage during registration.
 * This class is needed to execute the command {@link com.github.ramilyamar.subsreadfile.app.Command#REG}.
 */
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
