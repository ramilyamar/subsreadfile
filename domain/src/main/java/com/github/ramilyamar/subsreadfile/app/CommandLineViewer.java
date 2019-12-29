package com.github.ramilyamar.subsreadfile.app;

import com.github.ramilyamar.subsreadfile.file.MovieInfo;
import com.github.ramilyamar.subsreadfile.user.LoginResult;
import com.github.ramilyamar.subsreadfile.word.WordInfo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code CommandLineViewer} class provides methods for creating messages for user about result of user's command
 * in the command line.
 */
public class CommandLineViewer implements Viewer {

    @Override
    public String createRegView() {
        return "Регистрация прошла успешно.";
    }

    @Override
    public String createLoginView(String name, LoginResult result) {
        switch (result) {
            case SUCCESS:
                return "Добро пожаловать, " + name + "!";
            case WRONG_PASSWORD:
                return "Неверный пароль.";
            case USER_NOT_FOUND:
                return "Пользователь не найден.";
            default:
                throw new RuntimeException("Неизвестный результат.");
        }
    }

    @Override
    public String createLogoutView() {
        return "До новых встреч!";
    }

    @Override
    public String createExitView() {
        return "Программа завершается.";
    }

    @Override
    public String createAddView(long fileId) {
        return "Субтитры загружены. ID файла: " + fileId + ".";
    }

    @Override
    public String createWordsView(Collection<WordInfo> wordInfoList) {
        return wordInfoList
                .stream()
                .map(w -> w.getWord() + " - " +
                        w.getTranslations() + " (" +
                        w.getLearningStatus().toString().toLowerCase() + ")"
                ).collect(Collectors.joining("\n"));
    }

    @Override
    public String createMoviesView(List<MovieInfo> movieInfoList) {
        return movieInfoList
                .stream()
                .map(m -> m.getFileId() + ": " + m.getMovieName())
                .collect(Collectors.joining("\n"));
    }
}
