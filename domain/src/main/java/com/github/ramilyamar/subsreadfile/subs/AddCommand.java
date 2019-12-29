package com.github.ramilyamar.subsreadfile.subs;

import com.github.ramilyamar.subsreadfile.dict.Dictionary;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.file.FileInfo;
import com.github.ramilyamar.subsreadfile.word.LearningStatus;
import com.github.ramilyamar.subsreadfile.word.MovieWordLinkDao;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordInfo;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.SortedSet;

/**
 * The {@code AddCommand} class provides method for saving word information from loaded subs file.
 * This class is needed to execute the command {@link com.github.ramilyamar.subsreadfile.app.Command#ADD}.
 */
@AllArgsConstructor
public class AddCommand {

    private final WordsExtractor wordsExtractor;
    private final Dictionary dictionary;
    private final FileDao fileDao;
    private final WordDao wordDao;
    private final MovieWordLinkDao linkDao;

    /**
     * Returns id of subs file and saves word information from loaded file in storage.
     *
     * @param filePath  subs file path
     * @param userId    id of user
     * @param movieName name of movie
     * @return id of subs file
     */
    public long execute(String filePath, long userId, String movieName) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            SortedSet<String> uniqueWords = wordsExtractor.getUniqueWords(inputStream);
            String name = new File(filePath).getName();
            long fileId = fileDao.createFile(new FileInfo(name, userId, movieName));
            for (String word : uniqueWords) {
                Collection<String> translations = dictionary.translate(word);
                WordInfo wordInfo = new WordInfo(word, translations, userId, LearningStatus.NEW_WORD);
                long wordId = wordDao.getOrSaveWord(wordInfo);
                linkDao.saveMovieWord(wordId, fileId);
            }
            return fileId;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
