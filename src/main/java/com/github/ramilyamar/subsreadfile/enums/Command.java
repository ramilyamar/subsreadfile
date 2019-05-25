package com.github.ramilyamar.subsreadfile.enums;

public enum Command {
    ADD("add"),
    WORDS("words"),
    LOGIN("login"),
    REG("reg"),
    LOGOUT("logout"),
    USERS("users"),
    EXIT("exit");

    public final String commandName;

    Command(String commandName) {
        this.commandName = commandName;
    }
}
