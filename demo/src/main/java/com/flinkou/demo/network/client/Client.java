package com.flinkou.demo.network.client;

import com.flinkou.demo.StartApplication;
import com.flinkou.demo.enums.CloseType;
import com.flinkou.demo.network.client.helper.InputHandlerClient;
import com.flinkou.demo.network.client.helper.OutputHandlerClient;
import com.flinkou.demo.network.props.ServerSettings;
import com.flinkou.demo.network.server.helper.OutputHandlerServer;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * Class of Client.
 */
public class Client {
    // Client socket to connect to server.
    private Socket socket;
    // Client name.
    private String clientName;
    // Top 10 games.
    private String top;

    // Data output stream to communicate with server.
    private DataOutputStream dos;
    // Data input stream to communicate with server.
    private DataInputStream dis;

    // Vars of clients stats.
    private int clientScore, clientTime;

    // Arguments for the game.
    private String[] args;

    // Default constructor.
    public Client() {
    }

    /**
     * Start working client.
     */
    public void start() {
        OutputHandlerClient.welcomeMessage();
        OutputHandlerClient.nameInstruction();
        clientName = InputHandlerClient.getName();
        startGame();
    }

    /**
     * Start game.
     */
    private void startGame() {
        connectToServer();
        OutputHandlerClient.connectionOK();
        args = getArgs();
        sendStartDataToServer();
        showResults();
        OutputHandlerClient.waitForStart();
        listenServer();
    }

    /**
     * Show top 10 games.
     */
    private void showResults() {
        OutputHandlerClient.showResults();
        String des = InputHandlerClient.getDes();
        if (Objects.equals(des, "1")) {
            OutputHandlerClient.say(top);
        }
    }

    /**
     * Start listen server.
     */
    private void listenServer() {
        Thread listener = new Thread(this::listeningFunction);
        listener.start();
    }

    /**
     * Listening function to listen server.
     */
    private void listeningFunction() {
        while (true) {
            try {
                String line = dis.readUTF();
                checkTerminate(line);
                checkScore(line);
                checkStart(line);
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Start game message.
     */
    private void checkStart(String line) {
        String[] data = line.split(" ");
        if (Objects.equals(data[0], ServerSettings.getStartPattern())) {
            OutputHandlerClient.startGameMessage();
            launchGame(args);
        }
    }

    /**
     * Check score from string.
     *
     * @param line String line.
     */
    private void checkScore(String line) {
        String[] data = line.split(" ");
        if (Objects.equals(data[0], ServerSettings.getScorePattern())) {
            String[] message = new String[data.length - 1];
            System.arraycopy(data, 1, message, 0, data.length - 1);
            OutputHandlerClient.say(String.join(" ", message));
            endGame();
        }
    }

    /**
     * End game function.
     */
    private void endGame() {
        OutputHandlerClient.startNewGameInstruction();
        String des = InputHandlerClient.newGame();
        if (Objects.equals(des, ServerSettings.getNewGameKey())) {
            // StartClient.main(new String[]{""});
            OutputHandlerClient.cannotStartNewGame();
        } else {
            OutputHandlerClient.byeMessage();
            System.exit(ServerSettings.getFinishStatus());
        }
    }

    /**
     * Check terminate command from line.
     *
     * @param line String line.
     */
    private void checkTerminate(String line) {
        if (Objects.equals(line, ServerSettings.getConnectionTerminatedPattern())) {
            OutputHandlerServer.errorConnection();
            OutputHandlerClient.byeMessage();
            immediatelyCloseProgram();
        }
    }

    /**
     * Sending start data to server.
     */
    private void sendStartDataToServer() {
        OutputHandlerClient.sendStartDataToServer();
        try {
            dos.writeUTF(clientName);
        } catch (IOException e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Make args.
     *
     * @return String array of args.
     */
    private String[] getArgs() {
        OutputHandlerClient.getStartDataFromServer();

        int seed = 0, gameTime = 0;
        String serverName = "";
        try {
            serverName = dis.readUTF();
            gameTime = dis.readInt();
            seed = dis.readInt();
            top = dis.readUTF();
        } catch (Exception e) {
            OutputHandlerClient.errorConnection();
            OutputHandlerClient.byeMessage();
            sendTerminate();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
        return new String[]{Integer.toString(gameTime), Integer.toString(seed), clientName, serverName};
    }

    /**
     * Launch game in thread.
     *
     * @param args Game args.
     */
    private void launchGame(String[] args) {
        Thread game = new Thread(() -> StartApplication.main(args));
        game.start();

        try {
            game.join();
            checkCloseType();
            getResults();
            sendResultsToServer();
        } catch (InterruptedException e) {
            OutputHandlerClient.errorConnection();
            OutputHandlerClient.byeMessage();
            sendTerminate();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Check close type of application.
     */
    private void checkCloseType() {
        if (StartApplication.getGameService().getCloseType() == CloseType.CROSS) {
            sendTerminate();
        }
    }

    /**
     * Get game stats from application.
     */
    private void getResults() {
        clientScore = StartApplication.getGameService().getScore();
        clientTime = StartApplication.getGameService().getTime();
    }

    /**
     * Send results to server.
     */
    private void sendResultsToServer() {
        send(ServerSettings.getScorePattern() + " " + clientScore);
        send(ServerSettings.getTimePattern() + " " + clientTime);
        send(ServerSettings.getFinishGamePattern());
    }

    /**
     * Send random string to server.
     *
     * @param data String data.
     */
    private void send(String data) {
        try {
            dos.writeUTF(data);
        } catch (IOException e) {
            OutputHandlerServer.errorConnection();
            OutputHandlerServer.byeMessage();
            sendTerminate();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Install connection to server.
     */
    private void connectToServer() {
        OutputHandlerClient.serverSettings();
        ServerSettings.setPost(InputHandlerClient.getPort());

        try {
            socket = new Socket(ServerSettings.getHost(), ServerSettings.getPort());
            setStreams();
        } catch (IOException e) {
            OutputHandlerClient.errorConnection();
            OutputHandlerClient.byeMessage();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Configure streams.
     */
    private void setStreams() {
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            OutputHandlerClient.errorInStreams();
            OutputHandlerClient.byeMessage();
            sendTerminate();
            System.exit(ServerSettings.getErrorConnectionStatus());
        }
    }

    /**
     * Close all types of program.
     */
    private void immediatelyCloseProgram() {
        StartApplication.getGameService().exitGame();
        System.exit(ServerSettings.getErrorConnectionStatus());
    }

    /**
     * Send terminate message to server.
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
