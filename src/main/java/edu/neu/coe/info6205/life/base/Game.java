package edu.neu.coe.info6205.life.base;

import java.util.function.BiConsumer;

public class Game implements Generational<Grid> {
		private final Grid grid;
		private final BiConsumer<Long, Group> monitor;
		private long generation;

		public Game(Grid grid, BiConsumer<Long, Group> monitor) {
				this.grid = grid;
				generation = 0L;
				this.monitor = monitor;
		}

		public void run() {
				BiConsumer<Long, Grid> monitor = (l, g) -> System.out.println("generation " + l);
				while (!terminated())
						this.generation(++generation, monitor);
		}

		@Override
		public void generation(long generation, BiConsumer<Long, Grid> monitor) {
				monitor.accept(generation, grid);
				grid.generation(generation, this.monitor);
		}

		private boolean terminated() {
				return generation >= 1000;
		}

		public static void main(String[] args) {
				final Grid grid = new Grid();
				Game game = new Game(grid, (l, g) -> System.out.println("show grid for generation " + l));
				game.run();
		}
}
