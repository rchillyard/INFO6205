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

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<Item> iterator() {
        return list.iterator();
    }

    private final LinkedList<Item> list;
}
