/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * Concrete class which implements LinkedList of Item as a sequence of Elements.
 * This implementation of LinkedList could theoretically allow items to be added or removed elsewhere than the head.
 * If we wanted to be strict and allow addition/removal at the head only, we would have to make the next field of
 * Element final.
 *
 * @param <Item> the underlying type of this list.
 */
public class LinkedList_Elements<Item> implements LinkedList<Item> {

    /**
     * Add the given element to the head of this list.
     *
     * @param item an item.
     */
    public void add(Item item) {
        Element<Item> tail = head;
        head = new Element<>(item, tail);
    }

    /**
     * Remove the element at the head of this list.
     *
     * @return the value of the element that was at the head of the list.
     * @throws BQSException the list is empty.
     */
    public Item remove() throws BQSException {
        if (head == null) throw new BQSException("collection is empty");
        Item result = head.item;
        head = head.next;
        return result;
    }

    /**
     * Method to get the element at the head of this list without any mutation.
     * Equivalent to add(remove()).
     *
     * @return the item at the head of the list.
     */
    public Item getHead() {
        return isEmpty() ? null : head.item;
    }

    /**
     * @return true if this list is empty.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Method to yield an iterator for this list.
     *
     * @return an Iterator.
     */
    public Iterator<Item> iterator() {
        return asItemIterable().iterator();
    }

    /**
     * Method to render this List as a String.
     *
     * @return a representation of this list.
     */
    public String toString() {
        return asItemIterable().toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedList_Elements)) return false;
        LinkedList_Elements<?> that = (LinkedList_Elements<?>) o;
        return Objects.equals(head, that.head);
    }

    public int hashCode() {
        return Objects.hash(head);
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

    private Element<Item> head = null;
}