package com.github.ramilyamar.subsreadfile.user;

public class LogoutCommand {

    public void execute(Role[] currentRole, UserInfo[] currentUser) {
        currentRole[0] = Role.ANONYMOUS;
        currentUser[0] = null;
    }
}
