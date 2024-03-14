package edu.neu.coe.info6205.mcts.core;

import edu.neu.coe.info6205.bqs.UnorderedIterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

/**
 * This interface defines the behavior of a State of a game (G).
 *
 * @param <G> the type of game.
 */
public interface State<G extends Game> {
    /**
     * Method to yield the game of which this is a State.
     *
     * @return a G
     */
    G game();

    /**
     * Method to determine if this is a terminal State.
     *
     * @return true if it is not possible to follow this State of the game by any other State.
     */
    boolean isTerminal();

    /**
     * Method to determine the player who plays to this State.
     * The first player to play is considered to be "white" by analogy with chess.
     *
     * @return a non-negative integer.
     */
    int player();

    /**
     * Method to determine the winner, if this State represents the end of the game.
     *
     * @return of(w) where w is the index of the winner if this State is a win or loss;
     * otherwise, returns empty() [draw].
     */
    Optional<Integer> winner();

    /**
     * A random source associated with this State.
     *
     * @return the appropriate RandomState.
     */
    Random random();

    /**
     * Get the moves that can be made directly from the given state.
     * The moves can be in any order--the order will be randomized for usage.
     *
     * @param player the player of the moves.
     * @return all the possible moves from this state.
     */
    Collection<Move<G>> moves(int player);

    /**
     * Method to determine the next State.
     *
     * @param move the move which will generate the next State.
     * @return a new State.
     */
    State<G> next(Move<G> move);

    /**
     * Method to yield a (random) iterator of moves for the given player.
     *
     * @param player the player who will be making one of the moves.
     * @return an Iterator of moves.
     */
    default Iterator<Move<G>> moveIterator(int player) {
        Collection<Move<G>> moves = moves(player);
        if (moves == null) throw new RuntimeException("moves returned null");
        else return UnorderedIterator.createDeterministic(moves, random());
    }

    /**
     * Method to choose a move for the given player.
     * NOTE: depending on the field <code>random</code>, the choice may be deterministic or non-deterministic.
     *
     * @param player the player who will be making the move.
     * @return a move.
     */
    default Move<G> chooseMove(int player) {
        Iterator<Move<G>> iterator = moveIterator(player);
        if (iterator.hasNext()) return iterator.next();
        throw new RuntimeException("empty move iterator");
    }
}