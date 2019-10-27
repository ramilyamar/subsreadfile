package com.github.ramilyamar.subsreadfile.dict;

import java.util.Collection;

public interface Dictionary {

    /**
     * Translates word by getting value from Map dictionary.
     * @param word word from subs file
     * @return translation of word
     */
    Collection<String> translate(String word);
}
