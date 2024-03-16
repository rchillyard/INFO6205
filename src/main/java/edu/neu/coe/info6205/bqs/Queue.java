/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public interface Queue<Item> extends Iterable<Item> {

    /**
     * Update this Queue by adding an item on the "newest" end.
     *
     * @param item the item to add
     */
    void offer(Item item);

    /**
     * Update this Queue by taking the oldest item off the queue.
     *
     * @return the item or null if there is no such item.
     */
    Item poll();

    /**
     * @return true if this stack is empty
     */
    boolean isEmpty();
}