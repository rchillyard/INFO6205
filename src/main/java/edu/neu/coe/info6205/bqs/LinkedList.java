/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public class LinkedList<Item> {

    public void add(Item item) {
        Element<Item> tail = head;
        head = new Element<>(item, tail);
    }

    public Item remove() throws BQSException {
        if (head == null) throw new BQSException("collection is empty");
        Item result = head.item;
        head = head.next;
        return result;
    }

    public Item getHead() {
        //noinspection ConstantConditions
        return isEmpty() ? null : head.item;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private Element<Item> head = null;
}
