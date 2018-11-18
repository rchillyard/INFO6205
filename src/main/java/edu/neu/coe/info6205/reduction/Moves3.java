package edu.neu.coe.info6205.reduction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is from a problem in LeetCode: 780 Reaching Points (Hard)
 */
public class Moves3 implements Moves {

    private final Point s;

    public Moves3(Point s) {
        this.s = s;
    }

    public Moves3(int x, int y) {
        this(new Point(x,y));
    }

    public boolean valid(Point t) {
        Point p = t;
        while (true) {
            // DO NOT PUBLISH THIS IN PUBLIC REPO
            if (p.x==s.x) return p.y == s.y || (p.y - s.y) % s.x == 0; // Vertically aligned area
            if (p.y==s.y) return (p.x - s.x) % s.y == 0; // Horizontally aligned area
            if (p.x<s.x || p.y<s.y) return false; // Missed area
            p = move (p, true); // Area needing at least one more move.
        }
    }

    /**
     * This method defines the possible moves from point p
     *
     * @param p     the point
     * @param which ignored
     * @return the point we moved to
     */
    @Override
    public Point move(Point p, boolean which) {
        return (p.y > p.x) ? new Point(p.x, p.y - p.x) : new Point(p.x - p.y, p.y);
    }

    public boolean valid(int x, int y) {
         return valid(new Point(x,y));
    }
}
