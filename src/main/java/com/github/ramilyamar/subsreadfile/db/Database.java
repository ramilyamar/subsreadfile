package com.github.ramilyamar.subsreadfile.db;

import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {

    private static final String H2_DRIVER = "org.h2.Driver";

    private Connection connection;

    public Database() {
        try {
            Class.forName(H2_DRIVER);
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            statement.executeUpdate("create table IF NOT EXISTS users " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(255) UNIQUE, " +
                    "password VARCHAR(255), " +
                    "salt VARCHAR(30), " +
                    "PRIMARY KEY (id))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long insert(String sql, String... parameters) {
        try (PreparedStatement statement = executeStatement(sql, parameters);
             ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new RuntimeException("Ошибка при создании сущности");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("squid:S2095")
    private PreparedStatement executeStatement(String sql, String... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < parameters.length; i++) {
            statement.setString(i + 1, parameters[i]);
        }
        statement.executeUpdate();
        return statement;
    }

    public Option<String> getString(String sql, String... parameters) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setString(i + 1, parameters[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Option.of(resultSet.getString(1));
            else return Option.none();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
