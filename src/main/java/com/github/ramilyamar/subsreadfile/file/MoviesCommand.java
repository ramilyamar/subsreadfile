package com.github.ramilyamar.subsreadfile.file;

import com.github.ramilyamar.subsreadfile.user.UserInfo;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MoviesCommand {

    private final FileDao fileDao;

    /**
     * Returns user's movie list.
     *
     * @param userInfo information about user
     * @return user's movie list
     */
    public List<MovieInfo> execute(UserInfo userInfo) {
        return fileDao.getMoviesByUserId(userInfo.getId());
    }
}
