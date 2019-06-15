package com.github.ramilyamar.subsreadfile.enums;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static com.github.ramilyamar.subsreadfile.enums.Command.ADD;
import static com.github.ramilyamar.subsreadfile.enums.Command.EXIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    public void test() {
        assertEquals(Optional.of(EXIT), Command.fromString("exit"));
        assertEquals(Optional.of(ADD), Command.fromString("add"));
        assertEquals(Optional.empty(), Command.fromString("hhh000"));
    }
}