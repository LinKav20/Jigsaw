package com.flinkou.demo.ui.models;

import com.flinkou.demo.enums.GameState;
import javafx.scene.control.Button;

/**
 * Entity of playing button.
 */
public class PlayButton extends Button {
    // Game state on button.
    private GameState gameState = GameState.NOT_STARTED;
    // String for start label.
    private final String toStartGame = "Start game";
    // String for end label.
    private final String toEndGame = "Finish game";

    /**
     * Default constructor.
     */
    public PlayButton() {
        setText(toStartGame);
    }

    /**
     * Get game state.
     *
     * @return Game state.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Change button state.
     */
    public void changeState() {
        switch (gameState) {
            case NOT_STARTED -> {
                gameState = GameState.IN_PROGRESS;
                setText(toEndGame);
            }
            case IN_PROGRESS -> {
                gameState = GameState.NOT_STARTED;
                setText(toStartGame);
            }
        }
    }
}
