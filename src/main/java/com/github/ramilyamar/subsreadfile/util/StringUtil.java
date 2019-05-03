package com.github.ramilyamar.subsreadfile.util;

public class StringUtil {

    public static String substringBefore(String string, String delimiter) {
        int indexOfDelimiter = string.indexOf(delimiter);
        if (indexOfDelimiter == -1) return string;
        return string.substring(0, indexOfDelimiter);
    }

    public static String substringAfter(String string, String delimiter) {
        return string.substring(string.indexOf(delimiter) + 1);
    }
}