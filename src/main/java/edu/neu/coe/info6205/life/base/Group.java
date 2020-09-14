package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.util.Range;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import static edu.neu.coe.info6205.life.base.Grid.Origin;

/**
 * Class to model a group of cells. Groups may not overlap. If an overlap occurs through expansion, then the groups must merge.
 */
public class Group implements Generational<Group, Void>, Renderable, Countable {

    /**
     * Constructor for a Group with a particular origin and a list of Points.
     *
     * @param generation the generation of this Group.
     */
    public static Group create(long generation, Point point) {
        final List<Point> points = new ArrayList<>();
        points.add(point);
        return new Group(generation, point, points);
    }

    /**
     * Factory method to create a new Group at generation 0, from the given string.
     *
     * @param generation the current generation.
     * @param string     a String, typically from the library of Group patterns.
     * @return a new Group.
     */
    public static Group create(long generation, String string) {
        Group result = new Group(0L);
        if (string == null) throw new LifeException("create: was given null string");
        final boolean ok = result.add(string);
        assert ok : "create: problem adding: " + string;
        return result;
    }

    /**
     * Appends the specified point to the list of points and normalizes the Group.
     *
     * @param point the point to be added in coordinates relative to the Grid.
     * @return true.
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    boolean add(Point point) {
        addPoint(point);
        normalize();
        return true;
    }

    /**
     * Method to add a point expressed in absolute coordinates (relative to Grid).
     *
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     * @return an indication of success.
     */
    boolean add(int x, int y) {
        return add(new Point(x, y));
    }

    /**
     * Method to add points to this Group where the points are expressed as a String.
     *
     * @param s a comma-separated list of coordinate pairs (separated by a space)
     *          with x coming before y.
     * @return an indication of the success of the operation.
     */
    boolean add(String s) {
        return add(Point.points(s));
    }

    /**
     * Method to add a collection of points.
     * <p>
     * CONSIDER invoking the add without the normalize, and then do normalize at the end.
     *
     * @param points the points, in absolute (Grid) coordinates.
     * @return an indication of success.
     */
    boolean add(Iterable<Point> points) {
        boolean r = true;
        for (Point p : points) r = r && addPoint(p);
        normalize();
        return r;
    }

    /**
     * Method to determine if this Group overlaps with group.
     *
     * @param group the other group to be compared with this.
     * @return true if there is an overlap.
     */
    boolean overlap(Group group) {
        if (extent1 == null || group.extent1 == null) return false;
        if (extent2 == null || group.extent2 == null) return false;
        return withinExtents(group.getExtent1()) || withinExtents(group.getExtent2()) || withinExtents(group.getDiagonal(true)) || withinExtents(group.getDiagonal(false));
    }

    /**
     * Method to merge two overlapping groups.
     *
     * @param group the group to merge with this.
     * @return a new Group.
     * @throws LifeException if attempting to merge with self.
     */
    Group merge(Group group) throws LifeException {
        if (group == this) throw new LifeException("cannot merge with self");
        Point newOrigin = origin.compareTo(group.origin) <= 0 ? origin : group.origin;
        Group result = new Group(generation, newOrigin, extent1, extent2, moveCellsRelative(newOrigin));
        group.forEach(p -> result.add(p.relative(newOrigin)));
        return result;
    }

    /**
     * Method to transpose the points of this Group.
     *
     * @return a transposed version of this Group.
     */
    Group transpose() {
        return map(Point::transpose);
    }

    /**
     * Method to transpose the points of this Group.
     *
     * @return a transposed version of this Group.
     */
    Group move(Point vector) {
        return new Group(generation, origin.move(vector), points);
    }

    /**
     * Method to create a new version of this Group but translated by x and y with respect to the Grid.
     *
     * @param x the x coordinate of the move.
     * @param y the y coordinate of the move.
     * @return a new Group, moved according to x and y.
     */
    Group move(int x, int y) {
        return move(new Point(x, y));
    }

    /**
     * Method to create a new generation from this.
     *
     * @param generation the generation id.
     * @return a new Group, which may possibly overlap with other Groups.
     */
    Group newGeneration(long generation) {
        Group result = copy(generation);
        result.applyLifeRules();
        return result;
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Unless otherwise specified by the implementing class,
     * actions are performed in the order of iteration (if an iteration order
     * is specified).  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    void forEach(Consumer<? super Point> action) {
        points.forEach(action);
    }

    /**
     * Method to yield this Group's origin.
     *
     * @return the origin (relative to the Grid).
     */
    Point getOrigin() {
        return origin;
    }

    /**
     * @return a string representation of the object to help with debugging.
     */
    @Override
    public String toString() {
        return "generation " + this.generation + ", origin = " + origin + ", extents = [" + extent1 + ", " + extent2 + "]\n    " + points;
    }

    /**
     * @return a string representation of the object, with all coordinates in absolute terms.
     */
    public String toStringInGrid() {
        return "generation " + this.generation + ": extents = [" + getExtent1() + ", " + getExtent2() + "]\n    " + pointsAbsolute();
    }

    /**
     * Removes the first occurrence of the specified element from cells.
     * Remove does not change the origin or extent but will reduce the count.
     *
     * @param p element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this list
     */
    public boolean remove(Point p) {
        return points.remove(p);
    }

    /**
     * Method to yield the points in this Group with coordinates relative to the Grid.
     *
     * @return a List of points relative to Grid.
     */
    List<Point> pointsAbsolute() {
        List<Point> result = new ArrayList<>();
        forEach(p -> result.add(p.move(origin)));
        return result;
    }

    /**
     * Method to yield the number of points in this Group.
     *
     * @return the number of points.
     */
    @Override
    public int getCount() {
        return points.size();
    }

    /**
     * Method to yield a String which represents the cells of this Group.
     * Cells are marked '*' unless the cell is at the origin, in which case it is marked 'O'.
     *
     * @return a String.
     */
    @Override
    public String render() {
        return doRender(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return pointsAbsolute().equals(group.pointsAbsolute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    /**
     * Method to get the generation of this Group.
     *
     * @return the generation.
     */
    long getGeneration() {
        return generation;
    }

    /**
     * Method to yield a String which represents the cells of this Group.
     * Cells are marked '*' unless the cell is at the origin, in which case it is marked 'O'.
     *
     * @param withOrigin if true, then the location of the origin is shown also.
     * @return a String.
     */
    String doRender(boolean withOrigin) {
        normalize();
        final String result = CellsAndNeighbors.create(this).toString();
        return withOrigin ? result + "Origin: " + origin + "\n" : result;
    }

    // Private methods and fields...

    private boolean addPoint(Point point) {
        Point o = origin != null ? origin : Origin; // CONSIDER may not be necessary
        return points.add(point.relative(o));
    }

    /**
     * Method to create a new Group by mapping the current points.
     *
     * @param f the function to apply to the points.
     * @return a new Group based on the mapped points.
     */
    private Group map(UnaryOperator<Point> f) {
        return new Group(generation, origin, mapPoints(f));
    }

    private List<Point> mapPoints(UnaryOperator<Point> f) {
        final List<Point> mapped = new ArrayList<>();
        points.forEach(p -> mapped.add(p.map(f)));
        return mapped;
    }

    private void normalize() {
        if (points.size() == 0) return;
        forEach(this::updateExtents);
        if (origin != null && points.contains(Origin)) return;  // CONSIDER null check of origin may not be necessary
        updateOrigin(points.get(0));
    }

    private void updateOrigin(Point point) {
        Point o = origin != null ? origin : Origin;  // CONSIDER may not be necessary
        Point vector = point.vector(o);
        origin = point;
        extent1 = extent1.relative(point);
        extent2 = extent2.relative(point);
        points = mapPoints(p -> p.relative(point));
    }

    /**
     * NOTE: this is used by unit tests -- do not eliminate.
     *
     * @param generation the new generation.
     * @param origin     the new origin.
     * @return a new Group based on this, generation, and origin.
     */
    private Group changeOrigin(long generation, Point origin) {
        Point vector = origin.vector(this.origin);
        // TODO use updateOrigin
        return new Group(generation, origin, extent1.move(vector), extent2.move(vector), mapPoints(p -> p.move(vector)));
    }

    private Group copy(long generation) {
        return new Group(generation, origin.copy(), extent1.copy(), extent2.copy(), mapPoints(Point::copy));
    }

    private Point getAbsolute(Point p) {
        return p.move(origin);
    }

    /**
     * This method accounts for the change in origin as we go to the new generation.
     *
     * @param point the point, relative to which, the Points should move.
     * @return a list of Points based on the new coordinates.
     */
    private List<Point> moveCellsRelative(Point point) {
        List<Point> result = new ArrayList<>();
        forEach(p -> result.add(p.relative(point)));
        return result;
    }

    private Point updateCell(int count, boolean onEdge, int i, int j, int[][] grid) {
        // grid indices are in the coordinate system of the cells (points).
        final Point p = new Point(i, j).move(extent1);
        if (onEdge || grid[i - 1][j - 1] == 0)
            if (count == BirthNeighborCount) {
                assert onEdge || !points.contains(p) : "logic error: should not exist: " + p;
                return p;
            } else return null;
        else {
            assert points.contains(p) : "logic error: should exist: " + p;
            final boolean ok = DeathRange.contains(count) || remove(p);
            assert ok : "Problem removing point: " + p;
            return null;
        }
    }

    /**
     * Get the absolute value of the diagonal of the boundary.
     *
     * @param nw if true then the NW corner, else the SE corner.
     * @return a Point.
     */
    private Point getDiagonal(boolean nw) {
        if (nw) return getAbsolute(extent1.move(0, extent2.getY()));
        else return getAbsolute(extent1.move(extent2.getX(), 0));
    }

    private void updateExtents(Point point) {
        if (point == null) throw new LifeException("updateExtents: point is null");
        if (extent2 == null) extent2 = point;
        if (extent1 == null) extent1 = point;
        if (point.getX() >= extent2.getX()) extent2 = new Point(point.getX() + 1, extent2.getY());
        if (point.getY() >= extent2.getY()) extent2 = new Point(extent2.getX(), point.getY() + 1);
        if (point.getX() <= extent1.getX()) extent1 = new Point(point.getX() - 1, extent1.getY());
        if (point.getY() <= extent1.getY()) extent1 = new Point(extent1.getX(), point.getY() - 1);
    }

    /**
     * Method to determine if a point is within this Group.
     *
     * @param point a Point in absolute (Grid) coordinates.
     * @return true if point is within the extents of this Group.
     */
    private boolean withinExtents(Point point) {
        if (point == null) throw new LifeException("withinExtents: point is null");
        if (extent2 == null) return false;
        if (extent1 == null) return false;
        final int cfO = getExtent1().compare(point);
        if (!(cfO == 0 || cfO == 1 || cfO == 3 || cfO == 4)) return false;
        final int cfE = point.compare(getExtent2());
        return cfE == 0 || cfE == 1 || cfE == 3 || cfE == 4;
    }

    /**
     * Method to yield this Group's extent1.
     * TODO make private
     *
     * @return the extent1 (relative to the Grid).
     */
    Point getExtent1() {
        return extent1 != null ? getAbsolute(extent1) : null;
    }

    /**
     * Method to yield this Group's extent2.
     * TODO make private
     *
     * @return the extent2 (relative to the Grid).
     */
    Point getExtent2() {
        return extent2 != null ? getAbsolute(extent2) : null;
    }

    /**
     * This method is used by unit tests (via PrivateMethod Tester).
     * <p>
     * NOTE Do not eliminate this method.
     *
     * @return the points in this Group.
     */
    private Collection<Point> getPoints() {
        return points;
    }
    // which is furthest from the origin of the coordinate system.
    // All cells have negative coordinates compared to extent2.

    private void applyLifeRules() {
        final Collection<Point> points = CellsAndNeighbors.create(this).updateCells();
        // add points after moving relative to origin
        final List<Point> absPoints = new ArrayList<>();
        for (Point p : points) absPoints.add(p.move(origin));
        boolean ok = add(absPoints);
        assert ok : "Problem adding the new points: " + points;
        resetExtents();
    }

    private void resetExtents() {
        extent1 = extent2 = null;
        normalize();
    }

    private static final int BirthNeighborCount = 3;
    private static final int LonelinessNeighborThreshold = 2;
    private static final int OvercrowdingNeighborThreshold = 3;
    private static final Range DeathRange = Range.valueOf(LonelinessNeighborThreshold, OvercrowdingNeighborThreshold);
    private final long generation; // the current generation of this Group.
    private List<Point> points; // the list of non-empty cells within this group (must include one point at the origin).
    private Point origin; // the position of the origin relative to the grid.
    // All cells have coordinates which are relative to the origin.
    private transient Point extent1; // the position of the corner of the enclosing rectangle of this Group,
    // which is closest to the origin of the coordinate system.
    // All cells have positive coordinates compared to extent1.
    private transient Point extent2; // the position of the corner of the enclosing rectangle of this Group,

    /**
     * Base constructor.
     * <p>
     * TODO make this private.
     *
     * @param generation the generation of this Group.
     * @param origin     the origin of this Group relative to the origin of the Grid.
     * @param extent1    the extent1 of this Group (i.e. the SW corner of the boundary).
     * @param extent2    the extent2 of this Group (i.e. the NE corner of the boundary).
     * @param points     a list of points, which are in the coordinate system of this Group.
     */
    Group(long generation, Point origin, Point extent1, Point extent2, List<Point> points) {
        this.generation = generation;
        this.origin = origin;
        this.extent1 = extent1;
        this.extent2 = extent2;
        this.points = points;
    }

    /**
     * Constructor for an empty Group with Grid origin.
     * <p>
     * TODO make this private.
     *
     * @param generation the generation of this Group.
     */
    Group(long generation) {
        this(generation, Origin, new ArrayList<>());
    }

    /**
     * Constructor for a Group with a particular origin and a list of Points.
     * <p>
     * TODO make this private.
     *
     * @param generation the generation of this Group.
     * @param origin     the origin of this Group relative to the Grid (it is required that the origin is one of the points).
     * @param points     the points in this Group, with coordinates relative to the origin (points MUST include 0,0).
     */
    Group(long generation, Point origin, List<Point> points) {
        this(generation, origin, null, null, points);
        forEach(this::updateExtents);
    }

    @Override
    public Group generation(BiConsumer<Long, Void> monitor) {
        monitor.accept(generation, null);
        return newGeneration(generation + 1);
    }

    /**
     * CONSIDER making it a non-static inner class of Group.
     */
    static class CellsAndNeighbors {
        private final int width;
        private final int height;
        private final int[][] cells;
        private final int[][] neighbors;
        private final Group group;

        /**
         * Constructor for a new CellsAndNeighbors object.
         *
         * @param group     the group to which this CellsAndNeighbors object pertains.
         * @param width     the width of the cells array.
         * @param height    the height of the cells array.
         * @param cells     the (live) cells of the current generation.
         * @param neighbors the neighbor cells (with width and height extend one in each direction from cells).
         */
        CellsAndNeighbors(Group group, int width, int height, int[][] cells, int[][] neighbors) {
            this.width = width;
            this.height = height;
            this.cells = cells;
            this.neighbors = neighbors;
            this.group = group;
        }

        static CellsAndNeighbors create(Group group) {
            if (group == null) throw new LifeException("CellsAndNeighbors.create: group must not be null");
            Point extents = group.extent1 != null && group.extent2 != null ? group.extent1.vector(group.extent2) : new Point(1, 1);
            // Height and Width account for the fact that the extents are inclusive.
            int height = extents.getY() - 1;
            int width = extents.getX() - 1;
            // Neighbors is based on a grid that is appropriate for the new generation.
            int[][] neighbors = new int[width + 2][height + 2];
            // LiveCells is based on a grid that is appropriate to the current generation,
            int[][] liveCells = new int[width][height];
            group.forEach(p -> incrementNeighborsAndNoteCell(p.relative(group.extent1), neighbors, liveCells));
            return new CellsAndNeighbors(group, width, height, liveCells, neighbors);
        }

        private static void incrementNeighborsAndNoteCell(Point p, int[][] neighbors, int[][] cells) {
            int px = p.getX();
            int py = p.getY();
            // TODO use withinExtents or similar
            assert px > 0 && px < neighbors.length - 1 : px + " not in (exclusive) range 0.." + (neighbors.length - 1);
            assert py > 0 && py < neighbors[0].length - 1 : py + " not in (exclusive) range 0.." + (neighbors[0].length - 1);
            for (int i = -1; i < 2; i++) {
                final int[] neighborArray = neighbors[i + px];
                for (int j = -1; j < 2; j++)
                    if (i == 0 && j == 0) cells[px - 1][py - 1] = 1;
                    else neighborArray[j + py]++;
            }
        }

        @Override
        public String toString() {
            Point z = group.extent1;
            final StringBuilder sb = new StringBuilder();
            for (int j = height; j > 0; j--) {
                for (int i = 0; i < width; i++)
                    sb.append(cells[i][j - 1] == 1 ? i + z.getX() + 1 == 0 && j + z.getY() == 0 ? 'O' : '*' : '.');
                sb.append('\n');
            }
            return sb.toString();
        }

        private Collection<Point> updateCells() {
            List<Point> additions = new ArrayList<>();
            // i, j and neighbors are in the coordinate system of the total extents.
            for (int i = 0; i <= width + 1; i++)
                for (int j = 0; j <= height + 1; j++) {
                    final boolean onEdge = i == 0 || i == width + 1 || j == 0 || j == height + 1;
                    final Point p = group.updateCell(neighbors[i][j], onEdge, i, j, cells);
                    if (p != null) additions.add(p);
                }
            return additions;
        }
    }
}
