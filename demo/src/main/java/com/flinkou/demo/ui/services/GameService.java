package com.flinkou.demo.ui.services;

import com.flinkou.demo.enums.CloseType;
import com.flinkou.demo.enums.FigureState;
import com.flinkou.demo.enums.GameState;
import com.flinkou.demo.core.services.GameLogic;
import com.flinkou.demo.ui.models.Field;
import com.flinkou.demo.ui.models.Figure;
import com.flinkou.demo.ui.models.PlayButton;
import com.flinkou.demo.props.GameSettings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Visual game service.
 */
public class GameService {
    // Logic og the game.
    private GameLogic gameLogic;
    // Current figure on space.
    private com.flinkou.demo.core.models.Figure currentFigure;
    // Main space.
    private Pane main;
    // Filed of cells.
    private Field field;
    // Label for score.
    private Label scoreLabel;
    // Label for time.
    private Label timeLabel;
    // Player`s names.
    private Label namesLabel;

    // Button for play game.
    private PlayButton playButton;
    // Button for exit button.
    private Button exitButton;

    // Timer.
    private Timeline timeline;

    // Figure position on main space.
    private int figurePosition;
    // Time of game.
    private int time;

    // To know how the app was closed.
    private CloseType closeType = CloseType.CROSS;

    /**
     * Creates content for the game.
     * @param gameTime Game time in secs.
     * @param seed Seed for random.
     * @param names Names of players.
     * @return Content for the game.
     */
    public Parent createContent(int gameTime, int seed, String[] names) {
        gameLogic = new GameLogic(gameTime, seed, names);
        initAll();
        addAllToKids();

        main.setOnMouseReleased(mouseEvent -> {
            if (playButton.getGameState() == GameState.IN_PROGRESS) {
                Figure myFigure = ((Figure) (main.getChildren().get(figurePosition)));
                if (myFigure.getFigureState() == FigureState.TRY_TO_PUT) {
                    tryToPutFigure(myFigure);
                }
            }
        });

        actionButtonState(playButton.getGameState());
        playButton.changeState();
        return main;
    }

    /**
     * Get game score.
     *
     * @return Game score.
     */
    public int getScore() {
        return gameLogic.getScore();
    }

    /**
     * Get close type.
     *
     * @return Close type application.
     */
    public CloseType getCloseType() {
        return closeType;
    }

    /**
     * Get game time.
     *
     * @return Game time.
     */
    public int getTime() {
        return time;
    }

    public void exitGame() {
        closeType = CloseType.BUTTON;
        Platform.exit();
    }

    /**
     * Pause game.
     */
    public void pauseGame() {
        timeline.stop();
        ((Figure) (main.getChildren().get(figurePosition))).disableDraggable();
        System.out.println("1");
    }

    /**
     * Resume game.
     */
    public void resumeGame() {
        timeline.play();
        ((Figure) (main.getChildren().get(figurePosition))).makeDraggable();
        System.out.println("2");
    }

    /**
     * Action on play button clicked.
     *
     * @param gameState Current game state.
     */
    private void actionButtonState(GameState gameState) {
        switch (gameState) {
            case NOT_STARTED -> startGame();
            case IN_PROGRESS -> finishGame();
        }
    }

    /**
     * Start the game.
     */
    private void startGame() {
        gameLogic.startGame();
        field.clear();
        time = 0;
        scoreLabel.setText("Figures: " + gameLogic.getScore());
        timeLabel.setText("Time: " + timeFormat(time) + " from " + timeFormat(gameLogic.getMaxGameTimeInSec()));
        exitButton.setVisible(false);
        timeline.play();
        getNewFigure();
    }

    /**
     * Try to put figure on the field.
     *
     * @param figure Figure to put.
     */
    private void tryToPutFigure(Figure figure) {
        if (gameLogic.canPutFigure(figure.getFiledCoordinateToPut(), figure.getFigureCoordinateToPut(), currentFigure)) {
            var figureCoordinatesOnField =
                    gameLogic.putFigure(figure.getFiledCoordinateToPut(), figure.getFigureCoordinateToPut(), currentFigure);
            field.changeColor(figureCoordinatesOnField);
            main.getChildren().remove(figurePosition);
            scoreLabel.setText("Figures: " + gameLogic.getScore());
            getNewFigure();
        } else {
            figure.setStartPosition();
        }
    }

    /**
     * Get new figure.
     */
    private void getNewFigure() {
        currentFigure = gameLogic.getFigure();
        Figure figure = new Figure(currentFigure);
        figure.makeDraggable();
        main.getChildren().add(figure);
    }

    /**
     * Finish the game.
     */
    private void finishGame() {
        timeline.stop();
        currentFigure = null;
        main.getChildren().remove(figurePosition);
        exitGame();
    }

    /**
     * Init play button.
     */
    private void initButton() {
        playButton = new PlayButton();
        playButton.setLayoutX(12 * GameSettings.getXSize());
        playButton.setLayoutY(GameSettings.getYSize());

        playButton.setOnMouseClicked(mouseEvent -> actionButtonState(playButton.getGameState()));
    }

    /**
     * Init score label
     */
    private void initLabel() {
        scoreLabel = new Label();
        scoreLabel.setText("Not started");
        scoreLabel.setLayoutX(12 * GameSettings.getXSize());
        scoreLabel.setLayoutY(3.5 * GameSettings.getYSize());
    }

    /**
     * Init time label.
     */
    private void initTimeLabel() {
        timeLabel = new Label();
        timeLabel.setText("Time: --:--:--");
        timeLabel.setLayoutX(12 * GameSettings.getXSize());
        timeLabel.setLayoutY(3 * GameSettings.getYSize());
    }

    /**
     * Init exit button.
     */
    private void initExitButton() {
        exitButton = new Button("exit");
        exitButton.setLayoutX(15 * GameSettings.getXSize());
        exitButton.setLayoutY(GameSettings.getYSize());

        exitButton.setOnMouseClicked(mouseEvent -> exitGame());
    }

    /**
     * Init all nodes.
     */
    private void initAll() {
        initButton();
        initLabel();
        initExitButton();
        initTimeLabel();
        initTimer();
        initNamesLabel();
        main = new Pane();
        field = new Field();
    }

    /**
     * Init labels for names.
     */
    private void initNamesLabel() {
        namesLabel = new Label();
        namesLabel.setText(gameLogic.getNames());
        namesLabel.setLayoutX(12 * GameSettings.getXSize());
        namesLabel.setLayoutY(2.2 * GameSettings.getYSize());
    }

    /**
     * Init timer.
     */
    private void initTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            time++;
            timeLabel.setText("Time: " + timeFormat(time) + " from " + timeFormat(gameLogic.getMaxGameTimeInSec()));
            checkIfOver();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Check is times up.
     */
    public void checkIfOver() {
        if (gameLogic.gameOver(time)) {
            finishGame();
        }
    }

    /**
     * Add all nodes to main space.
     */
    private void addAllToKids() {
        main.getChildren().add(field);
        main.getChildren().add(playButton);
        main.getChildren().add(exitButton);
        main.getChildren().add(scoreLabel);
        main.getChildren().add(timeLabel);
        main.getChildren().add(namesLabel);

        figurePosition = main.getChildren().size();
    }

    /**
     * Make from seconds string time format.
     *
     * @param time Game time in seconds.
     * @return String time format.
     */
    private String timeFormat(int time) {
        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
