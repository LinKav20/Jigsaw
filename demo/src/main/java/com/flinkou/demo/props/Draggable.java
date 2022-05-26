package com.flinkou.demo.props;

import javafx.scene.Node;

/**
 * Make draggable properties.
 */
public class Draggable {
    // Mouse x coordinate.
    private double mouseX;
    // Mouse y coordinate.
    private double mouseY;

    /**
     * Make node draggable.
     *
     * @param node Node to make draggable.
     */
    public void makeDraggable(Node node) {
        node.setOnMousePressed(mouseEvent -> {
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseY);
        });
    }

    public void disableDraggable(Node node) {
        node.setOnMousePressed(mouseEvent -> {
        });

        node.setOnMouseDragged(mouseEvent -> {
        });
    }
}
