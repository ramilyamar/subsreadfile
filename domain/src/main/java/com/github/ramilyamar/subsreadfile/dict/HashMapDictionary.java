package com.github.ramilyamar.subsreadfile.dict;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Dictionary implementation based on {@link HashMap}.
 */
@AllArgsConstructor
public class HashMapDictionary implements Dictionary {

    private Map<String, Collection<String>> dictionary;

    @Override
    public Collection<String> translate(String word) {
        return dictionary.get(word);
    }
}
