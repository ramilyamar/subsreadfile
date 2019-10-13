package com.github.ramilyamar.subsreadfile.word;

import com.github.ramilyamar.subsreadfile.db.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WordDaoImpl implements WordDao {
    private Database database;
    private static final String INSERT_SQL =
            "INSERT INTO words (word, translation, fileId, userId, learningStatus) VALUES (?, ?, ?, ?, ?)";

    public WordDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public long saveWord(WordInfo wordInfo) {
        Collection<String> translations = wordInfo.getTranslations() != null
                ? wordInfo.getTranslations()
                : Collections.emptyList();
        return database.insert(INSERT_SQL, wordInfo.getWord(),
                String.join("|", translations),
                wordInfo.getFileId(), wordInfo.getUserId(), wordInfo.getLearningStatus().getId());
    }

    @Override
    public List<WordInfo> getWordsByUserId(long userId) {
        List<WordInfo> words = new ArrayList<>();
        String sql = "SELECT id, word, translation, fileId, userId, learningStatus " +
                "FROM words WHERE userId = '" + userId + "'";
        ResultSet resultSet = database.executeQuery(sql);
        try {
            while (resultSet.next()) {
                words.add(getWordInfo(resultSet));
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
        String sql = "SELECT id, word, translation, fileId, userId, learningStatus " +
                "FROM words WHERE userId = '" + userId + "' AND fileId = '" + fileId + "'";
        ResultSet resultSet = database.executeQuery(sql);
        try {
            while (resultSet.next()) {
                words.add(getWordInfo(resultSet));
            }
            return words;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private WordInfo getWordInfo(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String word = resultSet.getString("word");
        String translations = resultSet.getString("translation");
        List<String> translationsList = Arrays.asList(translations.split("\\|"));
        long fileId = resultSet.getLong("fileId");
        long userId = resultSet.getLong("userId");
        LearningStatus learningStatus = LearningStatus.fromInt(resultSet.getInt("learningStatus"))
                .getOrElseThrow(() -> new RuntimeException("Не найден статус изучения"));
        return new WordInfo(id, word, translationsList, fileId, userId, learningStatus);
    }
}
