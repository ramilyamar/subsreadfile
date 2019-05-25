package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.commands.SubsLoader;
import com.github.ramilyamar.subsreadfile.dict.SimpleDictionaryParser;
import com.github.ramilyamar.subsreadfile.words.SimpleWordsExtractor;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Application app = new Application(
                new SubsLoader(
                        new SimpleWordsExtractor(),
                        new SimpleDictionaryParser().parse(new File(args[0]))
                )
        );
        app.run();
    }
}