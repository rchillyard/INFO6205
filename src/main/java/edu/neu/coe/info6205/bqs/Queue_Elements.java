/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public class Queue_Elements<Item> implements Queue<Item> {
    public Queue_Elements() {
        first = null;
        last = null;
    }

    public void enqueue(Item item) {
        Element old = last;
        last = new Element<>(item, null);
        if (isEmpty()) first = last;
        else old.next = last;
    }

    public Item dequeue() {
        Item result = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return result;
    }

    public boolean isEmpty() {
        return first==null;
    }

    private Element<Item> first;

    private Element<Item> last;
}
