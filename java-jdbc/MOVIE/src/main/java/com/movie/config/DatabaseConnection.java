package com.movie.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Centraliza la conexión JDBC con MySQL. */
public final class DatabaseConnection {
    private static final String URL = env("MOVIE_DB_URL", "jdbc:mysql://localhost:3306/movie?serverTimezone=UTC");
    private static final String USER = env("MOVIE_DB_USER", "root");
    private static final String PASSWORD = env("MOVIE_DB_PASSWORD", "");

    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String env(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
