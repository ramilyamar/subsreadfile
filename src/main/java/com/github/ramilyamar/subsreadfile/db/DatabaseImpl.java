package com.github.ramilyamar.subsreadfile.db;

import io.vavr.control.Option;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseImpl implements Database {

    private static final String H2_DRIVER = "org.h2.Driver";

    private Connection connection;

    public DatabaseImpl() {
        try {
            Class.forName(H2_DRIVER);
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long insertAndGetId(String sql, Object... parameters) {
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

    public void insert(String sql, Object... parameters) {
        try (PreparedStatement ignored = executeStatement(sql, parameters)) {

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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

    public void executeUpdate(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("squid:S2095")
    private PreparedStatement executeStatement(String sql, Object... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < parameters.length; i++) {
            statement.setString(i + 1, String.valueOf(parameters[i]));
        }
        statement.executeUpdate();
        return statement;
    }
}
