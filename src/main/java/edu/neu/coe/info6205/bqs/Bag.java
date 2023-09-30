/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import edu.neu.coe.info6205.SizedIterable;

public interface Bag<Item> extends SizedIterable<Item> {

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
     * @param item an item which we want to find in this Bag.
     * @return true if the item has at least one instance in this Bag.
     */
    boolean contains(Item item);

    /**
     * @param item an item for whose multiplicity we desire.
     * @return the multiplicity of item, that's to say the number of instances of item there are in this Bag.
     */
    int multiplicity(Item item);

    /**
     * Method to get this Bag as an array.
     *
     * @return this Bag as an array of Objects.
     */
    Object[] asArray();

    /**
     * Empty out this Bag
     */
    void clear();
}