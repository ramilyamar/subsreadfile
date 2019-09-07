package com.github.ramilyamar.subsreadfile.subs;

import com.github.ramilyamar.subsreadfile.dict.Dictionary;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.file.FileInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.SortedSet;

public class SubsLoader {

    private final WordsExtractor wordsExtractor;
    private final Dictionary dictionary;
    private final FileDao fileDao;

    public SubsLoader(WordsExtractor wordsExtractor, Dictionary dictionary, FileDao fileDao) {
        this.fileDao = fileDao;
        this.wordsExtractor = wordsExtractor;
        this.dictionary = dictionary;
    }

    public long load(String filePath, long userId, String movieName) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            SortedSet<String> uniqueWords = wordsExtractor.getUniqueWords(inputStream);
            for (String word : uniqueWords) {
                Collection<String> translations = dictionary.translate(word);
                System.out.println(word + " - " + translations);
            }
            String name = new File(filePath).getName();
            fileDao.createFile(new FileInfo(name, userId, movieName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Укажите путь к файлу"); // TODO более корректно обработать ошибку
        }
        return 0;  // TODO: 25.05.2019 return unique ID
    }
}
