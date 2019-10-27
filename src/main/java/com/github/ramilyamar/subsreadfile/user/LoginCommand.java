package com.github.ramilyamar.subsreadfile.user;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginCommand {

    private final UserDao userDao;

    public LoginResult execute(Role[] currentRole, UserInfo[] currentUser, String[] tokens) {
        Option<UserInfo> userInfo = userDao.getUserInfoByName(tokens[1]);
        userInfo.onDefined(u -> {
            currentRole[0] = u.getRole();
            currentUser[0] = u;
        });

        return userInfo.map(u -> {
            if (PasswordUtils.verifyUserPassword(tokens[2], u.getEncryptedPassword())) {
                return LoginResult.SUCCESS;
            } else return LoginResult.WRONG_PASSWORD;
        }).getOrElse(LoginResult.USER_NOT_FOUND);
    }
}
