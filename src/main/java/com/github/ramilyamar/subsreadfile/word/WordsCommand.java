package com.github.ramilyamar.subsreadfile.word;

import com.github.ramilyamar.subsreadfile.user.UserInfo;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class WordsCommand {

    private final WordDao wordDao;

    /**
     * Returns list of all words of user if user didn't enter specified id of file,
     * or list of words from specified movie.
     *
     * @param currentUser current user
     * @param tokens      parts of full command in the command line
     * @return list of all words of user if user didn't enter specified id of file,
     * or list of words from specified movie
     */
    public Collection<WordInfo> execute(UserInfo[] currentUser, String[] tokens) {
        if (tokens.length == 1) {
            List<WordInfo> wordsByUserId = wordDao.getWordsByUserId(currentUser[0].getId());
            Map<String, WordInfo> uniqueWords = new HashMap<>();
            wordsByUserId.forEach(wordInfo ->
                    uniqueWords.put(wordInfo.getWord(), wordInfo)
            );
            return uniqueWords.values();
        } else {
            return wordDao.getWordsFromMovieByUserId(Long.parseLong(tokens[1]), currentUser[0].getId());
        }
    }
}
