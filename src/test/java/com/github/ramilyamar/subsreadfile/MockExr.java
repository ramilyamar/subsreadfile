package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.words.WordsExtractor;

import java.io.FileInputStream;
import java.util.SortedSet;

public class MockExr implements WordsExtractor {

    @Override
    public SortedSet<String> getUniqueWords(FileInputStream inputStream) {
        return null;
    }
}
