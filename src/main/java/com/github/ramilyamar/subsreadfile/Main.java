package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.dict.SimpleDictionaryParser;
import com.github.ramilyamar.subsreadfile.words.SimpleWordsExtractor;

public class Main {

    public static void main(String[] args) {
        Application app = new Application(
                new SimpleWordsExtractor(),
                new SimpleDictionaryParser(),
                args[1]
        );
        app.run(args[0]);
    }
}