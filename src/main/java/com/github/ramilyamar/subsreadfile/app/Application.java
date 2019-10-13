package com.github.ramilyamar.subsreadfile.app;

import com.github.ramilyamar.subsreadfile.devutil.NeedsRefactoring;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.subs.SubsLoader;
import com.github.ramilyamar.subsreadfile.user.PasswordUtils;
import com.github.ramilyamar.subsreadfile.user.Role;
import com.github.ramilyamar.subsreadfile.user.UserDao;
import com.github.ramilyamar.subsreadfile.user.UserInfo;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordInfo;
import io.vavr.control.Option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    private final SubsLoader subsLoader;
    private final UserDao userDao;
    private final FileDao fileDao;
    private final WordDao wordDao;

    public Application(SubsLoader subsLoader, UserDao userDao, FileDao fileDao, WordDao wordDao) {
        this.subsLoader = subsLoader;
        this.userDao = userDao;
        this.fileDao = fileDao;
        this.wordDao = wordDao;
    }

    @SuppressWarnings("squid:S2189")
    @NeedsRefactoring("S2T1")
    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final Role[] currentRole = new Role[]{Role.ANONYMOUS};
        final UserInfo[] currentUser = new UserInfo[]{null};

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
                    case REG:
                        userDao.createUser(tokens[1], PasswordUtils.encryptPassword(tokens[2]));
                        greeting(tokens[1]);
                        break;
                    case LOGIN:
                        Option<UserInfo> userInfo = performLogin(tokens[1], tokens[2]);
                        userInfo.onDefined(u -> {
                            currentRole[0] = u.getRole();
                            currentUser[0] = u;
                        });
                        break;
                    case LOGOUT:
                        System.out.println("До новых встреч!");
                        currentRole[0] = Role.ANONYMOUS;
                        currentUser[0] = null;
                        break;
                    case EXIT:
                        System.exit(0);
                        break;
                    case ADD:
                        subsLoader.load(tokens[1], currentUser[0].getId(), tokens[2]);
                        break;
                    case WORDS:
                        if (tokens.length == 1) {
                            List<WordInfo> wordsByUserId = wordDao.getWordsByUserId(currentUser[0].getId());
                            Map<String, WordInfo> uniqueWords = new HashMap<>();

                            wordsByUserId.forEach(wordInfo ->
                                    uniqueWords.put(wordInfo.getWord(), wordInfo)
                            );
                            uniqueWords.values().forEach(wordInfo ->
                                    System.out.println(wordInfo.getWord() + " - " + wordInfo.getTranslations())
                            );
                        } else {
                            wordDao.getWordsFromMovieByUserId(Long.parseLong(tokens[1]), currentUser[0].getId())
                                    .forEach(wordInfo ->
                                            System.out.println(wordInfo.getWord() + " - " + wordInfo.getTranslations())
                                    );
                        }

                        break;
                    case MOVIES:
                        fileDao.getMoviesByUserId(currentUser[0].getId()).forEach(movieInfo ->
                                System.out.println(movieInfo.getFileId() + ": " + movieInfo.getMovieName())
                        );
                        break;
                    case USERS:
                        break;
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
