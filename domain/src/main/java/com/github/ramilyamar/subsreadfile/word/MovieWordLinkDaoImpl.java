package com.github.ramilyamar.subsreadfile.word;

import com.github.ramilyamar.subsreadfile.db.Database;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieWordLinkDaoImpl implements MovieWordLinkDao {

    private final Database database;
    private static final String INSERT_SQL = "INSERT INTO movieWordLink (wordId, fileId) VALUES (?, ?)";

    @Override
    public void saveMovieWord(long wordId, long fileId) {
        try {
            database.insert(INSERT_SQL, wordId, fileId);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
