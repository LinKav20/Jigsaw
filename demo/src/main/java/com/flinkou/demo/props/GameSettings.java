package com.flinkou.demo.props;

import javafx.scene.paint.Color;

/**
 * Game settings static class.
 */
public class GameSettings {
    // X count of field cells.
    private final static int xCount = 9;
    // Y count of field cells.
    private final static int yCount = 9;
    // X size of cell.
    private final static int xSize = 40;
    // Y size of cell.
    private final static int ySize = 40;
    // Width of visual field.
    private final static int width = ySize * yCount;
    // Height of visual field.
    private final static int height = xSize * xCount;
    // Figure X position.
    private final static int figurePositionX = 13;
    // Figure Y position.
    private final static int figurePositionY = 6;

    // Color of cells stroke.
    private final static Color strokeColor = Color.SNOW;
    // Color of figure cells.
    private final static Color cellFigureColor = Color.rgb(234, 0, 97);
    // Color of field cells.
    private final static Color cellFieldColor = Color.rgb(145, 115, 182);
    // Color of empty cells.
    private final static Color clearCellColor = Color.rgb(115, 154, 182, 0.75);

    /**
     * Get X count of field cells.
     *
     * @return X count of field cells.
     */
    public static int getXCount() {
        return xCount;
    }

    /**
     * Get Y count of field cells.
     *
     * @return Y count of field cells.
     */
    public static int getYCount() {
        return yCount;
    }

    /**
     * Get X size of cell.
     *
     * @return X size of cell.
     */
    public static int getXSize() {
        return xSize;
    }

    /**
     * Get Y size of cell.
     *
     * @return Y size of cell.
     */
    public static int getYSize() {
        return ySize;
    }

    /**
     * Get width.
     *
     * @return Width.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Get height.
     *
     * @return height.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Get cell figure color.
     *
     * @return Cell figure color.
     */
    public static Color getCellFigureColor() {
        return cellFigureColor;
    }

    /**
     * Get cell field color.
     *
     * @return Cell field color.
     */
    public static Color getCellFieldColor() {
        return cellFieldColor;
    }

    /**
     * Get stroke cell color.
     *
     * @return Stroke cell color.
     */
    public static Color getStrokeColor() {
        return strokeColor;
    }

    /**
     * Get figure position X;
     *
     * @return Figure position X;
     */
    public static int getFigurePositionX() {
        return figurePositionX;
    }

    /**
     * Get figure position Y;
     *
     * @return Figure position Y;
     */
    public static int getFigurePositionY() {
        return figurePositionY;
    }

    /**
     * Get color of empty sell.
     *
     * @return Color of empty cell.
     */
    public static Color getClearCellColor() {
        return clearCellColor;
    }
}
