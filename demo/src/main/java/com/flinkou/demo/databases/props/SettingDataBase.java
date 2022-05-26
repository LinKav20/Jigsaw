package com.flinkou.demo.databases.props;

public class SettingDataBase {
    // Driver database.
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    // Prefix of URL connection.
    private static final String conURLPrefix = "jdbc:derby:";
    // Postfix of URL connection.
    private static final String conURLPostfix = ";create=true";
    // Max rows to select.
    private static final int maxRows = 10;

    /**
     * Get driver.
     *
     * @return Driver in string format.
     */
    public static String getDriver() {
        return driver;
    }

    /**
     * URL connection.
     *
     * @param dbName Database name.
     * @return String URL connection.
     */
    public static String getConnectionURL(String dbName) {
        return conURLPrefix + dbName + conURLPostfix;
    }

    /**
     * Max count of rows.
     *
     * @return Int count.
     */
    public static int getMaxRows() {
        return maxRows;
    }
}
