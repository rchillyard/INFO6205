/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public class Stack_LinkedList<Item> implements Stack<Item> {
    private final LinkedList<Item> list;

    public Stack_LinkedList() {
        list = new LinkedList<Item>();
    }

    @Override
    public void push(Item item) {
        list.add(item);
    }

    @Override
    public Item pop() throws RuntimeException {
        return list.remove();
    }

    @Override
    public Item peek() {
        return list.head.item;
    }

    @Override
    public boolean isEmpty() {
        return list.head==null;
    }
}
