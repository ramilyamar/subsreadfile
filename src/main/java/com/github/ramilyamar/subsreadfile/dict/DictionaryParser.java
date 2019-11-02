package com.github.ramilyamar.subsreadfile.dict;

import java.io.File;

public interface DictionaryParser {

    /**
     * Converts dictionary file to instance of {@link Dictionary}.
     *
     * @param file dictionary file
     * @return translation dictionary
     */
    Dictionary parse(File file);
}
