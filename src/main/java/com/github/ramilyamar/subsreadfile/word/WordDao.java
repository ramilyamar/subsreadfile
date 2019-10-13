package com.github.ramilyamar.subsreadfile.word;

import java.util.List;

public interface WordDao {

    long saveWord(WordInfo wordInfo);

    List<WordInfo> getWordsByUserId(long userId);

    List<WordInfo> getWordsFromMovieByUserId(long fileId, long userId);
}
