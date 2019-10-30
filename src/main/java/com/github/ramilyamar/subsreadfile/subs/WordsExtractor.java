package com.github.ramilyamar.subsreadfile.subs;

import java.io.FileInputStream;
import java.util.SortedSet;

interface WordsExtractor {

    /**
     * Returns set of unique words from file subs in alphabetical order.
     *
     * @param inputStream subs file
     * @return set of unique words from file subs by alphabetical order
     */
    SortedSet<String> getUniqueWords(FileInputStream inputStream);
}
