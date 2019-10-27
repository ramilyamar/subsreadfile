package com.github.ramilyamar.subsreadfile.dict;

import java.io.File;

public interface DictionaryParser {

    /**
     * Converts dictionary file to Map dictionary where word is key and translation is value
     * @param file dictionary file
     * @return Map dictionary
     */
    Dictionary parse(File file);
}
