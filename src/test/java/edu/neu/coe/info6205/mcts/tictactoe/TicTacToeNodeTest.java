package edu.neu.coe.info6205.mcts.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TicTacToeNodeTest {

    @Test
    public void winsAndPlayouts() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState(Position.parsePosition("X . 0\nX O .\nX . 0", TicTacToe.X));
        TicTacToeNode node = new TicTacToeNode(state);
        assertTrue(node.isLeaf());
        assertEquals(2, node.wins());
        assertEquals(1, node.playouts());
    }

    @Test
    public void state() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertEquals(state, node.state());
    }

    @Test
    public void white() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertTrue(node.white());
    }

    @Test
    public void children() {
        // no tests yet
    }

    @Test
    public void addChild() {
        // no tests yet
    }

    @Test
    public void backPropagate() {
        // no tests yet
    }
}