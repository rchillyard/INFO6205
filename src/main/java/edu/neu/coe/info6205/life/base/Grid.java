package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * This class represents an infinite grid on which the game of life can be played.
 * There can be independent groups in the grid, but if they ever overlap, they must be merged.
 * In practice, you will typically only have one group in a grid.
 */
public class Grid implements Generational<Group> {

		public Grid(List<Group> groups) {
				this.groups = groups;
		}

		public Grid() {
				this(new ArrayList<>());
		}

		/**
		 * Appends the specified element to the end of this list of groups.
		 *
		 * @param group element to be appended to this list
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
		public boolean add(Group group) {
				return groups.add(group);
		}

		/**
		 * Removes the first occurrence of the specified element from groups,
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
		public boolean remove(Group o) {
				return groups.remove(o);
		}

		/**
		 * Performs the given action for each element of groups
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
		public void forEach(Consumer<? super Group> action) {
				groups.forEach(action);
		}

		private final List<Group> groups;

		@Override
		public void generation(long generation, BiConsumer<Long, Group> monitor) {
				forEach(g -> monitor.accept(generation, g));
		}

		public static Point Origin = new Point(0, 0);
}
