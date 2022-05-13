package edu.neu.coe.info6205.dynamicProgramming.lucas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LucasTest {

    @Test
    public void testGet1() {
        Lucas lucas = new Lucas();
        assertEquals(2, lucas.get(0));
        assertEquals(1, lucas.get(1));
        assertEquals(3, lucas.get(2));
        assertEquals(4, lucas.get(3));
        assertEquals(7, lucas.get(4));
        assertEquals(11, lucas.get(5));
        assertEquals(18, lucas.get(6));
        assertEquals(29, lucas.get(7));
        assertEquals(47, lucas.get(8));
        assertEquals(76, lucas.get(9));
        assertEquals(123, lucas.get(10));
        assertEquals(199, lucas.get(11));
    }
    @Test
    public void testGet2() {
        assertEquals(2, new Lucas().get(0));
        assertEquals(1, new Lucas().get(1));
        assertEquals(3, new Lucas().get(2));
        assertEquals(4, new Lucas().get(3));
        assertEquals(87403803, new Lucas().get(38));
    }
    @Test
    public void testGet3() {
        assertEquals(28143753123L, new Lucas().get(50));
    }
    @Test
    public void testGet4() {
        assertEquals(6440026026380244498L, new Lucas().get(90));
    }
}