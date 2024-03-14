package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.State;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TicTacToeTest {

    /**
     *
     */
    @Test
    public void runGame() {
        long seed = 0L;
        TicTacToe target = new TicTacToe(seed); // games run here will all be deterministic.
        State<TicTacToe> state = target.runGame();
        Optional<Integer> winner = state.winner();
        if (winner.isPresent()) assertEquals(Integer.valueOf(TicTacToe.X), winner.get());
        else fail("no winner");
    }
}