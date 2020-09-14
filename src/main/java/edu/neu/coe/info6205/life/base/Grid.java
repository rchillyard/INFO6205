package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * This class represents an infinite grid on which the game of life can be played.
 * There can be independent groups in the grid, but if they ever overlap, they must be merged.
 * In practice, you will typically only have one group in a grid.
 */
public class Grid implements Generational<Grid, Group>, Countable, Renderable {

    Grid(long generation) {
        this(generation, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Grid{" +
                "generation=" + generation +
                ", groups=" + groups +
                '}';
    }

    @Override
    public String render() {
        Group group = new Group(generation);
        for (Group g : groups) group = group.merge(g);
        return group.render();
    }

    /**
     * Appends the specified element to the end of the list of groups.
     *
     * @param group element to be added to this Grid.
     * @return a boolean indicating whether the add changed this.
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this list
     * @throws NullPointerException     if the specified element is null and this
     *                                  list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this list
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

    public int getCount() {
        int result = 0;
        for (Group g : groups)
            result += g.getCount();
        return result;
    }

    /**
     * Test for equality, ignoring the generation.
     *
     * @param o the other Grid.
     * @return true if they are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid)) return false;
        Grid grid = (Grid) o;
        return groups.equals(grid.groups);
    }

    /**
     * Get the hash code, ignoring the generation.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(groups);
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
    public Grid generation(BiConsumer<Long, Group> monitor) {
        forEach(g -> monitor.accept(generation, g));
        if (groups == null)
            throw new LifeException("logic error: groups is null");
        final List<Group> newGroups = this.groups.stream().map(g -> g.generation((l, group) -> System.out.println("Group generation: " + l))).collect(Collectors.toList());
        return new Grid(generation + 1, mergeGroups(newGroups));
    }

    /**
     * method to look for and to merge overlapping Groups.
     *
     * @param groups the groups to be checked.
     * @return a list of non-overlapping groups.
     */
    static List<Group> mergeGroups(List<Group> groups) {
        return inner(groups, new ArrayList<>());
    }

    private Grid(long generation, List<Group> groups) {
        this.generation = generation;
        this.groups = groups;
    }

    private static List<Group> inner(List<Group> work, List<Group> result) {
        if (work.size() == 0) return result;
        Group group = work.get(0);
        final List<Group> subList = work.subList(1, work.size());
        for (int i = 0; i < subList.size(); i++) {
            final Group other = subList.get(i);
            if (group.overlap(other)) {
                List<Group> merged = new ArrayList<>();
                merged.add(group.merge(other));
                merged.addAll(subList.subList(0, i));
                if (i < subList.size()) merged.addAll(subList.subList(i + 1, subList.size()));
                return inner(merged, result);
            }
        }
        result.add(group);
        return inner(subList, result);
    }

    private final long generation;
    private final List<Group> groups;

    static final Point Origin = new Point(0, 0);
}
