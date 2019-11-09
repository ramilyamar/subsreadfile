package com.github.ramilyamar.subsreadfile.util;

/**
 * The {@code StringUtil} class provides methods for processing of strings.
 */
public class StringUtil {

    /**
     * Returns a substring by parameters - the initial string and the separator.
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
     * The method returns a substring - all data after the separator.
     *
     * @param string    initial string
     * @param delimiter separator
     * @return substring by parameters - the initial string and the separator
     */
    public static String substringAfter(String string, String delimiter) {
        return string.substring(string.indexOf(delimiter) + 1);
    }
}