/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

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

    @Nullable
    public Item peek() {
        return list.getHead();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @NotNull
    private final LinkedList<Item> list;
}
