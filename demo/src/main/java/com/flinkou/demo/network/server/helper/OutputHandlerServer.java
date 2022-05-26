package com.flinkou.demo.network.server.helper;

import com.flinkou.demo.network.props.ServerSettings;

public class OutputHandlerServer {
    /**
     * Welcome message.
     */
    public static void welcomeMessage() {
        System.out.println("Hi there! You are server.");
    }

    /**
     * Game type instruction.
     */
    public static void gameTypeInstruction() {
        System.out.println("Please choose game type: (1) alone or (2) with some one\nEnter only number of your choice.");
    }

    /**
     * Game time instruction.
     */
    public static void gameTimeInstruction() {
        System.out.println("Please enter total game time in seconds.");
    }

    /**
     * Server settings.
     */
    public static void serverSettings() {
        System.out.println("Your host is: " + ServerSettings.getHost() + "\nPlease enter port of game.");
    }

    /**
     * Error connection.
     */
    public static void errorConnection() {
        System.out.println("Oops... Something went wrong with connection");
    }

    /**
     * Bye message.
     */
    public static void byeMessage() {
        System.out.println("Bye!");
    }

    /**
     * Error connection client.
     */
    public static void errorConnectionClient() {
        System.out.println("Client cannot connect to server");
    }

    /**
     * Name instruction.
     */
    public static void nameInstruction() {
        System.out.println("Enter your name");
    }

    /**
     * Start single game.
     */
    public static void startSingleGameMessage() {
        System.out.println("Enjoy single game :)");
    }

    /**
     * Start multi game.
     */
    public static void startMultiGameMessage() {
        System.out.println("Enjoy multi game :)");
    }

    /**
     * Client connected.
     */
    public static void clientConnected() {
        System.out.println("Client successfully connected!");
    }

    /**
     * Wait client.
     */
    public static void waitClient() {
        System.out.println("...wait for client connection...");
    }

    /**
     * Error in streams.
     */
    public static void errorInStreams() {
        System.out.println("Oops... Something goes wrong with steams");
    }

    /**
     * Send data to client.
     */
    public static void giveDataToClient() {
        System.out.println("...sending data to client...");
    }

    /**
     * Get start data from client.
     */
    public static void getStartDataFromClient() {
        System.out.println("...receiving data from client...");
    }

    /**
     * Client finish game.
     */
    public static void clientIsFinished() {
        System.out.println("Client is finished game!");
    }

    /**
     * Results with opponent.
     *
     * @param scoreFirst  First player score.
     * @param timeFirst   First player time.
     * @param scoreSecond Second player score.
     * @param timeSecond  Second player time.
     */
    public static void resultsWithOpponent(int scoreFirst, int timeFirst, int scoreSecond, int timeSecond) {
        String res = String.format("Game finished with your score %d with time %d and opponent score %d and time %d",
                scoreFirst, timeFirst, scoreSecond, timeSecond);
        System.out.println(res);
    }


    /**
     * this player results.
     *
     * @param score Score player.
     * @param time  Time player.
     */
    public static void results(int score, int time) {
        String res = String.format("Game finished with your score %d with time %d",
                score, time);
        System.out.println(res);
    }

    /**
     * Win message.
     */
    public static void win() {
        System.out.println("!!! You win !!!");
    }

    /**
     * Lose message.
     */
    public static void lose() {
        System.out.println("You lose");
    }

    /**
     * Client disconnect.
     */
    public static void clientDisconnect() {
        System.out.println("Oops... Client disconnect");
    }

    /**
     * Start new game instruction.
     */
    public static void startNewGameInstruction() {
        System.out.println("If you want new game enter " + ServerSettings.getNewGameKey() + ", else any key");
    }

    /**
     * Resume or end
     */
    public static void resumeOrEnd() {
        System.out.println("You can (1) resume or (2) end the game.\nPlease enter only number");
    }

    /**
     * Try again.
     */
    public static void tryAgain() {
        System.out.println("Try again!");
    }

    /**
     * Start new game.
     */
    public static void cannotStartNewGame() {
        System.out.println("Cannot start new game :(");
    }

    /**
     * Printing some string.
     *
     * @param str String with message.
     */
    public static void say(String str) {
        System.out.println(str);
    }

    /**
     * Show terminal menu.
     */
    public static void menu() {
        System.out.println("(1) score or (2) start");
    }
}


