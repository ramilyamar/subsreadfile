package com.github.ramilyamar.subsreadfile.file;

import com.github.ramilyamar.subsreadfile.db.Database;
import io.vavr.control.Option;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDaoImpl implements FileDao {

    private Database database;
    private static final String INSERT_SQL = "INSERT INTO files (name, userId, movieName) VALUES (?, ?, ?)";

    public FileDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public long createFile(FileInfo fileInfo) {
        return database.insert(INSERT_SQL, fileInfo.getName(), fileInfo.getUserId(), fileInfo.getMovieName());
    }

    @Override
    public Option<FileInfo> getFileInfoById(long id) {
        String sql = "SELECT name, userId, movieName FROM files WHERE id = '" + id + "'";
        ResultSet resultSet = database.executeQuery(sql);
        try {
            if (!resultSet.next()) {
                return Option.none();
            }
            String name = resultSet.getString("name");
            long userId = resultSet.getLong("userId");
            String movieName = resultSet.getString("movieName");
            return Option.of(new FileInfo(name, userId, movieName));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
