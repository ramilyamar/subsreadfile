package com.github.ramilyamar.subsreadfile.user;

import com.github.ramilyamar.subsreadfile.app.Command;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@AllArgsConstructor
public enum Role {

    USER(1, RoleConstants.userCommands),
    ADMIN(2, ArrayUtils.addAll(RoleConstants.userCommands, Command.USERS)),
    ANONYMOUS(3, new Command[]{Command.LOGIN, Command.REG, Command.EXIT});

    private final int id;
    // TODO: 25.05.2019 make unmodifiable
    private final Command[] availableCommands;

    static Option<Role> fromInt(int roleId) {
        for (Role role : Role.values()) {
            if (role.id == roleId) {
                return Option.some(role);
            }
        }
        return Option.none();
    }

    interface RoleConstants {
        Command[] userCommands = new Command[]{
                Command.ADD, Command.WORDS, Command.LOGOUT, Command.EXIT
        };
    }
}
