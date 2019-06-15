package com.github.ramilyamar.subsreadfile.db;

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
            statement.executeUpdate("create table IF NOT EXISTS users (id INTEGER not NULL auto_increment, name VARCHAR(255), " +
                    "password VARCHAR(255), PRIMARY KEY (id))");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long insert(String sql, String... parameters) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < parameters.length; i++) {
                statement.setString(i + 1, parameters[i]);
            }
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new RuntimeException("Ошибка при создании сущности");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
