package edu.neu.coe.info6205.game.generics;

import java.util.Objects;

public class GridPosition {
    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    final int x;
    final int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridPosition that = (GridPosition) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "GridPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
