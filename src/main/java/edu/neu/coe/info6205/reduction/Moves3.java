package edu.neu.coe.info6205.reduction;

/**
 * This is from a problem in LeetCode: 780 Reaching Points (Hard)
 */
public class Moves3 implements Moves {

    private final Point s;

    public Moves3(Point s) {
        this.s = s;
    }

    public Moves3(int x, int y) {
        this(new Point(x, y));
    }

    public boolean valid(Point t) {
        // DO NOT PUBLISH THIS IN PUBLIC REPO!!!!
        Point p = t;
        while (true) {
            // FIXME  Sorry, but you have to do this one yourself! by replacing the following code
             return false;
            // END 
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
        return valid(new Point(x, y));
    }
}
