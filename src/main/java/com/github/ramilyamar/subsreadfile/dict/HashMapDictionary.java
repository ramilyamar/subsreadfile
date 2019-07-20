package com.github.ramilyamar.subsreadfile.dict;

import java.util.*;

public class HashMapDictionary implements Dictionary {

    private Map<String, Collection<String>> dictionary;

    HashMapDictionary(Map<String, Collection<String>> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public Collection<String> translate(String word) {
        return dictionary.get(word);
    }
}