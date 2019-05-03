package com.github.ramilyamar.subsreadfile.dict;

import com.github.ramilyamar.subsreadfile.util.StringUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class SimpleDictionaryParser implements DictionaryParser {

    @Override
    public Dictionary parse(File file) {
        final Map<String, Collection<String>> dictionary = new HashMap<>();
        try {
            Stream<String> lines = Files.lines(Paths.get(file.toURI()));
            State[] previousState = new State[]{State.NONE};

            final String[] word = new String[1];

            lines.forEach(line -> {
                State currentState = getCurrentState(previousState[0], line);
                previousState[0] = currentState;
                switch (currentState) {
                    case WORD:
                        word[0] = StringUtil.substringBefore(line, " /");
                        break;
                    case TRANSLATION_SINGLE:
                        dictionary.put(word[0], Collections.singletonList(line));
                        break;
                    case TRANSLATION_MULTIPLE:
                        String translation = StringUtil.substringAfter(line, " ");
                        Collection<String> savedTranslations = dictionary.get(word[0]);
                        if (savedTranslations == null) {
                            List<String> translations = new ArrayList<>();
                            translations.add(translation);
                            dictionary.put(word[0], translations);
                        } else
                            savedTranslations.add(translation);
                        break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashMapDictionary(dictionary);
    }

    private State getCurrentState(State previousState, String currentLine) {
        switch (previousState) {
            case NONE:
                return State.WORD;
            case TRANSLATION_SINGLE:
                return State.WORD;
            case WORD:
                return startsWithDigit(currentLine) ? State.TRANSLATION_MULTIPLE : State.TRANSLATION_SINGLE;
            case TRANSLATION_MULTIPLE:
                return startsWithDigit(currentLine) ? State.TRANSLATION_MULTIPLE : State.WORD;
        }
        throw new IllegalStateException("impossible");
    }

    private boolean startsWithDigit(String line) {
        return line.charAt(0) >= '0' && line.charAt(0) <= '9';
    }

    private enum State {
        TRANSLATION_SINGLE,
        TRANSLATION_MULTIPLE,
        WORD,
        NONE
    }
}