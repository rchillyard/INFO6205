package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Class to model a group of cells. Groups may not overlap. If an overlap occurs through expansion, then the groups must merge.
 */
public class Group {
		/**
		 * Base constructor.
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
		 *
		 * 		 * @param generation the generation of this Group.
		 */
		Group(long generation) {
				this(generation, Grid.Origin, new ArrayList<>());
		}

		/**
		 * Constructor for a Group with a particular origin and a list of Points.
		 *
		 * @param generation the generation of this Group.
		 * @param origin     the origin of this Group relative to the Grid.
		 * @param points     the points in this Group, with coordinates relative to the origin.
		 */
		Group(long generation, Point origin, List<Point> points) {
				this(generation, origin, null, null, points);
				forEach(this::updateExtents);
		}

		/**
		 * Appends the specified cell to the end of cells.
		 *
		 * @param point element to be appended to this list
		 * @return a boolean indicating whether the add changed this.
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
				updateExtents(point);
				return points.add(point);
		}

		boolean add(int x, int y) {
				return add(new Point(x, y));
		}

		boolean add(String s) {
				return add(Point.points(s));
		}

		boolean add(List<Point> points) {
				boolean r = true;
				for (Point p : points) r = r && add(p);
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
		 * Method to create a new generation from this.
		 *
		 * @param generation the generation id.
		 * @return a new Group, which may possibly overlap with other Groups.
		 */
		Group newGeneration(long generation) {
				// Height and Width account for the fact that the extents are inclusive.
				int height = extent2.getY() - extent1.getY() + 1;
				int width = extent2.getX() - extent1.getX() + 1;
				// Neighbors is based on a grid that is appropriate for the new generation.
				int[][] neighbors = new int[width + 2][height + 2];
				// LiveCells is based on a grid that is appropriate to the current generation,
				int[][] liveCells = new int[width][height];
				final Point newOrigin = extent1.move(-1, -1);
				forEach(p -> incrementNeighborsAndNoteCell(p.relative(extent1), neighbors, liveCells));
				// CONSIDER optimizing here if any outer edge will not generate any new liveCells.
				Group result = new Group(generation, newOrigin, newOrigin, extent2.move(1, 1), moveCellsRelative(newOrigin));
				result.updateCells(neighbors, liveCells, width, height);
				return result;
		}

		/**
		 * @return a string representation of the object.
		 */
		@Override
		public String toString() {
				return "generation " + this.generation + ": origin=" + this.origin + ", extent1 = " + this.extent1 + ", extent2 = " + this.extent2 + "\n    " + this.points;
		}

		List<Point> pointsAbsolute() {
				List<Point> result = new ArrayList<>();
				forEach(p -> result.add(p.move(origin)));
				return result;
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

		private void updateCells(int[][] neighbors, int[][] cells, int width, int height) {
				// i, j and neighbors are in the coordinate system of the new generation
				for (int i = 0; i <= width + 1; i++)
						for (int j = 0; j <= height + 1; j++) {
								final boolean onEdge = i == 0 || i == width + 1 || j == 0 || j == height + 1;
								updateCell(neighbors[i][j], onEdge, i, j, cells);
						}
		}

		private void updateCell(int count, boolean onEdge, int i, int j, int[][] grid) {
				// grid indices are in the coordinate system of the old generation.
				final Point p = new Point(i, j);
				if (onEdge || grid[i - 1][j - 1] == 0) {
						assert !points.contains(p) : "logic error: should not exist: " + p;
						final boolean ok = (count != 3) || add(p);
						assert ok : "Problem adding point: " + p;
				} else {
						assert points.contains(p) : "logic error: should exist: " + p;
						final boolean ok = (count >= 2 && count <= 3) || remove(p);
						assert ok : "Problem removing point: " + p;
				}
		}

		private void incrementNeighborsAndNoteCell(Point p, int[][] neighbors, int[][] cells) {
				int px = p.getX();
				int py = p.getY();
				for (int i = -1; i < 2; i++) {
						final int[] neighborArray = neighbors[i + px + 1];
						for (int j = -1; j < 2; j++)
								if (i == 0 && j == 0) cells[px][py] = 1;
								else neighborArray[j + py + 1]++;
				}
		}

		/**
		 * Get the absolute value of the diagonal of the boundary.
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
				if (point.getX() >= extent2.getX()) extent2 = new Point(point.getX(), extent2.getY());
				if (point.getY() >= extent2.getY()) extent2 = new Point(extent2.getX(), point.getY());
				if (point.getX() <= extent1.getX()) extent1 = new Point(point.getX(), extent1.getY());
				if (point.getY() <= extent1.getY()) extent1 = new Point(extent1.getX(), point.getY());
		}

		/**
		 * Method to determine if a point is within this Group.
		 *
		 * @param point a Point in absolute (Grid) coordinates.
		 * @return true if point is within the extents of this Group.
		 */
		boolean withinExtents(Point point) {
				if (point == null) throw new LifeException("withinExtents: point is null");
				if (extent2 == null) return false;
				if (extent1 == null) return false;
				final int cfO = getExtent1().move(-1, -1).compare(point);
				if (!(cfO == 0 || cfO == 1 || cfO == 3 || cfO == 4)) return false;
				final int cfE = point.compare(getExtent2().move(1, 1));
				return cfE == 0 || cfE == 1 || cfE == 3 || cfE == 4;
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
		 * Method to yield this Group's extent1.
		 *
		 * @return the extent1 (relative to the Grid).
		 */
		Point getExtent1() {
				return extent1 != null ? getAbsolute(extent1) : null;
		}

		/**
		 * Method to yield this Group's extent2.
		 *
		 * @return the extent2 (relative to the Grid).
		 */
		Point getExtent2() {
				return extent2 != null ? getAbsolute(extent2) : null;
		}

		/**
		 * Method to yield the number of points in this Group.
		 *
		 * @return the number of points.
		 */
		int getCount() {
				return points.size();
		}

		@Override
		public boolean equals(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
				Group group = (Group) o;
				return points.equals(group.points);
		}

		@Override
		public int hashCode() {
				return Objects.hash(points);
		}

		/**
		 * This method is used by unit tests (via PrivateMethod Tester).
		 * <p>
		 * XXX Do not eliminate this method.
		 *
		 * @return the points in this Group.
		 */
		private List<Point> getPoints() {
				return points;
		}

		private final List<Point> points; // the list of non-empty cells within this group.
		private final long generation; // the current generation of this Group.
		private final Point origin; // the position of the origin relative to the grid.
		// All cells have coordinates which are relative to the origin.
		private transient Point extent1; // the position of the corner of the enclosing rectangle of this Group,
		// which is closest to the origin of the coordinate system.
		// All cells have positive coordinates compared to extent1.
		private transient Point extent2; // the position of the corner of the enclosing rectangle of this Group,
		// which is furthest from the origin of the coordinate system.
		// All cells have negative coordinates compared to extent2.
}
