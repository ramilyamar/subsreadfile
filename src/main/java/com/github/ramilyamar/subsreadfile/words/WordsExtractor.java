package com.github.ramilyamar.subsreadfile.words;

import java.io.FileInputStream;
import java.util.SortedSet;

public interface WordsExtractor {
    SortedSet<String> getUniqueWords(FileInputStream inputStream);
}