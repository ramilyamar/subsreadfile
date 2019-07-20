package com.github.ramilyamar.subsreadfile.enums;

import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static com.github.ramilyamar.subsreadfile.enums.Command.ADD;
import static com.github.ramilyamar.subsreadfile.enums.Command.EXIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    public void test() {
        assertEquals(Option.of(EXIT), Command.fromString("exit"));
        assertEquals(Option.of(ADD), Command.fromString("add"));
        assertEquals(Option.none(), Command.fromString("hhh000"));
    }
}