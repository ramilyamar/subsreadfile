package com.github.ramilyamar.subsreadfile.word;

import java.util.List;

public interface WordDao {

    /**
     * Creates and saves word to storage or returns id of existing word.
     *
     * @param wordInfo word information
     * @return id of created word or id of existing word
     */
    long getOrSaveWord(WordInfo wordInfo);

    /**
     * Returns list of words by current user.
     *
     * @param userId id of user
     * @return list of words by current user
     */
    List<WordInfo> getWordsByUserId(long userId);

    /**
     * Returns list of words by current user and chosen file.
     *
     * @param fileId id of file
     * @param userId id of user
     * @return list of words by current user and chosen file
     */
    List<WordInfo> getWordsFromMovieByUserId(long fileId, long userId);
}
