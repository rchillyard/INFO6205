/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.Iterator;

public class Stack_LinkedList<Item> implements Stack<Item> {

    /**
     * Primary constructor
     */
    public Stack_LinkedList() {
        list = new LinkedList_Elements<>();
    }

    /**
     * push method, delegates to list as add
     *
     * @param item the item to add
     */
    public void push(Item item) {
        list.add(item);
    }

    /**
     * pop method, delegates to list as remove
     *
     * @return the item on the top of this stack
     * @throws BQSException if this stack is empty
     */
    public Item pop() throws BQSException {
        return list.remove();
    }

    /**
     * peek method, delegates to list as getHead
     *
     * @return the value at the top of the stack (no change is made to the stack). Result may be null
     */
    public Item peek() {
        return list.getHead();
    }

    /**
     * isEmtpy method, delegates to list.
     *
     * @return true if this stack is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<Item> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    private final LinkedList_Elements<Item> list;
}
