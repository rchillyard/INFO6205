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
		Group(long generation, Point origin, Point extent1, Point extent2, List<Point> cells) {
				this.generation = generation;
				this.origin = origin;
				this.extent1 = extent1;
				this.extent2 = extent2;
				this.cells = cells;
		}

		Group(long generation) {
				this(generation, new Point(0, 0), null, null, new ArrayList<>());
		}

		Group(long generation, Point origin, List<Point> cells) {
				this(generation, origin, null, null, cells);
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
				return cells.add(point);
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
				return withinExtents(group.extent1) || withinExtents(group.extent2) || withinExtents(group.getDiagonal(true)) || withinExtents(group.getDiagonal(false));
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
				Group result = new Group(generation, new Point(0, 0), extent1, extent2, cells);
				group.forEach(result::add);
				return result;
		}

		/**
		 * Method to create a new generation from this.
		 *
		 * @param generation the generation id.
		 * @return a new Group, which may possibly overlap with other Groups.
		 */
		Group newGeneration(long generation) throws LifeException {
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
				boolean ok = result.updateCells(neighbors, liveCells, width, height);
				if (!ok) throw new LifeException("Logic error in adding or removing a point");
				return result;
		}

		/**
		 * @return a string representation of the object.
		 */
		@Override
		public String toString() {
				return "generation " + this.generation + ": origin=" + this.origin + ", extent1 = " + this.extent1 + ", extent2 = " + this.extent2 + "\n    " + this.cells;
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

		private boolean updateCells(int[][] neighbors, int[][] cells, int width, int height) {
				boolean result = true;
				// i, j and neighbors are in the coordinate system of the new generation
				for (int i = 0; i <= width + 1; i++)
						for (int j = 0; j <= height + 1; j++) {
								final int count = neighbors[i][j];
								// cells are in the coordinate system of the old generation.
								final Point p = new Point(i, j);
								if (i == 0 || i == width + 1 || j == 0 || j == height + 1 || cells[i - 1][j - 1] == 1) {
										final boolean exists = this.cells.contains(p);
										if (exists) System.out.println(p+" exists"); else System.out.println(p+" doesn't exist");
										if (exists && (count < 2 || count > 3)) result = result && remove(p);
								} else if (count == 3) result = result && add(p);
						}
				return result;
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

		private Point getDiagonal(boolean nw) {
				if (nw) return extent1.move(0, extent2.getY());
				else return extent1.move(extent2.getX(), 0);
		}

		private void updateExtents(Point point) {
				if (extent2 == null) extent2 = point;
				if (extent1 == null) extent1 = point;
				if (point.getX() >= extent2.getX()) extent2 = new Point(point.getX(), extent2.getY());
				if (point.getY() >= extent2.getY()) extent2 = new Point(extent2.getX(), point.getY());
				if (point.getX() <= extent1.getX()) extent1 = new Point(point.getX(), extent1.getY());
				if (point.getY() <= extent1.getY()) extent1 = new Point(extent1.getX(), point.getY());
		}

		boolean withinExtents(Point point) {
				if (extent2 == null) return false;
				if (extent1 == null) return false;
				final int cfO = extent1.move(-1, -1).compare(point);
				if (!(cfO == 0 || cfO == 1 || cfO == 3 || cfO == 4)) return false;
				final int cfE = point.compare(extent2.move(1, 1));
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
				return cells.remove(p);
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
				cells.forEach(action);
		}

		Point getOrigin() {
				return origin;
		}

		Point getExtent1() {
				return extent1;
		}

		Point getExtent2() {
				return extent2;
		}

		int getCount() {
				return cells.size();
		}

		@Override
		public boolean equals(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
				Group group = (Group) o;
				return cells.equals(group.cells);
		}

		@Override
		public int hashCode() {
				return Objects.hash(cells);
		}

		private List<Point> getCells() {
				return cells;
		}

		private final List<Point> cells; // the list of non-empty cells within this group.
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
