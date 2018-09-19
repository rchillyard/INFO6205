/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.Iterator;

public class Stack_LinkedList<Item> implements Stack<Item> {
    public Stack_LinkedList() {
        list = new LinkedList_Elements<>();
    }

    public void push(Item item) {
        list.add(item);
    }

    public Item pop() throws BQSException {
        return list.remove();
    }

    public Item peek() {
        return list.getHead();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    private final LinkedList_Elements<Item> list;

    @Override
    public Iterator<Item> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
