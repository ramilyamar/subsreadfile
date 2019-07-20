package com.github.ramilyamar.subsreadfile.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {

    @Test
    void substringBefore() {
        String actual = StringUtil.substringBefore("Belgian woman /beldʒənwumən/", " /");
        assertEquals("Belgian woman", actual);
    }

    @Test
    void substringBeforeNoDelimiter() {
        String actual = StringUtil.substringBefore("abc", " ");
        assertEquals("abc", actual);
    }

    @Test
    void substringAfter() {
        String actual = StringUtil.substringAfter("1. американский", " ");
        assertEquals("американский", actual);
    }

    @Test
    void substringAfter2() {
        String actual = StringUtil.substringAfter("10000. американский", " ");
        assertEquals("американский", actual);
    }

    @Test
    void substringAfterNoDelimiter() {
        String actual = StringUtil.substringAfter("abc", " ");
        assertEquals("abc", actual);
    }
}
