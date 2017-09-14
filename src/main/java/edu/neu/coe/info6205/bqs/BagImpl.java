/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class BagImpl<Item> implements Bag<Item> {

    public BagImpl() {
        grow((Item[])new Object[0], 32);
    }

    private Item[] items = null;
    private int count = 0;

    @Override
    public void add(Item item) {
        if (full())
            grow(items,2 * capacity());
        items[count++] = item;
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

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<Item> iterator()  {
        return Arrays.asList(Arrays.copyOf(items,count)).iterator();
    }

    /**
     * This fairly primitive grow method takes an Object array called "from",
     * instantiates a new array of the given size,
     * copies all the elements of from into the start of the resulting array,
     * then returns the result.
     * @param from
     * @param size
     */
    private static <T> T[] growFrom(T[] from, int size) {
        T[] result = (T[])new Object[size];
        System.arraycopy(from, 0, result, 0, from.length);
        return result;
    }

}
