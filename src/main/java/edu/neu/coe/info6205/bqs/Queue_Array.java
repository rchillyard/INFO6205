package edu.neu.coe.info6205.bqs;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class to represent a circular queue.
 *
 * @param <Item> the underlying type.
 */
public class Queue_Array<Item> implements Queue<Item> {

    /**
     * Update this Queue by adding an item on the "newest" end.
     *
     * @param item the item to add
     */
    public void offer(Item item) {
        items[j++] = item;
        j = j % n;
        ensureRoom();
    }

    /**
     * Update this Queue by taking the oldest item off the queue.
     *
     * @return the item or null if there is no such item.
     */
    public Item poll() {
        if (isEmpty()) return null;
        Item result = items[i++];
        i = i % n;
        return result;
    }

    /**
     * @return true if this stack is empty
     */
    public boolean isEmpty() {
        return i == j;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    public Iterator<Item> iterator() {
        assert items != null; // Should be not-null any time after construction.
        // NOTE: there is no Java-defined array iterator.
        return Arrays.asList(asArray()).iterator();
    }

    public int size() {
        return (n + j - i) % n;
    }

    /**
     * Package-scope method to get the contents of this Queue_Array as an array.
     * <p>
     * NOTE: Internally, Object[] can be cast as an Item[] but it is not valid externally.
     * Hence, the arraycopy.
     * This is a quirk of Java Generics.
     *
     * @return this Queue as an array.
     */
    Item[] asArray() {
        int count = size();
        @SuppressWarnings("unchecked") Item[] items = (Item[]) new Object[count];
        if (straddle() && !isEmpty()) {
            int l = n - i;
            System.arraycopy(this.items, i, items, 0, l);
            System.arraycopy(this.items, 0, items, l, j);
        } else
            System.arraycopy(this.items, i, items, 0, count);
        return items;
    }

    void show() {
        String sb = "Queue_Array: " + "i: " + i + ", " +
                "j: " + j + ", " +
                Arrays.toString(items);
        System.out.println(sb);
    }

    private Item[] items;
    private int i;
    private int j;
    private int n;

    public Queue_Array(Item[] items, int i, int j) {
        this.items = items;
        this.i = i;
        this.j = j;
        this.n = items.length;
    }

    public Queue_Array(int n) {
        //noinspection unchecked
        this((Item[]) new Object[n], 0, 0);
    }

    private void ensureRoom() {
        // When this method is called, the queue cannot be empty because we just completed an "offer."
        if (i == j) {
            items = growArray(items, n * 2);
            System.arraycopy(items, 0, items, n, i);
            j += n;
            n = n * 2;
        }
    }

    private boolean straddle() {
        return j <= i;
    }

    private static <T> T[] growArray(T[] ts, int n) {
        assert (n >= ts.length);
        @SuppressWarnings("unchecked") T[] result = (T[]) new Object[n];
        System.arraycopy(ts, 0, result, 0, ts.length);
        return result;
    }

}