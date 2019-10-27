package com.github.ramilyamar.subsreadfile.file;

import com.github.ramilyamar.subsreadfile.user.UserInfo;

import java.util.List;

public class MoviesCommand {

    private final FileDao fileDao;

    public MoviesCommand(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public List<MovieInfo> execute(UserInfo userInfo) {
        return fileDao.getMoviesByUserId(userInfo.getId());
    }
}
