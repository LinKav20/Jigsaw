package com.flinkou.demo.network.server;

import com.flinkou.demo.databases.DataBase;

/**
 * Start server class.
 */
public class StartServer {

    /**
     * Run server.
     *
     * @param args Main args.
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
