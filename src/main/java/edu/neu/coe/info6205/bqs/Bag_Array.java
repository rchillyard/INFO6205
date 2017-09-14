/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.Arrays;
import java.util.Iterator;

public class Bag_Array<Item> implements Bag<Item> {

    public Bag_Array() {
        //noinspection unchecked
        grow((Item[])new Object[0], 32);
    }

    public void add(Item item) {
        if (full())
            grow(items,2 * capacity());
        items[count++] = item;
    }

    public boolean isEmpty() {
        return count==0;
    }

    public int size() {
        return count;
    }

    public Iterator<Item> iterator()  {
        return Arrays.asList(Arrays.copyOf(items,count)).iterator();
    }

    private void grow(Item[] source, int size) {
        items = growFrom(source, size);
    }

    private int capacity() {
        return items.length; // items should always be non-null when this method is called
    }

    private boolean full() {
        return size()==capacity();
    }

    /**
     * This fairly primitive grow method takes a T array called "from",
     * instantiates a new array of the given size,
     * copies all the elements of from into the start of the resulting array,
     * then returns the result.
     * @param from the source array
     * @param size the size of the new array
     */
    private static <T> T[] growFrom(T[] from, int size) {
        @SuppressWarnings("unchecked") T[] result = (T[])new Object[size];
        System.arraycopy(from, 0, result, 0, from.length);
        return result;
    }

    private Item[] items = null;
    private int count = 0;
}
