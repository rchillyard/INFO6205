/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public interface Queue<Item> {

    /**
     * Update this Queue by adding an item on the "newest" end.
     * @param item the item to add
     */
    public void enqueue(Item item);

    /**
     * Update this Queue by taking the oldest item off the queue.
     * @return the item or null if there is no such item.
     */
    public Item dequeue();

    /**
     * @return true if this stack is empty
     */
    public boolean isEmpty();
}
