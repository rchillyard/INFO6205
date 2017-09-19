/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class Queue_Elements<Item> implements Queue<Item> {
    public Queue_Elements() {
        oldest = null;
        newest = null;
    }

    /**
     * Enqueue the given item into the linked list referenced by oldest
     *
     * @param item the item to add
     */
    public void enqueue(@NotNull Item item) {
        Element<Item> element = new Element<>(item);
        @Nullable Element<Item> secondNewest = newest;
        if (isEmpty()) oldest = element;
        else {
            assert secondNewest != null; // Redundant Check
            secondNewest.next = element;
        }
        this.newest = element;
    }

    public @NotNull
    Item dequeue() {
        if (isEmpty()) return null;
        else {
            assert oldest != null; // Redundant assertion
            @NotNull Item result = oldest.item;
            @Nullable Element<Item> secondOldest = oldest.next;
            oldest = secondOldest;
            if (isEmpty()) newest = null;
            return result;
        }
    }

    public boolean isEmpty() {
        return oldest == null;
    }

    // This Element essentially begins a LinkedList of Elements which correspond
    // to the elements that can be taken from the queue (head points to the oldest element).
    // However, it is built in manner that requires a pointer to the newest element.
    @org.jetbrains.annotations.Nullable
    @Nullable
    private Element<Item> oldest;

    // This element always points to the newest (tail-most) element in the LinkedList referenced by oldest.
    @org.jetbrains.annotations.Nullable
    @Nullable
    private Element<Item> newest;
}
