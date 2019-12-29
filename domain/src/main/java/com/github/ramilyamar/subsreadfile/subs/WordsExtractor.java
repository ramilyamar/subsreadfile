package com.github.ramilyamar.subsreadfile.subs;

import java.io.FileInputStream;
import java.util.SortedSet;

interface WordsExtractor {

    /**
     * Returns set of unique words from subs file in alphabetical order.
     *
     * @param inputStream subs file
     * @return set of unique words from subs file by alphabetical order
     */
    SortedSet<String> getUniqueWords(FileInputStream inputStream);
}
