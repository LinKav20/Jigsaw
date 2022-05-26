package com.flinkou.demo.databases;

/**
 * Class for database queries.
 */
public class Queries {
    /**
     * Create table.
     *
     * @param tableName Table name.
     * @return Query for create table.
     */
    public static String createTable(String tableName) {
        return "CREATE TABLE " + tableName
                + " (" + tableName + "_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                + "   CONSTRAINT " + tableName + "_PK PRIMARY KEY, "
                + " END_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + " USER_NAME VARCHAR(64) NOT NULL, "
                + " SCORE INT NOT NULL, "
                + " GAME_TIME INT NOT NULL) ";
    }

    /**
     * Update table.
     *
     * @param tableName Table name.
     * @return Query for update table.
     */
    public static String updateTable(String tableName) {
        return "update " + tableName + " set END_TIME = CURRENT_TIMESTAMP, USER_NAME = 'TEST ENTRY', SCORE = 0, GAME_TIME = 0 where 1=3";
    }

    /**
     * Insert row in table.
     *
     * @param tableName Table name.
     * @return Query for insert row in table.
     */
    public static String insertScore(String tableName, String username, int score, int gameTime) {
        return "INSERT INTO " + tableName
                + "(USER_NAME, SCORE, GAME_TIME) VALUES ('"
                + username + "', " + score + ", "
                + gameTime + ")";
    }

    /**
     * Select from table.
     *
     * @param tableName Table name.
     * @return Query for select from table.
     */
    public static String selectAll(String tableName) {
        return "select END_TIME, USER_NAME, SCORE, GAME_TIME from " + tableName + " order by USER_NAME, GAME_TIME, SCORE";
    }
}
