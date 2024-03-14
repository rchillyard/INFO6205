package edu.neu.coe.info6205.mcts.core;

import java.util.Collection;
import java.util.Iterator;

/**
 * This interface defines the behavior of a Node in an MCTS for playing a game (G).
 *
 * @param <G> the type of the Game.
 */
public interface Node<G extends Game> {

    /**
     * @return true if this node is a leaf node (in which case no further exploration is possible).
     */
    boolean isLeaf();

    /**
     * @return the State of the Game G that this Node represents.
     */
    State<G> state();

    /**
     * Method to determine if the player who plays to this node is the opening player (by analogy with chess).
     * NOTE: this assumes a two-player game.
     *
     * @return true if this node represents a "white" move; false for "black."
     */
    boolean white();

    /**
     * Method to yield the children of this Node.
     *
     * @return a Collection of Nodes.
     */
    Collection<Node<G>> children();

    /**
     * Method which adds the immediate children of this Node.
     * NOTE this is a mutating method. I'm not sure if that's best.
     */
    default void explore() {
        if (isLeaf()) return;
        if (children().isEmpty()) {
            addChildren(state());
            backPropagate();
        } else throw new RuntimeException("exploration done already for " + this);
    }

    /**
     * This method sets the number of wins and playouts according to the children states.
     */
    void backPropagate();

    /**
     * Method to add a child to this Node.
     *
     * @param state the State for the new chile.
     */
    void addChild(State<G> state);

    /**
     * @return the score for this Node and its descendents a win is worth 2 points, a draw is worth 1 point.
     */
    int wins();

    /**
     * @return the number of playouts evaluated (including this node). A leaf node will have a playouts value of 1.
     */
    int playouts();

    private void addChildren(final State<G> state) {
        for (Iterator<Move<G>> it = state.moveIterator(state.player()); it.hasNext(); )
            addChild(state.next(it.next()));
    }

}