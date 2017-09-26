/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public class Stack_LinkedList<Item> implements Stack<Item> {
    public Stack_LinkedList() {
        list = new LinkedList<>();
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

    private final LinkedList<Item> list;
}
