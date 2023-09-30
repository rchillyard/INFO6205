package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.life.library.Library;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Game implements Generational<Game, Grid>, Countable, Renderable {

    /**
     * Method to get the cell count.
     *
     * @return the number of live cells.
     */
    @Override
    public int getCount() {
        return grid.getCount();
    }

    @Override
    public String toString() {
        return "Game{" +
                "grid=" + grid +
                ", generation=" + generation +
                '}';
    }

    /**
     * Method to test equality, ignoring generation.
     *
     * @param o the other Game.
     * @return true if this and o are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return grid.equals(game.grid);
    }

    /**
     * Method to generate a hashCode, ignoring generation.
     *
     * @return hashCode for this.
     */
    @Override
    public int hashCode() {
        return Objects.hash(grid);
    }

    @Override
    public Game generation(BiConsumer<Long, Grid> monitor) {
        monitor.accept(generation, grid);
        return new Game(generation + 1, grid.generation(this.monitor), this.monitor);
    }

    @Override
    public String render() {
        return grid.render();
    }

    public static final int MaxGenerations = 1000;

    // CONSIDER making this a priority queue so that the older generations can be discarded.
    public static final Map<Game, Long> generations = new HashMap<>();

    public static void main(String[] args) {
        String patternName = args.length > 0 ? args[0] : "Blip";
        System.out.println("Game of Life with starting pattern: " + patternName);
        final String pattern = Library.get(patternName);
        run(pattern);
    }

    public static void run(String pattern) {
        final long generation = 0L;
        final Grid grid = new Grid(generation);
        grid.add(Group.create(generation, pattern));
        BiConsumer<Long, Grid> gridMonitor = (l, g) -> System.out.println("generation " + l + "; grid=" + g);
        BiConsumer<Long, Group> groupMonitor = (l, g) -> System.out.println("generation " + l + ";\ngroup=\n" + g.render());
        Game game = new Game(generation, grid, groupMonitor);
        while (!game.terminated()) {
            generations.put(game, game.generation);
            System.out.println(game.render());
            game = game.generation(gridMonitor);
        }
        System.out.println("Ending Game of Life after " + game.generation + " generations and with " + game.getCount() + " cells");
    }

    private Game(long generation, BiConsumer<Long, Group> monitor) {
        this(generation, new Grid(generation), monitor);
    }

    private Game(long generation, Grid grid, BiConsumer<Long, Group> monitor) {
        this.grid = grid;
        this.generation = generation;
        this.monitor = monitor;
    }

    private boolean terminated() {
        return testTerminationPredicate(g -> g.generation >= MaxGenerations, "having exceeded " + MaxGenerations + " generations") ||
                testTerminationPredicate(g -> g.getCount() == 0, "no cells") ||
                testTerminationPredicate(generations::containsKey, "having previous equivalent game");
    }

    private boolean testTerminationPredicate(Predicate<Game> predicate, String message) {
        if (predicate.test(this)) {
            System.out.println("Terminating due to: " + message);
            return true;
        }
        return false;
    }

    private final Grid grid;
    private final BiConsumer<Long, Group> monitor;
    private final long generation;
}