package com.github.ramilyamar.subsreadfile.db;

import io.vavr.control.Option;

import java.sql.ResultSet;

public interface Database {

    /**
     * Creates and saves data to storage, returns id of created data.
     * @param sql query for inserting data to storage
     * @param parameters parameters for SQL-query
     * @return id of created object
     */
    long insertAndGetId(String sql, Object... parameters);

    /**
     * Creates and saves data to storage.
     * @param sql query for inserting data to storage
     * @param parameters parameters for SQL-query
     */
    void insert(String sql, Object... parameters);

    /**
     * Returns string from SQL-query.
     * @param sql query for searching data from database
     * @param parameters parameters for SQL-query
     * @return string from SQL-query
     */
    Option<String> getString(String sql, String... parameters);

    /**
     * Executes SQL-queries such as CREATE, READ, UPDATE, DELETE operations.
     * @param sql SQL-query for CREATE, READ, UPDATE, DELETE operations
     */
    void executeUpdate(String sql);

    /**
     * Returns ResultSet of SQL-query such as SELECT command.
     * @param sql SQL-query for SELECT command
     * @return ResultSet of SQL-query such as SELECT command
     */
    ResultSet executeQuery(String sql);
}
