/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Bag_Array<Item> implements Bag<Item> {

    /**
     * Primary constructor that takes an explicit Random source (which will be passed to any UnorderedIterator).
     * NOTE: random is mutable and therefore unpredictable.
     *
     * @param random a random source.
     */
    public Bag_Array(Random random) {
        //noinspection unchecked
        grow((Item[]) new Object[0], 32);
        this.random = random;
    }

    public Bag_Array() {
        this(new Random());
    }

    public void add(Item item) {
        assert items != null;
        if (full())
            grow(items, 2 * capacity());
        items[count++] = item;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void clear() {
        count = 0;
    }

    public boolean contains(Item item) {
        for (Item i : items) {
            if (i != null && i.equals(item))
                return true;
        }
        return false;
    }

    public int multiplicity(Item item) {
        int result = 0;
        if (isEmpty()) return 0;
        for (Item i : items) {
            if (i != null && i.equals(item))
                result++;
        }
        return result;
    }

    /**
     * Method to generate a randomly ordered iterator on this Bag.
     *
     * @return an Iterator on Item.
     */
    public Iterator<Item> iterator() {
        assert items != null; // Should be not-null any time after construction.
        return new UnorderedIterator<>(asArray(), random);
    }

    /**
     * Method to get this Bag as an array.
     * <p>
     * Internally, Object[] can be cast as an Item[] but it is not valid externally.
     * Hence, the arraycopy.
     * This is a quirk of Java Generics.
     *
     * @return this Bag as an array.
     */
    public Item[] asArray() {
        @SuppressWarnings("unchecked") Item[] items = (Item[]) new Object[count];
        System.arraycopy(this.items, 0, items, 0, count);
        return items;
    }

    @Override
    public String toString() {
        return "Bag_Array{" +
                "items=" + Arrays.toString(asArray()) +
                ", count=" + count +
                '}';
    }

    private void grow(Item[] source, int size) {
        items = growFrom(source, size);
    }

    private int capacity() {
        assert items != null; // Should be not-null any time after construction.
        return items.length;
    }

    private boolean full() {
        return size() == capacity();
    }

    /**
     * This fairly primitive grow method takes a T array called "from",
     * instantiates a new array of the given size,
     * copies all the elements of from into the start of the resulting array,
     * then returns the result.
     *
     * @param from the source array
     * @param size the size of the new array
     */
    private static <T> T[] growFrom(T[] from, int size) {
        // TO BE IMPLEMENTED  grow array and copy
         return null;
        // END SOLUTION
    }

    private final Random random;

    private Item[] items = null;
    private int count = 0;
}