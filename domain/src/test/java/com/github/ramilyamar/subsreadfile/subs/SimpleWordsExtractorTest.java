package com.github.ramilyamar.subsreadfile.subs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleWordsExtractorTest {

    private static WordsExtractor wordsExtractor = new SimpleWordsExtractor();
    private static File file = new File("src/test/resources/example.srt");

    private static Collection<String> words;
    static {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        words = wordsExtractor.getUniqueWords(inputStream);
    }

    @Test
    @DisplayName("Should not contain numbers")
    void noNumbers() {
        boolean containNumber = words
                .stream()
                .noneMatch(w -> w.matches("\\d+"));
        assertTrue(containNumber);
    }

    @Test
    @DisplayName("Should contain only latin letters and allowed symbols: [-']")
    void onlyAllowedSymbols() {
        boolean onlyAllowedSymbols = words
                .stream()
                .allMatch(w -> w.matches("[A-Za-z-']*"));
        assertTrue(onlyAllowedSymbols);
    }

    @Test
    @DisplayName("Should return all words from input file")
    void allWords() {
        boolean allWords = words
                .containsAll(Arrays.asList("legend", "tells", "of", "legendary", "warrior", "whose",
                        "kung", "fu", "skills", "were", "the", "stuff", "he", "was", "for", "years"));
        assertTrue(allWords);
    }

    @Test
    @DisplayName("Should return unique words")
    void uniqueWords() {
        long distinctCount = words.stream().distinct().count();
        assertEquals(distinctCount, words.size());
    }

    @Test
    @DisplayName("Should return words in lower case")
    void lowerCase() {
        boolean allLowerCase = words
                .stream()
                .allMatch(w -> w.toLowerCase().equals(w));
        assertTrue(allLowerCase);
    }

    @Test
    @DisplayName("Should not contain spaces")
    void noSpaces() {
        boolean containSpace = words
                .stream()
                .noneMatch(w -> w.matches(".* .*"));
        assertTrue(containSpace);
    }
}
