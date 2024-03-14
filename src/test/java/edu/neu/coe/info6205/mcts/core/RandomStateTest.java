package edu.neu.coe.info6205.mcts.core;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RandomStateTest {

    @Test
    public void next() {
        RandomState target = new RandomState(1000, 0L);
        RandomState actual = target.next();
        RandomState expected = new RandomState(1000, -4962768465676381896L);
        assertEquals(expected, actual);
    }

    @Test
    public void nextValue() {
        RandomState target = new RandomState(1000, 0L);
        long actual = target.next().longValue();
        long expected = 4804307197456638271L;
        assertEquals(expected, actual);
    }

    @Test
    public void nextNextValue() {
        RandomState target = new RandomState(1000, 0L);
        long actual = target.next().next().longValue();
        long expected = -1034601897293430941L;
        assertEquals(expected, actual);
    }

    @Test
    public void intValue() {
        RandomState target = new RandomState(1000, 0L);
        int actual = target.intValue();
        int expected = new Random(0L).nextInt(1000);
        assertEquals(expected, actual);
    }

    @Test
    public void longValue() {
        RandomState target = new RandomState(1000, 0L);
        long actual = target.longValue();
        long expected = new Random(0L).nextLong();
        assertEquals(expected, actual);
    }

    @Test
    public void booleanValue() {
        RandomState target = new RandomState(1000, 0L);
        boolean actual = target.booleanValue();
        boolean expected = new Random(0L).nextBoolean();
        assertEquals(expected, actual);
    }
}