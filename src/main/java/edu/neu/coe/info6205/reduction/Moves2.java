package edu.neu.coe.info6205.reduction;

import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;

/**
 * This is from a problem in LeetCode: 780 Reaching Points (Hard)
 */
public class Moves2 {

    private final Point t;

    public Moves2(Point t) {
        this.t = t;
    }

    public Moves2(int x, int y) {
        this(new Point(x, y));
    }

    public boolean valid(int x, int y) {
        Queue<Point> points = new Queue_Elements<>();
        points.offer(new Point(x, y));
        return inner(points, false);
    }

    private boolean inner(Queue<Point> points, boolean result) {
        if (points.isEmpty()) return result;
        Point x = points.poll();
        if (x.equals(t)) return true;
        if (x.x > t.x || x.y > t.y) return inner(points, false);
        points.offer(move(x, true));
        points.offer(move(x, false));
        return inner(points, result);
    }

    /**
     * This method defines the possible moves from point p
     *
     * @param p     the point
     * @param which determines which of two possible moves we want.
     * @return the point we moved to
     */
    public Point move(Point p, boolean which) {
        return which ? new Point(p.x, p.y + p.x) : new Point(p.x + p.y, p.y);
    }

}