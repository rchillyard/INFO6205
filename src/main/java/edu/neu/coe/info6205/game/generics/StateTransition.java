package edu.neu.coe.info6205.game.generics;

import java.util.function.UnaryOperator;

/**
 * This interface models a state transition of the board.
 * A StateTransition is a component of a "move."
 * Frequently, a move consists of only one StateTransition.
 *
 * @param <StateType> the state type.
 * @param <Position>  the position.
 */
public interface StateTransition<StateType, Position> {

    /**
     * The change of state for this StateTransition.
     * <p>
     * NOTE: it is possible that the resulting function is not strictly a UnaryOperator but is in effect a Supplier.
     * That's to say that the input to the function might be ignored (for example, it might legitimately be null).
     *
     * @return a function which will transform a StateType into a different StateType.
     */
    UnaryOperator<StateType> transitionFunction();

    /**
     * The starting position for this StateTransition.
     * <p>
     * NOTE that for some simple games, the "start position" may be null, in which case the function given
     * by stateTransition should, effectively, a Supplier.
     *
     * @return the starting position for this StateTransition.
     */
    Position startPosition();

    /**
     * The ending position for this StateTransition.
     * It is not necessarily different from the startPosition.
     * However, if they are the same, then it is expected that stateTransition will be other than the identity.
     *
     * @return the ending position for this StateTransition.
     */
    Position endPosition();

    /**
     * @return the next component of <code>this</code> StateTransition.
     */
    StateTransition<StateType, Position> next();
}
