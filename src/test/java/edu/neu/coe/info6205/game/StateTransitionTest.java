package edu.neu.coe.info6205.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StateTransitionTest {

    @Test
    public void getRow() {
        Move<Integer> move = new Move<>(1, 2, 3);
        assertEquals(Integer.valueOf(1), move.row);
    }

    @Test
    public void getColumn() {
        Move<Integer> move = new Move<>(1, 2, 3);
        assertEquals(Integer.valueOf(2), move.column);
    }

    @Test
    public void getVal() {
        Move<Integer> move = new Move<>(1, 2, 3);
        assertEquals(Integer.valueOf(3), move.getVal());
    }
}