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
		public Group(Point origin, Point extent, List<Point> cells) {
				this.origin = origin;
				this.extent = extent;
				this.cells = cells;
		}

		public Group() {
				this(null, null, new ArrayList<>());
		}

		public Group(List<Point> cells) {
				this(null, null, cells);
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
		public boolean add(Point point) {
				updateExtents(point);
				return cells.add(point);
		}

		public boolean overlap(Group group) {
				if (origin == null || group.origin == null) return false;
				return extent != null && group.extent != null;
				// For now, we just see if extents overlap
		}

		public Group merge(Group group) throws LifeException {
				if (group == this) throw new LifeException("cannot merge with self");
				Group result = new Group(origin, extent, cells);
				group.forEach(result::add);
				return result;
		}

		private void updateExtents(Point point) {
				if (extent == null) extent = point;
				if (origin == null) origin = point;
				if (point.getX() >= extent.getX()) extent = new Point(point.getX(), extent.getY());
				if (point.getY() >= extent.getY()) extent = new Point(extent.getX(), point.getY());
				if (point.getX() <= origin.getX()) extent = new Point(point.getX(), origin.getY());
				if (point.getY() <= origin.getY()) extent = new Point(origin.getX(), point.getY());
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

		Point getExtent() {
				return extent;
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

		private final List<Point> cells; // the list of non-empty cells within this group.
		private transient Point origin; // the position of the origin relative to the grid. All cells have positive coordinates relative to the origin.
		private transient Point extent; // the position of the furthest corner a rectangle drawn around the group, encompassing all cells.  All cells have negative coordinates relative to extent.
}
