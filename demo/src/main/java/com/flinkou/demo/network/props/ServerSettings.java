package com.flinkou.demo.network.props;

/**
 * Server Settings.
 */
public class ServerSettings {
    // Host of the game.
    private final static String host = "localhost";
    // Port of the game.
    private static int port = -0000;
    // Database prefix name for pattern.
    private final static String dataBasePrefixName = "jdbcDemoDB";

    // Status for error connection game.
    private final static int errorConnectionStatus = -8;
    // Status for correct finish.
    private final static int finishStatus = 2;

    // Pattern for terminate program.
    private final static String connectionTerminatedPattern = "TERMINATE";
    // Pattern for finish game.
    private final static String finishGamePattern = "FINISH";
    // Pattern for score.
    private final static String scorePattern = "SCORE";
    // Pattern for time.
    private final static String timePattern = "TIME";
    // Key for new game.
    private final static String newGameKey = "start";
    // Pattern for start game.
    private final static String startPattern = "START";

    /**
     * Get game port.
     *
     * @return Port.
     */
    public static int getPort() {
        return port;
    }

    /**
     * Get host.
     *
     * @return Host.
     */
    public static String getHost() {
        return host;
    }

    /**
     * Set port.
     *
     * @param newPort New port.
     */
    public static void setPost(int newPort) {
        port = newPort;
    }

    /**
     * Get error connection status.
     *
     * @return Error connection status.
     */
    public static int getErrorConnectionStatus() {
        return errorConnectionStatus;
    }

    /**
     * Get connection terminate pattern.
     *
     * @return Connection terminate pattern.
     */
    public static String getConnectionTerminatedPattern() {
        return connectionTerminatedPattern;
    }

    /**
     * Get finish pattern.
     *
     * @return Finish pattern.
     */
    public static String getFinishGamePattern() {
        return finishGamePattern;
    }

    /**
     * Get score pattern.
     *
     * @return Score pattern.
     */
    public static String getScorePattern() {
        return scorePattern;
    }

    /**
     * Get time pattern.
     *
     * @return Time pattern.
     */
    public static String getTimePattern() {
        return timePattern;
    }

    /**
     * Get finish status.
     *
     * @return Finish status.
     */
    public static int getFinishStatus() {
        return finishStatus;
    }

    /**
     * Get new game key.
     *
     * @return Game key.
     */
    public static String getNewGameKey() {
        return newGameKey;
    }

    /**
     * Get pattern for database name.
     *
     * @return Pattern for database name.
     */
    public static String getPatternForDBName() {
        return dataBasePrefixName;
    }

    public static String getStartPattern(){
        return startPattern;
    }
}
