package com.github.ramilyamar.subsreadfile.subs;

import com.github.ramilyamar.subsreadfile.Main;
import com.github.ramilyamar.subsreadfile.db.TestDatabase;
import com.github.ramilyamar.subsreadfile.dict.Dictionary;
import com.github.ramilyamar.subsreadfile.dict.SimpleDictionaryParser;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.file.FileDaoImpl;
import com.github.ramilyamar.subsreadfile.file.FileInfo;
import com.github.ramilyamar.subsreadfile.user.EncryptedPassword;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordDaoImpl;
import com.github.ramilyamar.subsreadfile.word.WordInfo;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SubsLoaderTest {
    private static String file = "src/test/resources/example.srt";
    private UserDaoImpl userDao = new UserDaoImpl(TestDatabase.getInstance().getDatabase());
    private WordsExtractor wordsExtractor = new SimpleWordsExtractor();
    private Dictionary dictionary = new SimpleDictionaryParser()
            .parse(new File(Main.defaultDictionary));
    private FileDao fileDao = new FileDaoImpl(TestDatabase.getInstance().getDatabase());
    private WordDao wordDao = new WordDaoImpl(TestDatabase.getInstance().getDatabase());

    private SubsLoader subsLoader = new SubsLoader(wordsExtractor, dictionary, fileDao, wordDao);

    @Test
    void load() {
        long userId = userDao.createUser("Name", new EncryptedPassword("2", "2"));
        long fileId = subsLoader.load(file, userId, "HP");

        Option<FileInfo> createdFile = fileDao.getFileInfoById(fileId);
        assertEquals("HP", createdFile.get().getMovieName());
        List<WordInfo> words = wordDao.getWordsFromMovieByUserId(fileId, userId);
        assertFalse(words.isEmpty());
    }
}
