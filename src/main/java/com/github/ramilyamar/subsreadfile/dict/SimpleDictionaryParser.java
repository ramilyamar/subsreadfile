package com.github.ramilyamar.subsreadfile.dict;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SimpleDictionaryParser implements DictionaryParser {

    @Override
    public Dictionary parse(File file) {
        try {
            Stream<String> lines = Files.lines(Paths.get(file.toURI()));
            // TODO: 27.04.2019 остановились тут


//            lines.forEach(
//            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashMapDictionary(null);
    }
}
