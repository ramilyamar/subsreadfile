package com.github.ramilyamar.subsreadfile.subs;

import com.github.ramilyamar.subsreadfile.dict.Dictionary;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.file.FileInfo;
import com.github.ramilyamar.subsreadfile.word.LearningStatus;
import com.github.ramilyamar.subsreadfile.word.MovieWordLinkDao;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.SortedSet;

public class SubsLoader {

    private final WordsExtractor wordsExtractor;
    private final Dictionary dictionary;
    private final FileDao fileDao;
    private final WordDao wordDao;
    private final MovieWordLinkDao linkDao;

    public SubsLoader(WordsExtractor wordsExtractor, Dictionary dictionary, FileDao fileDao,
                      WordDao wordDao, MovieWordLinkDao linkDao) {
        this.wordsExtractor = wordsExtractor;
        this.dictionary = dictionary;
        this.fileDao = fileDao;
        this.wordDao = wordDao;
        this.linkDao = linkDao;
    }

    public long load(String filePath, long userId, String movieName) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            SortedSet<String> uniqueWords = wordsExtractor.getUniqueWords(inputStream);
            String name = new File(filePath).getName();
            long fileId = fileDao.createFile(new FileInfo(name, userId, movieName));
            for (String word : uniqueWords) {
                Collection<String> translations = dictionary.translate(word);
                System.out.println(word + " - " + translations);
                WordInfo wordInfo = new WordInfo(word, translations, userId, LearningStatus.NEW_WORD);
                long wordId = wordDao.getOrSaveWord(wordInfo);
                linkDao.saveMovieWord(wordId, fileId);
            }
            return fileId;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Укажите путь к файлу"); // TODO более корректно обработать ошибку
            throw new RuntimeException(e);
        }
    }
}
