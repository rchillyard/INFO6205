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
        Element<Item> element = new Element<>(item);
        if (isEmpty()) first = element;
        else old.next = element;
        this.last = element;
    }

    public Item dequeue() {
        Item result = first.item;
        Element<Item> tail = first.next;
        first = tail;
        if (isEmpty()) last = null;
        return result;
    }

    public boolean isEmpty() {
        return first == null;
    }

    // This Element essentially begins a LinkedList of Elements which correspond
    // to the elements that can be taken from the queue.
    // CONSIDER simplifying by using a LinkedList here instead of Element
    private Element<Item> first;

    // This element always points to the last element in the LinkedList referenced by First.
    private Element<Item> last;
}
