package com.flinkou.demo.core.models;

import java.util.Objects;

/**
 * Entity of coordinate.
 */
public record Coordinate(int x, int y) {
    /**
     * Get x point of coordinate.
     *
     * @return X.
     */
    public int X() {
        return x;
    }

    /**
     * Get y point of coordinate.
     *
     * @return Y.
     */
    public int Y() {
        return y;
    }

    /**
     * Compare to this coordinate.
     *
     * @param o New object.
     * @return True if its equals, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    /**
     * Get hash code of coordinate.
     *
     * @return Hash code of coordinate.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
