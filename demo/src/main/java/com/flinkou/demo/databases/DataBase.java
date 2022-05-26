package com.flinkou.demo.databases;

import com.flinkou.demo.databases.helper.OutputHandlerDataBase;
import com.flinkou.demo.databases.props.SettingDataBase;

import java.sql.*;

/**
 * Class of database.
 */
public class DataBase {
    // Connection with database.
    private Connection connection;
    // Params od databases.
    private final String name, tableName;
    // Statement for database.
    private Statement statement;

    /**
     * Constructor with database name.
     *
     * @param dbName Name of database.
     */
    public DataBase(String dbName) {
        name = dbName;
        tableName = "SCORE_TABLE";
    }

    /**
     * Connect to database.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(SettingDataBase.getConnectionURL(name));
            statement = connection.createStatement();
            statement.setMaxRows(SettingDataBase.getMaxRows());
        } catch (Exception e) {
            OutputHandlerDataBase.connectionToDataBaseError();
        }
    }

    /**
     * Create table.
     */
    public void createTable() {
        try {
            if (!Utils.checker(connection, tableName)) {
                statement.execute(Queries.createTable(tableName));
            }
        } catch (Exception e) {
            OutputHandlerDataBase.smthWentWrong();
        }
    }

    /**
     * Insert score.
     *
     * @param username User`s login.
     * @param score    User`s score.
     * @param gameTime User`s time of the game.
     */
    public void insertScore(String username, int score, int gameTime) {
        try {
            statement.execute(Queries.insertScore(tableName, username, score, gameTime));
        } catch (Exception e) {
            OutputHandlerDataBase.smthWentWrong();
        }
    }

    /**
     * Get top 10 games.
     *
     * @return 10 games in string.
     */
    public String getTOPTen() {
        StringBuilder result = new StringBuilder();
        try {
            ResultSet scores = statement.executeQuery(Queries.selectAll(tableName));
            while (scores.next()) {
                result
                        .append(scores.getTimestamp(1))
                        .append("  ")
                        .append(scores.getString(2))
                        .append(" ")
                        .append(scores.getInt(3))
                        .append(" ")
                        .append(scores.getInt(4))
                        .append("\n");
            }
        } catch (Exception e) {
            OutputHandlerDataBase.smthWentWrong();
        }

        return result.toString();
    }

    /**
     * Close database connection.
     */
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (Exception e) {
            OutputHandlerDataBase.smthWentWrong();
        }
    }
}
