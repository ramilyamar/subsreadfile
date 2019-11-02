package com.github.ramilyamar.subsreadfile.util;

/**
 * The {@code StringUtil} class provides methods for processing of strings.
 */
public class StringUtil {

    /**
     * Returns a substring by parameters - the initial string and the separator.
     * First, the index of this separator is determined.
     * Then, if the delimiter is not found, the string is returned unchanged.
     * If the delimiter is found, the substring is returned from zero to the delimiter index.
     *
     * @param string    initial string
     * @param delimiter separator
     * @return substring by parameters - the initial string and the separator
     */
    public static String substringBefore(String string, String delimiter) {
        int indexOfDelimiter = string.indexOf(delimiter);
        if (indexOfDelimiter == -1) return string;
        return string.substring(0, indexOfDelimiter);
    }

    /**
     * Returns a substring by parameters - the initial string and the separator.
     * The method returns a substring, the starting index of the substring is the separator index + 1,
     * in other words, it returns all data after the separator.
     * If there is no separator, then its index is -1, after applying method index is 0
     * and then the substring starts at the beginning of the line.
     *
     * @param string    initial string
     * @param delimiter separator
     * @return substring by parameters - the initial string and the separator
     */
    public static String substringAfter(String string, String delimiter) {
        return string.substring(string.indexOf(delimiter) + 1);
    }
}