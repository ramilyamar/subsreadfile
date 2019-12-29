package com.github.ramilyamar.subsreadfile.dict;

import java.util.Collection;

/**
 * The {@code Dictionary} interface represents translation dictionary, for example, English-Russian dictionary.
 */
public interface Dictionary {

    /**
     * Translates word.
     *
     * @param word word from subs file
     * @return translations of word
     */
    Collection<String> translate(String word);
}
