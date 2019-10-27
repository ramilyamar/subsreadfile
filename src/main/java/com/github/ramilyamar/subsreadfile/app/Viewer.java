package com.github.ramilyamar.subsreadfile.app;

import com.github.ramilyamar.subsreadfile.file.MovieInfo;
import com.github.ramilyamar.subsreadfile.user.LoginResult;
import com.github.ramilyamar.subsreadfile.word.WordInfo;

import java.util.Collection;
import java.util.List;

public interface Viewer {

    String createRegView();

    String createLoginView(String name, LoginResult result);

    String createLogoutView();

    String createExitView();

    String createAddView(long fileId);

    String createWordView(Collection<WordInfo> wordInfoList);

    String createMovieView(List<MovieInfo> movieInfoList);
}
