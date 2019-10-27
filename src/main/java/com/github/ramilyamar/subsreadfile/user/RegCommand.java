package com.github.ramilyamar.subsreadfile.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegCommand {

    private final UserDao userDao;

    public long execute(String[] tokens) {
        return userDao.createUser(tokens[1], PasswordUtils.encryptPassword(tokens[2]));
    }
}
