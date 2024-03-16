package edu.neu.coe.info6205.mcts.core;

/**
 * This interface defines the behavior of a Move in a Game.
 *
 * @param <G>
 */
public interface Move<G extends Game> {
    /**
     * The player of this Move.
     *
     * @return the player.
     */
    int player();
}