package com.github.ramilyamar.subsreadfile.app;

import com.github.ramilyamar.subsreadfile.devutil.NeedsRefactoring;
import com.github.ramilyamar.subsreadfile.subs.SubsLoader;
import com.github.ramilyamar.subsreadfile.user.PasswordUtils;
import com.github.ramilyamar.subsreadfile.user.Role;
import com.github.ramilyamar.subsreadfile.user.UserDao;
import com.github.ramilyamar.subsreadfile.user.UserInfo;
import io.vavr.control.Option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Application {

    private SubsLoader subsLoader;
    private UserDao userDao;

    public Application(SubsLoader subsLoader, UserDao userDao) {
        this.subsLoader = subsLoader;
        this.userDao = userDao;
    }

    @SuppressWarnings("squid:S2189")
    @NeedsRefactoring("S2T1")
    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final Role[] currentRole = new Role[]{Role.ANONYMOUS};

        while (true) {
            String fullCommand = reader.readLine();
            String[] tokens = fullCommand.split(" ");
            String commandText = tokens[0];

            Option<Command> commandOption = Command.fromString(commandText);

            commandOption.onDefined(command -> {
                if (Arrays.stream(currentRole[0].getAvailableCommands()).noneMatch(c -> c.equals(command))) {
                    System.out.println("Недоступная команда");
                    return;
                }
                switch (command) {
                    case ADD:
                        subsLoader.load(tokens[1]);
                        break;
                    case LOGIN:
                        Option<UserInfo> userInfo = performLogin(tokens[1], tokens[2]);
                        userInfo.onDefined(u -> currentRole[0] = u.getRole());
                        break;
                    case REG:
                        userDao.createUser(tokens[1], PasswordUtils.encryptPassword(tokens[2]));
                        greeting(tokens[1]);
                        break;
                    case LOGOUT:
                        System.out.println("До новых встреч!");
                        currentRole[0] = Role.ANONYMOUS;
                        break;
                    case EXIT:
                        System.exit(0);
                }
            }).onEmpty(() -> System.out.println("Нет такой команды: " + commandText));
        }
    }

    private Option<UserInfo> performLogin(String username, String password) {
        return userDao.getUserInfoByName(username)
                .onDefined(userInfo -> {
                    if (PasswordUtils.verifyUserPassword(password, userInfo.getEncryptedPassword()))
                        greeting(username);
                    else System.out.println("Неверный пароль");
                }).onEmpty(() -> System.out.println("Пользователь не найден"));
    }

    private void greeting(String name) {
        System.out.println("Добро пожаловать, " + name + "!");
    }
}
