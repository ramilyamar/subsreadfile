package com.github.ramilyamar.subsreadfile.word;

import com.github.ramilyamar.subsreadfile.db.Database;

public class MovieWordLinkDaoImpl implements MovieWordLinkDao {

    private Database database;
    private static final String INSERT_SQL = "INSERT INTO movieWordLink (wordId, fileId) VALUES (?, ?)";

    public MovieWordLinkDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public void saveMovieWord(long wordId, long fileId) {
        try {
            database.insert(INSERT_SQL, wordId, fileId);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
