package com.github.ramilyamar.subsreadfile.file;

import com.github.ramilyamar.subsreadfile.db.TestDatabase;
import com.github.ramilyamar.subsreadfile.db.TestUtil;
import com.github.ramilyamar.subsreadfile.user.EncryptedPassword;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileDaoImplTest {

    private FileDaoImpl dao = new FileDaoImpl(TestDatabase.getInstance().getDatabase());
    private UserDaoImpl userDao = new UserDaoImpl(TestDatabase.getInstance().getDatabase());

    @Test
    void createFile() {
        String expectedName = TestUtil.uniqueString();
        long userId = userDao.createUser(expectedName, new EncryptedPassword("2", "2"));
        FileInfo expectedFileInfo = new FileInfo("2", userId, "2");
        long id = dao.createFile(expectedFileInfo);

        Option<FileInfo> fileInfoById = dao.getFileInfoById(id);

        assertEquals(Option.of(expectedFileInfo), fileInfoById);
    }

    @Test
    void getMoviesByUserId() {
        long userId = userDao.createUser(TestUtil.uniqueString(), new EncryptedPassword("2", "2"));
        String expectedMovieName = TestUtil.uniqueString();
        dao.createFile(new FileInfo("2", userId, expectedMovieName));

        List<MovieInfo> movies = dao.getMoviesByUserId(userId);

        assertEquals(1, movies.size());
        assertEquals(expectedMovieName, movies.get(0).getMovieName());
    }
}
