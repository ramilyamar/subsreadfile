package com.github.ramilyamar.subsreadfile.user;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;

/**
 * The {@code LoginCommand} class provides method for performing authentication.
 * This class is needed to execute the command {@link com.github.ramilyamar.subsreadfile.app.Command#LOGIN}.
 */
@AllArgsConstructor
public class LoginCommand {

    private final UserDao userDao;

    /**
     * Performs authentication and changes current role and current user to current state.
     *
     * @param currentRole current role
     * @param currentUser current user
     * @param tokens      array of parts of entered command split by space
     * @return {@link LoginResult#SUCCESS} if login and password are correct, or {@link LoginResult#WRONG_PASSWORD} if
     * password is wrong, or {@link LoginResult#USER_NOT_FOUND} if user does not exist in database
     */
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
