package com.github.ramilyamar.subsreadfile.app;

import io.vavr.control.Option;

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

    public static Option<Command> fromString(String commandName) {
        for (Command command : Command.values()) {
            if (command.commandName.equals(commandName)) {
                return Option.of(command);
            }
        }
        return Option.none();
    }
}