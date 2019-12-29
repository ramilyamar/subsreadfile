package com.github.ramilyamar.subsreadfile.app;

import com.github.ramilyamar.subsreadfile.file.MovieInfo;
import com.github.ramilyamar.subsreadfile.user.LoginResult;
import com.github.ramilyamar.subsreadfile.word.WordInfo;

import java.util.Collection;
import java.util.List;

/**
 * The {@code Viewer} interface provides methods for creating messages for user about result of user's command.
 */
public interface Viewer {

    /**
     * Creates message for user about result of registration.
     *
     * @return string with message for user about result of registration
     */
    String createRegView();

    /**
     * Creates message for user about result of authentication.
     *
     * @param name   name of user
     * @param result enum LoginResult (result of authentication)
     * @return string with message for user about result of authentication
     */
    String createLoginView(String name, LoginResult result);

    /**
     * Creates message for user about result of logout.
     *
     * @return string with message for user about result of logout
     */
    String createLogoutView();

    /**
     * Creates message for user about result of exit from program.
     *
     * @return string with message for user about result of exit from program
     */
    String createExitView();

    /**
     * Creates message for user about result of loading subs file.
     *
     * @param fileId id of subs file
     * @return string with message for user about result of loading subs file
     */
    String createAddView(long fileId);

    /**
     * Creates word view.
     *
     * @param wordInfoList collection with words of user
     * @return string representation of user's words list
     */
    String createWordsView(Collection<WordInfo> wordInfoList);

    /**
     * Creates movie view.
     *
     * @param movieInfoList collection with movies of user
     * @return string representation of user's movies list
     */
    String createMoviesView(List<MovieInfo> movieInfoList);
}
