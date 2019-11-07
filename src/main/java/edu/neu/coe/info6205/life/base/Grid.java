package edu.neu.coe.info6205.life.base;

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
		 * Appends the specified element to the end of the list of groups.
		 *
		 * @param group element to be added to this Grid.
		 * @return a boolean indicating whether the add changed this.
		 * @throws ClassCastException            if the class of the specified element
		 *                                       prevents it from being added to this list
		 * @throws NullPointerException          if the specified element is null and this
		 *                                       list does not permit null elements
		 * @throws IllegalArgumentException      if some property of this element
		 *                                       prevents it from being added to this list
		 */
		public boolean add(Group group) {
				List<Group> unmergedGroups = new ArrayList<>();
				forEach(unmergedGroups::add);
				groups.clear();
				Group merged = null;
				for (Group g : unmergedGroups)
						if (merged != null || !g.overlap(group)) groups.add(g);
						else merged = g.merge(group);

				if (merged != null)
						return add(merged);
				else
						return groups.add(group);
		}

		int getCount() {
				int result = 0;
				for (Group g : groups)
						result += g.getCount();
				return result;
		}

		/**
		 * Unsupported Operation,
		 *
		 * @param o an element.
		 * @throws UnsupportedOperationException if the <tt>remove</tt> operation
		 *                                       is not supported by this list
		 */
		boolean remove(Group o) {
				throw new UnsupportedOperationException("cannot remove a Group from a Grid");
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
		void forEach(Consumer<? super Group> action) {
				groups.forEach(action);
		}

		@Override
		public void generation(long generation, BiConsumer<Long, Group> monitor) {
				forEach(g -> monitor.accept(generation, g));
//				grid.generation(generation, this.monitor);

		}

		private final List<Group> groups;

		static Point Origin = new Point(0, 0);
}
