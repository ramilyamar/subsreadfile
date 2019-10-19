package com.github.ramilyamar.subsreadfile.subs;

import com.github.ramilyamar.subsreadfile.Main;
import com.github.ramilyamar.subsreadfile.db.TestDatabase;
import com.github.ramilyamar.subsreadfile.db.TestUtil;
import com.github.ramilyamar.subsreadfile.dict.Dictionary;
import com.github.ramilyamar.subsreadfile.dict.SimpleDictionaryParser;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.file.FileDaoImpl;
import com.github.ramilyamar.subsreadfile.file.FileInfo;
import com.github.ramilyamar.subsreadfile.user.EncryptedPassword;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import com.github.ramilyamar.subsreadfile.word.*;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

class SubsLoaderTest {
    private UserDaoImpl userDao = new UserDaoImpl(TestDatabase.getInstance().getDatabase());
    private WordsExtractor wordsExtractor = new SimpleWordsExtractor();
    private Dictionary dictionary = new SimpleDictionaryParser()
            .parse(new File(Main.defaultDictionary));
    private FileDao fileDao = new FileDaoImpl(TestDatabase.getInstance().getDatabase());
    private MovieWordLinkDao linkDao = new MovieWordLinkDaoImpl(TestDatabase.getInstance().getDatabase());
    private WordDao wordDao = new WordDaoImpl(TestDatabase.getInstance().getDatabase());
    private SubsLoader subsLoader = new SubsLoader(wordsExtractor, dictionary, fileDao, wordDao, linkDao);

    @TempDir
    Path tempDir;

    @Test
    void load() {
        String file = "src/test/resources/example.srt";
        long userId = userDao.createUser("Name", new EncryptedPassword("2", "2"));
        long fileId = subsLoader.load(file, userId, "HP");

        Option<FileInfo> createdFile = fileDao.getFileInfoById(fileId);
        assertEquals("HP", createdFile.get().getMovieName());
        List<WordInfo> words = wordDao.getWordsFromMovieByUserId(fileId, userId);
        assertFalse(words.isEmpty());
    }

    @Test
    void getWordsFromDifferentFiles() throws IOException {
        long userId = userDao.createUser(TestUtil.uniqueString(), new EncryptedPassword("2", "2"));
        long firstFileId = getTempSubsFile(userId, "Hell", "I'm Tom", "Zoo");
        long secondFileId = getTempSubsFile(userId, "Hell", "American", "Bye");

        List<String> allWords = getWords(wordDao.getWordsByUserId(userId));
        List<String> firstFileWords = getWords(wordDao.getWordsFromMovieByUserId(firstFileId, userId));
        List<String> secondFileWords = getWords(wordDao.getWordsFromMovieByUserId(secondFileId, userId));
        assertEquals(1, allWords.stream().filter(w -> w.equals("hell")).count());
        assertThat(firstFileWords, hasItem("zoo"));
        assertThat(secondFileWords, hasItem("american"));
    }

    private long getTempSubsFile(long userId, String... subs) throws IOException {
        Path path = tempDir.resolve(TestUtil.uniqueString());
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < subs.length; i++) {
            lines.add(String.valueOf(i + 1));
            lines.add("00:00:37,700 --> 00:00:41,120");
            lines.add(subs[i]);
            lines.add("");
        }
        Files.write(path, lines);
        return subsLoader.load(path.toString(), userId, "movie name");
    }

    private List<String> getWords(List<WordInfo> list) {
        return list
                .stream()
                .map(WordInfo::getWord)
                .collect(Collectors.toList());
    }
}
