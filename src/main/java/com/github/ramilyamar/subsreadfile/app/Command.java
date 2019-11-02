package com.github.ramilyamar.subsreadfile.app;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;

/**
 * The constants of {@code Command} enum provide a list of the commands.
 */
@AllArgsConstructor
public enum Command {
    // auth
    REG("reg"),
    LOGIN("login"),
    LOGOUT("logout"),
    EXIT("exit"),

    // movies
    ADD("add"),
    WORDS("words"),
    MOVIES("movies"),

    // service
    USERS("users");

    public final String commandName;

    public static Option<Command> fromString(String commandName) {
        for (Command command : Command.values()) {
            if (command.commandName.equals(commandName)) {
                return Option.of(command);
            }
        }
        return Option.none();
    }
}
