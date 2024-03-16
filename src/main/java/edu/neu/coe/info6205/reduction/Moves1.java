package edu.neu.coe.info6205.reduction;

/**
 * This is from a problem in LeetCode: 780 Reaching Points (Hard)
 */
public class Moves1 implements Moves {

    private final int tx;
    private final int ty;

    public Moves1(int tx, int ty) {
        this.tx = tx;
        this.ty = ty;
    }

    public boolean valid(int x, int y) {
        return valid(new Point(x, y));
    }

    public boolean valid(Point p) {
        if (p.x == tx && p.y == ty) return true;
        //noinspection SimplifiableIfStatement
        if (p.x > tx || p.y > ty) return false;
        return valid(p.x, p.x + p.y) || valid(p.x + p.y, p.y);
    }

    public Point move(Point p, boolean which) {
        return null;
    }
}