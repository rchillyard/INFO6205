/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public interface Bag<Item> extends Iterable<Item> {

    /**
     * Update this Bag by adding item.
     * No guarantee is made regarding the ordering of Items in the iterator
     *
     * @param item the item to add
     */
    void add(Item item);

    /**
     * @return true if this bag is empty
     */
    boolean isEmpty();

    /**
     * @return the number of elements in this bag (not the capacity which is an implementation-dependent feature)
     */
    int size();
}
