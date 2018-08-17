/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.*;

public class LinkedList<Item> implements Iterable<Item> {

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

    @Override
    public Iterator<Item> iterator() {
        return asArrayList().iterator();
    }

    private ArrayList<Item> asArrayList() {
        ArrayList<Item> result = new ArrayList<>();
        Element<Item> x = head;
        while (x!=null) {
            result.add(x.item);
            x = x.next;
        }
        return result;
    }

    @Override
    public String toString() {
        return asArrayList().toString();
    }
}
