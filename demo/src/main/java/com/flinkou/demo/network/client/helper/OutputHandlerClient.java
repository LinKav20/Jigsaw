package com.flinkou.demo.network.client.helper;

import com.flinkou.demo.network.props.ServerSettings;

/**
 * Class to write all data in console.
 */
public class OutputHandlerClient {
    /**
     * Welcome message.
     */
    public static void welcomeMessage() {
        System.out.println("Hi there! You are in client!");
    }

    /**
     * Name instruction.
     */
    public static void nameInstruction() {
        System.out.println("Enter your name");
    }

    /**
     * Error connection.
     */
    public static void errorConnection() {
        System.out.println("Oops... Something went wrong with connection");
    }

    /**
     * Server settings.
     */
    public static void serverSettings() {
        System.out.println("Your host is: " + ServerSettings.getHost() + "\nPlease enter port");
    }

    /**
     * Bye message.
     */
    public static void byeMessage() {
        System.out.println("Bye!");
    }

    /**
     * Connection is ok.
     */
    public static void connectionOK() {
        System.out.println("Connection is OK!");
    }

    /**
     * Start game.
     */
    public static void startGameMessage() {
        System.out.println("Enjoy your game :)");
    }

    /**
     * Error in streams.
     */
    public static void errorInStreams() {
        System.out.println("Oops.. Something goes wrong with steams");
    }

    /**
     * Get start data from server.
     */
    public static void getStartDataFromServer() {
        System.out.println("...start receiving data to server...");
    }

    /**
     * Send start data to server.
     */
    public static void sendStartDataToServer() {
        System.out.println("...start sending data to server...");
    }

    /**
     * Say message.
     *
     * @param message Message to say.
     */
    public static void say(String message) {
        System.out.println(message);
    }

    /**
     * Start new game.
     */
    public static void startNewGameInstruction() {
        System.out.println("If you want new game enter " + ServerSettings.getNewGameKey() + ", else any key");
    }

    /**
     * Start new game.
     */
    public static void cannotStartNewGame() {
        System.out.println("Cannot start new game :(");
    }

    /**
     * Menu for show results.
     */
    public static void showResults() {
        System.out.println("if you want to see top 10 games enter 1");
    }

    /**
     * Waiting for server start.
     */
    public static void waitForStart() {
        System.out.println("...wait for server start game...");
    }
}
