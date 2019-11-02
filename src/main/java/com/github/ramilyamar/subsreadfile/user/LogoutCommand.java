package com.github.ramilyamar.subsreadfile.user;

public class LogoutCommand {

    /**
     * Converts current role to ANONYMOUS and current user to null after logout.
     *
     * @param currentRole current role of user
     * @param currentUser current user
     */
    public void execute(Role[] currentRole, UserInfo[] currentUser) {
        currentRole[0] = Role.ANONYMOUS;
        currentUser[0] = null;
    }
}
