package com.github.ramilyamar.subsreadfile.app;

import com.github.ramilyamar.subsreadfile.subs.SubsLoader;
import com.github.ramilyamar.subsreadfile.user.PasswordUtils;
import com.github.ramilyamar.subsreadfile.user.UserDao;
import io.vavr.control.Option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private SubsLoader subsLoader;
    private UserDao userDao;

    public Application(SubsLoader subsLoader, UserDao userDao) {
        this.subsLoader = subsLoader;
        this.userDao = userDao;
    }

    @SuppressWarnings("squid:S2189")
    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String fullCommand = reader.readLine();
            String[] tokens = fullCommand.split(" ");
            String commandText = tokens[0];

            Option<Command> commandOption = Command.fromString(commandText);

            commandOption.onDefined(command -> {
                switch (command) {
                    case ADD:
                        subsLoader.load(tokens[1]);
                        break;
                    case REG:
                        userDao.createUser(tokens[1], PasswordUtils.encryptPassword(tokens[2]));
                        System.out.println("Добро пожаловать, " + tokens[1] + "!");
                        break;
                    case EXIT:
                        System.exit(0);
                }
            }).onEmpty(() -> System.out.println("Нет такой команды: " + commandText));
        }
    }
}
