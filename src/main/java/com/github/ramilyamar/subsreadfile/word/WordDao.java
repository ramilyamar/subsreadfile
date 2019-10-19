package com.github.ramilyamar.subsreadfile.word;

import java.util.List;

public interface WordDao {

    long getOrSaveWord(WordInfo wordInfo);

    List<WordInfo> getWordsByUserId(long userId);

    List<WordInfo> getWordsFromMovieByUserId(long fileId, long userId);
}
