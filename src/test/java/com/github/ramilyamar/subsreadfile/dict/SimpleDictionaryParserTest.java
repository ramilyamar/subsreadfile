package com.github.ramilyamar.subsreadfile.dict;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleDictionaryParserTest {

    SimpleDictionaryParser parser = new SimpleDictionaryParser();

    @Test
    void parse() {
        // TODO: 03.05.2019 разделить кейсы. придется поменять сигнатуру метода, чтобы он принимал стрим строк
        File file = new File("src/test/resources/eng-rus.dict");
        Dictionary dictionary = parser.parse(file);
        assertEquals(Arrays.asList("зоопарк"), dictionary.translate("zoo"));
        assertEquals(Arrays.asList("американский", "американец"), dictionary.translate("American"));
    }
}
