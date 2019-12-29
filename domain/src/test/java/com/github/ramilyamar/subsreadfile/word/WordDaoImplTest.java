package com.github.ramilyamar.subsreadfile.word;

import com.github.ramilyamar.subsreadfile.db.TestDatabase;
import com.github.ramilyamar.subsreadfile.db.TestUtil;
import com.github.ramilyamar.subsreadfile.user.EncryptedPassword;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordDaoImplTest {

    private WordDao wordDao = new WordDaoImpl(TestDatabase.getInstance().getDatabase());
    private UserDaoImpl userDao = new UserDaoImpl(TestDatabase.getInstance().getDatabase());

    @Test
    void getOrSaveWord() {
        long userId = userDao.createUser(TestUtil.uniqueString(), new EncryptedPassword("2", "2"));
        Collection<String> translations = Arrays.asList("он");
        WordInfo wordInfo = new WordInfo("he", translations, userId, LearningStatus.NEW_WORD);
        long expected = wordDao.getOrSaveWord(wordInfo);
        List<WordInfo> wordsByUserIdAfterSaving = wordDao.getWordsByUserId(userId);
        assertEquals(1, wordsByUserIdAfterSaving.size());
        assertEquals(expected, wordsByUserIdAfterSaving.get(0).getId().longValue());
    }

    @Test
    void saveAlreadyExistingWord() {
        long userId = userDao.createUser(TestUtil.uniqueString(), new EncryptedPassword("2", "2"));
        Collection<String> translations = Arrays.asList("он");
        WordInfo wordInfo = new WordInfo("he", translations, userId, LearningStatus.NEW_WORD);
        long firstWordId = wordDao.getOrSaveWord(wordInfo);
        long secondWordId = wordDao.getOrSaveWord(wordInfo);
        assertEquals(firstWordId, secondWordId);
    }
}
