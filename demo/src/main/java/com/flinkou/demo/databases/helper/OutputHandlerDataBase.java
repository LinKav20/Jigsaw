package com.flinkou.demo.databases.helper;

/**
 * Class for output database messages.
 */
public class OutputHandlerDataBase {
    /**
     * First connection is bad.
     */
    public static void connectionToDataBaseError() {
        System.out.println("Cannot connect with database");
    }

    /**
     * Errors with database connection.
     */
    public static void smthWentWrong() {
        System.out.println("...Oops smth wrong with database connection...");
    }
}
