package com.github.ramilyamar.subsreadfile.word;

import com.github.ramilyamar.subsreadfile.db.Database;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@AllArgsConstructor
public class WordDaoImpl implements WordDao {
    private final Database database;
    private static final String INSERT_SQL =
            "INSERT INTO words (word, translations, userId, learningStatus) VALUES (?, ?, ?, ?)";

    @Override
    public long getOrSaveWord(WordInfo wordInfo) {
        long wordId;
        Collection<String> translations = wordInfo.getTranslations() != null
                ? wordInfo.getTranslations()
                : Collections.emptyList();
        String sql = "SELECT id FROM words WHERE word = '" + wordInfo.getWord() +
                "' AND userId = '" + wordInfo.getUserId() + "'";
        ResultSet resultSet = database.executeQuery(sql);
        try {
            if (!resultSet.next()) {
                wordId = database.insertAndGetId(INSERT_SQL, wordInfo.getWord(),
                        String.join("|", translations),
                        wordInfo.getUserId(), wordInfo.getLearningStatus().getId());
            } else {
                wordId = resultSet.getLong("id");
            }
            return wordId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WordInfo> getWordsByUserId(long userId) {
        List<WordInfo> words = new ArrayList<>();
        String sql = "SELECT id, word, translations, userId, learningStatus " +
                "FROM words WHERE userId = '" + userId + "'";
        ResultSet resultSet = database.executeQuery(sql);
        try {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                words.add(getWordInfo(resultSet, id));
            }
            return words;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<WordInfo> getWordsFromMovieByUserId(long fileId, long userId) {
        List<WordInfo> words = new ArrayList<>();
        String sql = "SELECT wordId, word, translations, userId, fileId, learningStatus " +
                "FROM words INNER JOIN movieWordLink " +
                "ON words.id = movieWordLink.wordId " +
                "WHERE words.userId = '" + userId + "' AND movieWordLink.fileId = '" + fileId + "'";
        ResultSet resultSet = database.executeQuery(sql);
        try {
            while (resultSet.next()) {
                long id = resultSet.getLong("wordId");
                words.add(getWordInfo(resultSet, id));
            }
            return words;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private WordInfo getWordInfo(ResultSet resultSet, long id) throws SQLException {
        String word = resultSet.getString("word");
        String translations = resultSet.getString("translations");
        List<String> translationsList = Arrays.asList(translations.split("\\|"));
        long userId = resultSet.getLong("userId");
        LearningStatus learningStatus = LearningStatus.fromInt(resultSet.getInt("learningStatus"))
                .getOrElseThrow(() -> new RuntimeException("Не найден статус изучения"));
        return new WordInfo(id, word, translationsList, userId, learningStatus);
    }
}
