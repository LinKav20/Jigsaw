package com.flinkou.demo;

import com.flinkou.demo.ui.services.GameService;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application.
 */
public class StartApplication extends Application {
    public static int gameTime;
    public static int seed;
    public static String[] names;
    private static GameService gs;

    /**
     * Start game.
     *
     * @param stage Stage to load.
     * @throws IOException Exception.
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(createContent()));
        stage.setResizable(false);
        stage.show();
    }

    public static GameService getGameService() {
        return gs;
    }

    /**
     * Start module.
     *
     * @param args Start args.
     */
    public static void main(String[] args) {
        gameTime = Integer.parseInt(args[0]);
        seed = Integer.parseInt(args[1]);
        names = new String[args.length - 2];
        System.arraycopy(args, 2, names, 0, args.length - 2);
        launch();
    }

    /**
     * Create content of application.
     *
     * @return Parent content.
     */
    private Parent createContent() {
        GameService gameService = new GameService();
        gs = gameService;
        return gameService.createContent(gameTime, seed, names);
    }
}
