package com.flinkou.demo.databases;

import java.sql.*;

public class Utils {
    public static boolean checker(Connection conTst, String tableName) throws SQLException {
        try {
            Statement s = conTst.createStatement();
            s.execute(Queries.updateTable(tableName));
        } catch (SQLException e) {
            String theError = (e).getSQLState();
            if (theError.equals("42X05")) {
                return false;
            }
        }
        return true;
    }
}
