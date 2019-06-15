package com.github.ramilyamar.subsreadfile.enums;

import java.util.Optional;

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
    
    public static Optional<Command> fromString(String commandName) {
        for (Command command : Command.values()) {
            if (command.commandName.equals(commandName)) {
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }
}
