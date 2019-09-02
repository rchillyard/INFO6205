/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public interface LinkedList<Item> extends Iterable<Item> {
    void add(Item item);

    Item remove() throws BQSException;

    Item getHead();

    boolean isEmpty();
}
