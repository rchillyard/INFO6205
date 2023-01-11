package edu.neu.coe.info6205.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderedArrayTest {

    @Test
    public void testConstructor0() {
        Integer[] integers = {3, 1, 4, 2, 0};
        OrderedArray<Integer> target = new OrderedArray<>(integers, false);
        assertEquals(Integer.valueOf(0), integers[0]);
        assertEquals(Integer.valueOf(0), target.get(0));
    }

    @Test
    public void testConstructor1() {
        Integer[] integers = {3, 1, 4, 2, 0};
        OrderedArray<Integer> target = new OrderedArray<>(integers);
        assertEquals(Integer.valueOf(0), target.get(0));
    }

    @Test
    public void testConstructor2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(0);
        OrderedArray<Integer> target = new OrderedArray<>(list);
        assertEquals(Integer.valueOf(0), target.get(0));
    }

    @Test
    public void testAddElements() {
        OrderedArray<Integer> orderedArray = OrderedArray.from(3, 1, 4);
        orderedArray.addElements(new Integer[]{2, 0});
        Iterator<Integer> iterator = orderedArray.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(0), iterator.next());
    }

    @Test
    public void testIterator() {
        OrderedArray<Integer> orderedArray = OrderedArray.from(3, 1, 4, 2, 0);
        Iterator<Integer> iterator = orderedArray.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(0), iterator.next());
    }

    @Test
    public void testIndexOf() {
        OrderedArray<Integer> orderedArray = OrderedArray.from(3, 1, 4, 2, 0);
        assertEquals(4, orderedArray.indexOf(4));
        assertEquals(-1, orderedArray.indexOf(-2));
    }
}