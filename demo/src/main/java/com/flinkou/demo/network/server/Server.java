package com.flinkou.demo.network.server;

import com.flinkou.demo.StartApplication;
import com.flinkou.demo.databases.DataBase;
import com.flinkou.demo.enums.CloseType;
import com.flinkou.demo.network.props.GameMode;
import com.flinkou.demo.network.props.ServerSettings;
import com.flinkou.demo.network.server.helper.InputHandlerServer;
import com.flinkou.demo.network.server.helper.OutputHandlerServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * Server class.
 */
public class Server {
    // Server socket to connection.
    private ServerSocket serverSocket;
    // Client socket.
    private Socket socket;
    // Names of players.
    private String serverName, clientName;

    // Game seed for random to make same sequence.
    private final int seed;

    // Data output stream to communicate with client.
    private DataOutputStream dos;
    // Data input stream to communicate with client.
    private DataInputStream dis;

    // Vars of game.
    private int serverScore, clientScore, serverTime, clientTime;
    private boolean serverFinish = false, clientFinish = false;

    // Game mode.
    private GameMode gameMode;

    // Database.
    private DataBase db;

    // Default constructor.
    public Server() {
        seed = getSeed();
    }

    /**
     * Start server.
     */
    public void start() {
        OutputHandlerServer.welcomeMessage();
        OutputHandlerServer.nameInstruction();
        serverName = InputHandlerServer.getName();
        OutputHandlerServer.gameTypeInstruction();
        int gameType = InputHandlerServer.getGameType();
        startGame(gameType);
    }

    /**
     * Start game.
     *
     * @param type Game type.
     */
    private void startGame(int type) {
        OutputHandlerServer.gameTimeInstruction();
        int gameTimeInSec = InputHandlerServer.getGameTimeInSeconds();
        switch (type) {
            case 1 -> startSingleGame(gameTimeInSec);
            case 2 -> startMultiGame(gameTimeInSec);
            default -> throw new RuntimeException("Error: Incorrect game type");
        }
    }

    /**
     * Start single game.
     *
     * @param time Time of the game
     */
    private void startSingleGame(int time) {
        gameMode = GameMode.SINGLE;
        connectToDatabase();
        openMenuForSingleGame(time);
    }

    /**
     * Menu.
     *
     * @param time Time of the game.
     */
    private void openMenuForSingleGame(int time) {
        OutputHandlerServer.menu();
        int menuOption = InputHandlerServer.getMenuOption();
        switch (menuOption) {
            case 1 -> {
                receiveTOPGames();
                openMenuForSingleGame(time);
            }
            case 2 -> {
                OutputHandlerServer.startSingleGameMessage();
                launchGame(new String[]{Integer.toString(time), Integer.toString(seed), serverName});
            }
            default -> throw new RuntimeException("Something went wrong");
        }
    }

    /**
     * Start multi game.
     *
     * @param time Time of the game
     */
    private void startMultiGame(int time) {
        gameMode = GameMode.MULTI;
        setServerSettings();
        OutputHandlerServer.waitClient();
        waitForClient();
        OutputHandlerServer.clientConnected();
        connectToDatabase();
        sendStartDataToClient(time);
        receiveStartDataFromClient();
        listenClient();
        openMenu(time);
    }

    /**
     * Menu.
     *
     * @param time Time of the game.
     */
    private void openMenu(int time) {
        OutputHandlerServer.menu();
        int menuOption = InputHandlerServer.getMenuOption();
        switch (menuOption) {
            case 1 -> showScores(time);
            case 2 -> loadGame(time);
            default -> throw new RuntimeException("Something went wrong");
        }
    }

    /**
     * Show top 10 games.
     *
     * @param time Time of the game.
     */
    private void showScores(int time) {
        receiveTOPGames();
        openMenu(time);
    }

    /**
     * Load game.
     *
     * @param time Time of the game.
     */
    private void loadGame(int time) {
        sendStart();
        OutputHandlerServer.startMultiGameMessage();
        launchGame(new String[]{Integer.toString(time),
                Integer.toString(seed),
                serverName, clientName});
    }

    /**
     * Send start message to the client.
     */
    private void sendStart() {
        OutputHandlerServer.giveDataToClient();
        try {
            dos.writeUTF(ServerSettings.getStartPattern());
        } catch (IOException e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            sendTerminate();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Get top 10 game from database.
     */
    public void receiveTOPGames() {
        String top = db.getTOPTen();
        OutputHandlerServer.say(top);
    }

    /**
     * Listen client.
     */
    private void listenClient() {
        Thread listener = new Thread(this::listeningFunction);
        listener.start();
    }

    /**
     * Function for listening client.
     */
    private void listeningFunction() {
        while (true) {
            try {
                String line = dis.readUTF();
                checkTerminate(line);
                checkFinish(line);
                checkScore(line);
                checkTime(line);
                getScore();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Send score to client.
     *
     * @param verdict Verdict of the game.
     */
    private void sendScore(String verdict) {
        if (clientFinish && serverFinish) {
            try {
                String data = String.format("%s %s",
                        ServerSettings.getScorePattern(), InputHandlerServer.getScoreMessage(serverScore, serverTime, clientScore, clientTime));
                dos.writeUTF(data + "\n" + verdict);
            } catch (IOException e) {
                OutputHandlerServer.errorConnection();
                OutputHandlerServer.byeMessage();
                sendTerminate();
                System.exit(ServerSettings.getErrorConnectionStatus());
            }
        }
    }

    /**
     * Get score from client.
     */
    private void getScore() {
        if (serverFinish && clientFinish) {
            OutputHandlerServer.resultsWithOpponent(serverScore, serverTime, clientScore, clientTime);
            db.insertScore(clientName, clientScore, clientTime);

            String resultOpponent = "!!! You win !!!";

            if (serverScore > clientScore) {
                OutputHandlerServer.win();
                resultOpponent = "You lose :(";
            } else if (serverScore == clientScore) {
                if (serverTime <= clientTime) {
                    OutputHandlerServer.win();
                    resultOpponent = "You lose :(";
                } else {
                    OutputHandlerServer.lose();
                }
            } else {
                OutputHandlerServer.lose();
            }

            sendScore(resultOpponent);
            endGame();
        }
    }

    /**
     * End game.
     */
    private void endGame() {
        db.close();
        OutputHandlerServer.startNewGameInstruction();
        String des = InputHandlerServer.newGame();
        if (Objects.equals(des, ServerSettings.getNewGameKey())) {
            // StartServer.main(new String[]{""});
            OutputHandlerServer.cannotStartNewGame();
        } else {
            OutputHandlerServer.byeMessage();
            System.exit(ServerSettings.getFinishStatus());
        }
    }

    /**
     * Check time pattern.
     *
     * @param line Pattern line.
     */
    private void checkTime(String line) {
        String[] data = line.split(" ");
        if (Objects.equals(data[0], ServerSettings.getTimePattern())) {
            clientTime = InputHandlerServer.getIntFromString(data[1]);
        }
    }

    /**
     * Check score pattern.
     *
     * @param line Pattern line.
     */
    private void checkScore(String line) {
        String[] data = line.split(" ");
        if (Objects.equals(data[0], ServerSettings.getScorePattern())) {
            clientScore = InputHandlerServer.getIntFromString(data[1]);
        }
    }

    /**
     * Check finish pattern.
     *
     * @param line Pattern line.
     */
    private void checkFinish(String line) {
        if (Objects.equals(line, ServerSettings.getFinishGamePattern())) {
            OutputHandlerServer.clientIsFinished();
            clientFinish = true;
        }
    }

    /**
     * Check terminate pattern.
     *
     * @param line Pattern line.
     */
    private void checkTerminate(String line) {
        if (Objects.equals(line, ServerSettings.getConnectionTerminatedPattern())) {
            pauseGame();
        }
    }

    /**
     * Pause game.
     */
    private void pauseGame() {
        OutputHandlerServer.clientDisconnect();
        OutputHandlerServer.resumeOrEnd();
        StartApplication.getGameService().pauseGame();
        int des = InputHandlerServer.resumeOrEnd();
        switch (des) {
            case 1 -> StartApplication.getGameService().resumeGame();
            case 2 -> {
                getResults();
                endGame();
            }
            default -> throw new RuntimeException("Incorrect format type.");
        }
    }

    /**
     * Receive data from client.
     */
    private void receiveStartDataFromClient() {
        OutputHandlerServer.getStartDataFromClient();
        try {
            clientName = dis.readUTF();
        } catch (Exception e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            sendTerminate();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Send data to client.
     *
     * @param time Time of the game.
     */
    private void sendStartDataToClient(int time) {
        OutputHandlerServer.giveDataToClient();
        try {
            dos.writeUTF(serverName);
            dos.writeInt(time);
            dos.writeInt(seed);
            dos.writeUTF(db.getTOPTen());
        } catch (IOException e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            sendTerminate();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Launch game.
     *
     * @param args Args for game.
     */
    private void launchGame(String[] args) {
        Thread game = new Thread(() -> StartApplication.main(args));
        game.start();

        try {
            game.join();
            checkCloseType();
            getResults();
            getScore();
            if (gameMode == GameMode.SINGLE) {
                endGame();
            }
        } catch (InterruptedException e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Check close type of application.
     */
    private void checkCloseType() {
        if (StartApplication.getGameService().getCloseType() == CloseType.CROSS) {
            if (gameMode == GameMode.MULTI) {
                sendTerminate();
            }
            endGame();
        }
    }

    /**
     * Get results from application.
     */
    private void getResults() {
        serverScore = StartApplication.getGameService().getScore();
        serverTime = StartApplication.getGameService().getTime();
        serverFinish = true;
        OutputHandlerServer.results(serverScore, serverTime);
        db.insertScore(serverName, serverScore, serverTime);
    }

    /**
     * Set server settings.
     */
    private void setServerSettings() {
        OutputHandlerServer.serverSettings();
        ServerSettings.setPost(InputHandlerServer.getPort());

        try {
            serverSocket = new ServerSocket(ServerSettings.getPort());
        } catch (IOException e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Connect to database and create tables.
     */
    private void connectToDatabase() {
        db = new DataBase(ServerSettings.getPatternForDBName() + ServerSettings.getPort());
        db.connect();
        db.createTable();
    }

    /**
     * Wait for client.
     */
    private void waitForClient() {
        try {
            socket = serverSocket.accept();
            setStreams();
        } catch (IOException e) {
            OutputHandlerServer.errorConnectionClient();
            OutputHandlerServer.byeMessage();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Set streams connection.
     */
    private void setStreams() {
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            OutputHandlerServer.errorInStreams();
            OutputHandlerServer.byeMessage();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Get seed for game.
     *
     * @return Int seed.
     */
    private int getSeed() {
        return (int) System.currentTimeMillis();
    }

    /**
     * Sent terminate message for client.
     */
    private void sendTerminate() {
        try {
            dos.writeUTF(ServerSettings.getConnectionTerminatedPattern());
        } catch (IOException e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }
}
