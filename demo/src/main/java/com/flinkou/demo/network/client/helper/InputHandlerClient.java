package com.flinkou.demo.network.client.helper;

import com.flinkou.demo.network.server.helper.OutputHandlerServer;

import java.util.Scanner;

/**
 * Class for read all data from console.
 */
public class InputHandlerClient {
    // Scanner for reading.
    private static Scanner input = new Scanner(System.in);

    /**
     * Read port from console.
     *
     * @return Int port.
     */
    public static int getPort() {
        updateScanner();
        String line = input.nextLine();
        int answer = 0;
        try {
            answer = Integer.parseInt(line);
            if (answer < 1 || answer > 65256) {
                throw new RuntimeException("Value is not correct");
            }
        } catch (Exception e) {
            OutputHandlerServer.tryAgain();
            answer = getPort();
        }
        return answer;
    }

    /**
     * Get name from console.
     *
     * @return Strong name.
     */
    public static String getName() {
        return input.nextLine();
    }

    /**
     * Get decision from user.
     *
     * @return String from user.
     */
    public static String getDes() {
        updateScanner();
        return input.nextLine();
    }

    /**
     * Get command for new game.
     *
     * @return String user respond.
     */
    public static String newGame() {
        updateScanner();
        return input.nextLine();
    }

    /**
     * Update scanner.
     */
    private static void updateScanner() {
        input = new Scanner(System.in);
    }
}
