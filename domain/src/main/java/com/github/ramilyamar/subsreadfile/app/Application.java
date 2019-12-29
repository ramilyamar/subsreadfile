package com.github.ramilyamar.subsreadfile.app;

import com.github.ramilyamar.subsreadfile.file.MovieInfo;
import com.github.ramilyamar.subsreadfile.file.MoviesCommand;
import com.github.ramilyamar.subsreadfile.subs.AddCommand;
import com.github.ramilyamar.subsreadfile.user.*;
import com.github.ramilyamar.subsreadfile.word.WordInfo;
import com.github.ramilyamar.subsreadfile.word.WordsCommand;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The {@code Application} class reads commands of user from command line and provides result of commands.
 */
@AllArgsConstructor
public class Application {

    private final RegCommand regCommand;
    private final LoginCommand loginCommand;
    private final LogoutCommand logoutCommand;
    private final AddCommand addCommand;
    private final WordsCommand wordsCommand;
    private final MoviesCommand moviesCommand;
    private final CommandLineViewer clViewer;

    /**
     * Array keeps one current role.
     *
     * @implNote Array is workaround to use mutable variable inside lambda.
     */
    static final Role[] currentRole = new Role[]{Role.ANONYMOUS};

    /**
     * Array keeps one current user.
     *
     * @implNote Array is workaround to use mutable variable inside lambda.
     */
    static final UserInfo[] currentUser = new UserInfo[]{null};

    @SuppressWarnings("squid:S2189")
    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
                        regCommand.execute(tokens);
                        System.out.println(clViewer.createRegView());
                        break;
                    case LOGIN:
                        LoginResult result = loginCommand.execute(currentRole, currentUser, tokens);
                        System.out.println(clViewer.createLoginView(tokens[1], result));
                        break;
                    case LOGOUT:
                        logoutCommand.execute(currentRole, currentUser);
                        System.out.println(clViewer.createLogoutView());
                        break;
                    case EXIT:
                        System.out.println(clViewer.createExitView());
                        System.exit(0);
                        break;
                    case ADD:
                        long fileId = addCommand.execute(tokens[1], currentUser[0].getId(), tokens[2]);
                        System.out.println(clViewer.createAddView(fileId));
                        break;
                    case WORDS:
                        Collection<WordInfo> wordInfoList = wordsCommand.execute(currentUser, tokens);
                        System.out.println(clViewer.createWordsView(wordInfoList));
                        break;
                    case MOVIES:
                        List<MovieInfo> movieInfoList = moviesCommand.execute(currentUser[0]);
                        System.out.println(clViewer.createMoviesView(movieInfoList));
                        break;
                    case USERS:
                        break;
                }
            }).onEmpty(() -> System.out.println("Нет такой команды: " + commandText));
        }
    }
}
