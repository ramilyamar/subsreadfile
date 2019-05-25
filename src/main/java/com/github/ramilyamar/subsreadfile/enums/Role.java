package com.github.ramilyamar.subsreadfile.enums;

import org.apache.commons.lang3.ArrayUtils;

public enum Role {

    ADMIN(ArrayUtils.addAll(RoleConstants.userCommands, Command.USERS)),
    USER(RoleConstants.userCommands),
    ANONYMOUS(new Command[]{Command.LOGIN, Command.REG, Command.EXIT});

    private Command[] availableCommands;

    Role(Command[] availableCommands) {
        this.availableCommands = availableCommands;
    }

    public Command[] getAvailableCommands() {
        // TODO: 25.05.2019 return smth unmodifiable
        return availableCommands;
    }

    interface RoleConstants {
        Command[] userCommands = new Command[]{
                Command.ADD, Command.WORDS, Command.LOGOUT, Command.EXIT
        };
    }
}