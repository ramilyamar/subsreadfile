package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.dict.HashMapDictionary;
import com.github.ramilyamar.subsreadfile.words.SimpleWordsExtractor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Application app = new Application(
                new SimpleWordsExtractor(),
                new HashMapDictionary(Map.of(
                        "legend", Arrays.asList("легенда", "сказка"),
                        "warrior", Collections.singletonList("воин"),
                        "he", Collections.singletonList("он")
                ))
        );
        app.run(args[0]);
    }
}