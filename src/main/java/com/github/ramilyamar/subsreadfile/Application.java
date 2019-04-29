package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.dict.Dictionary;
import com.github.ramilyamar.subsreadfile.words.WordsExtractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.SortedSet;

public class Application {
    private WordsExtractor wordsExtractor;
    private Dictionary dictionary;

    Application(WordsExtractor wordsExtractor, Dictionary dictionary) {
        this.wordsExtractor = wordsExtractor;
        this.dictionary = dictionary;
    }

    void run(String filePath) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            SortedSet<String> uniqueWords = wordsExtractor.getUniqueWords(inputStream);
            for (String word : uniqueWords) {
                Collection<String> translations = dictionary.translate(word);
                System.out.println(word + " - " + translations);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Укажите путь к файлу"); // TODO более корректно обработать ошибку
        }
    }
}