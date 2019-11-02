package com.github.ramilyamar.subsreadfile.word;

/**
 * The {@code MovieWordLinkDao} interface provides methods to process data
 * about words from concrete movies of user.
 */
public interface MovieWordLinkDao {

    /**
     * Creates and saves word to storage.
     *
     * @param wordId id of word
     * @param fileId id of file
     */
    void saveMovieWord(long wordId, long fileId);
}
