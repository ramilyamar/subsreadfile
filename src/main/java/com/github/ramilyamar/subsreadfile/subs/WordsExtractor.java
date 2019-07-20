package com.github.ramilyamar.subsreadfile.subs;

import java.io.FileInputStream;
import java.util.SortedSet;

interface WordsExtractor {
    SortedSet<String> getUniqueWords(FileInputStream inputStream);
}
