package com.github.ramilyamar.subsreadfile.subs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.SortedSet;
import java.util.TreeSet;

public class SimpleWordsExtractor implements WordsExtractor {

    @Override
    public SortedSet<String> getUniqueWords(FileInputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        SortedSet<String> uniqueWords = new TreeSet<>(); // сортирует все слова по возрастанию
        try {
            for (String inputString; (inputString = reader.readLine()) != null; ) {
                String cleaned = inputString.replaceAll("<\\/?\\w+>", "");
                String[] words = cleaned.split("\\P{L}"); // "letter"

                for (String word : words) {
                    if (word.length() > 1 && !(word.matches("[0-9]+"))) {
                        uniqueWords.add(word.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            throw new WordsExtractorException(e);
        }
        return uniqueWords;
    }
}
