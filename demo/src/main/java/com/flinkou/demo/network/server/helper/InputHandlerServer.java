package com.flinkou.demo.network.server.helper;

import java.util.Scanner;

/**
 * Class for read all data from console.
 */
public class InputHandlerServer {
    // Scanner for reading.
    private static Scanner input = new Scanner(System.in);

    /**
     * Read game type.
     *
     * @return Int game type.
     */
    public static int getGameType() {
        updateScanner();
        String line = input.nextLine();
        int answer = 0;
        try {
            answer = Integer.parseInt(line);
            if (answer < 1 || answer > 2) {
                throw new RuntimeException("Value is not correct");
            }
        } catch (Exception e) {
            OutputHandlerServer.tryAgain();
            answer = getGameType();
        }
        return answer;
    }

    /**
     * Read game type.
     *
     * @return Int game type.
     */
    public static int getMenuOption() {
        updateScanner();
        String line = input.nextLine();
        int answer = 0;
        try {
            answer = Integer.parseInt(line);
            if (answer < 1 || answer > 2) {
                throw new RuntimeException("Value is not correct");
            }
        } catch (Exception e) {
            OutputHandlerServer.tryAgain();
            answer = getMenuOption();
        }
        return answer;
    }

    /**
     * Read seconds for game.
     *
     * @return Game time in seconds.
     */
    public static int getGameTimeInSeconds() {
        updateScanner();
        String line = input.nextLine();
        int answer = 0;
        try {
            answer = Integer.parseInt(line);
            if (answer < 0) {
                throw new RuntimeException("Value is not correct");
            }
        } catch (Exception e) {
            OutputHandlerServer.tryAgain();
            answer = getGameTimeInSeconds();
        }
        return answer;
    }

    /**
     * Read port.
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
     * Read name.
     *
     * @return String name.
     */
    public static String getName() {
        updateScanner();
        return input.nextLine();
    }

    /**
     * Transform string to int.
     *
     * @param value String value.
     * @return Int of string.
     */
    public static int getIntFromString(String value) {
        int answer = 0;
        try {
            answer = Integer.parseInt(value);
        } catch (Exception ignored) {
        }
        return answer;
    }

    /**
     * Make string by pattern and params.
     *
     * @param scoreFirst  First player score.
     * @param timeFirst   First player time.
     * @param scoreSecond Second player score.
     * @param timeSecond  Second player time.
     * @return String message.
     */
    public static String getScoreMessage(int scoreFirst, int timeFirst, int scoreSecond, int timeSecond) {
        return String.format("Game finished with your score %d with time %d and opponent score %d and time %d",
                scoreFirst, timeFirst, scoreSecond, timeSecond);
    }

    /**
     * Read command for new game.
     *
     * @return String command.
     */
    public static String newGame() {
        updateScanner();
        return input.nextLine();
    }

    /**
     * Read resume or end game mode.
     *
     * @return Int mode.
     */
    public static int resumeOrEnd() {
        updateScanner();
        String line = input.nextLine();
        int answer = 0;
        try {
            answer = Integer.parseInt(line);
            if (answer < 1 || answer > 2) {
                throw new RuntimeException("Value is not correct");
            }
        } catch (Exception e) {
            OutputHandlerServer.tryAgain();
            answer = resumeOrEnd();
        }
        return answer;
    }

    /**
     * Update scanner.
     */
    private static void updateScanner() {
        input = new Scanner(System.in);
    }
}
