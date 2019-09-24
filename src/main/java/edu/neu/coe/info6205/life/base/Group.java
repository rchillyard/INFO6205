package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;

import java.util.List;
import java.util.function.Consumer;

public class Group {
		public Group(Point origin, List<Point> cells) {
				this.origin = origin;
				this.cells = cells;
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
				return cells.add(point);
		}

		/**
		 * Removes the first occurrence of the specified element from cells,
		 *
		 * @param o element to be removed from this list, if present
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
		public boolean remove(Object o) {
				return cells.remove(o);
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
		public void forEach(Consumer<? super Point> action) {
				cells.forEach(action);
		}

		public Point getOrigin() {
				return origin;
		}

		private final Point origin; // the position of the origin relative to the grid.
		private final List<Point> cells; // the list of non-empty cells within this group.
		private transient Point extent; // the position of the furthest corner a rectangle drawn around the group, encompassing all cells.
}
