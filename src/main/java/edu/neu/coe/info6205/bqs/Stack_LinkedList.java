/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.Iterator;
import java.util.Objects;

public class Stack_LinkedList<Item> implements Stack<Item> {
    /**
     * push method, delegates to list as add.
     *
     * @param item the item to push.
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
     * isEmpty method, delegates to list.
     *
     * @return true if this stack is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    public Iterator<Item> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stack_LinkedList)) return false;
        Stack_LinkedList<?> that = (Stack_LinkedList<?>) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "Stack_LinkedList{" +
                "list=" + list +
                '}';
    }

    /**
     * Secondary, but sole public, constructor.
     */
    public Stack_LinkedList() {
        this(new LinkedList_Elements<>());
    }

    /**
     * Primary, but package-private, constructor.
     */
    Stack_LinkedList(LinkedList<Item> list) {
        this.list = list;
    }

    private final LinkedList<Item> list;
}