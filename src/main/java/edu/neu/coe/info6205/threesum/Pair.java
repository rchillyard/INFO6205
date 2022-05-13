package edu.neu.coe.info6205.threesum;

import java.util.Objects;

/**
 * A pair of two ints.
 */
class Pair implements Comparable<Pair> {
    public int sum() {
        return x + y ;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair triple = (Pair) o;
        return x == triple.x && y == triple.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Pair o) {
        int cf1 = this.x - o.x;
        if (cf1 != 0) return cf1;
        return this.y - o.y;
    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    final int x;
    final int y;
}
