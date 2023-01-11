package edu.neu.coe.info6205.bqs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LazyListTest {

    @Test
    public void iterate() {
        LazyList<Integer> lazyList = LazyList.iterate(1, x -> x + 1);
        Integer one = lazyList.head;
        assertEquals(Integer.valueOf(1), one);
        Integer two = lazyList.tailFunction.get().head;
        assertEquals(Integer.valueOf(2), two);
    }

    @Test
    public void from() {
        LazyList<Integer> lazyList = LazyList.from(1, 1);
        Integer one = lazyList.head;
        assertEquals(Integer.valueOf(1), one);
        Integer two = lazyList.tailFunction.get().head;
        assertEquals(Integer.valueOf(2), two);
    }

    @Test
    public void take() {
        LazyList<Integer> lazyList = LazyList.from(1);
        List<Integer> list = lazyList.take(3);
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }
}