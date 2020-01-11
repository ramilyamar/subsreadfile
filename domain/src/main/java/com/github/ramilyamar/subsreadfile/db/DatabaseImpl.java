package com.github.ramilyamar.subsreadfile.db;

import io.vavr.control.Option;

import java.sql.*;

public class DatabaseImpl implements Database {

    private Connection connection;

    public DatabaseImpl(DbProperties dbProperties) {
        try {
            Class.forName(dbProperties.getDriverClass());
            connection = DriverManager.getConnection(dbProperties.getUrl(),
                    dbProperties.getUser(), dbProperties.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public void insert(String sql, Object... parameters) {
        try (PreparedStatement ignored = executeStatement(sql, parameters)) {

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public void executeUpdate(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
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
