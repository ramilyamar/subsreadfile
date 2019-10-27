package com.github.ramilyamar.subsreadfile.word;

public interface MovieWordLinkDao {

    /**
     * Creates and saves word to storage.
     * @param wordId id of word
     * @param fileId id of file
     */
    void saveMovieWord(long wordId, long fileId);
}
