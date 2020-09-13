/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LinkedList_Elements<Item> implements LinkedList<Item> {

    @Override
    public void add(Item item) {
        Element<Item> tail = head;
        head = new Element<>(item, tail);
    }

    @Override
    public Item remove() throws BQSException {
        if (head == null) throw new BQSException("collection is empty");
        Item result = head.item;
        head = head.next;
        return result;
    }

    @Override
    public Item getHead() {
        return isEmpty() ? null : head.item;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private Element<Item> head = null;

    @Override
    public Iterator<Item> iterator() {
        return asItemIterable().iterator();
    }

    private Iterable<Item> asItemIterable() {
        Collection<Item> result = new ArrayList<>();
        Element<Item> x = head;
        while (x != null) {
            result.add(x.item);
            x = x.next;
        }
        return result;
    }

    @Override
    public String toString() {
        return asItemIterable().toString();
    }
}
