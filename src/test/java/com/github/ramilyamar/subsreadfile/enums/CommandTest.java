package com.github.ramilyamar.subsreadfile.enums;

import com.github.ramilyamar.subsreadfile.app.Command;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static com.github.ramilyamar.subsreadfile.app.Command.ADD;
import static com.github.ramilyamar.subsreadfile.app.Command.EXIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    void test() {
        assertEquals(Option.of(EXIT), Command.fromString("exit"));
        assertEquals(Option.of(ADD), Command.fromString("add"));
        assertEquals(Option.none(), Command.fromString("hhh000"));
    }
}
