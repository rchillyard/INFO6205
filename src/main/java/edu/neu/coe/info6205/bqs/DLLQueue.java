package edu.neu.coe.info6205.bqs;

import java.util.Iterator;
import java.util.Objects;

public class DLLQueue<Item> implements Queue<Item> {

    /**
     * Construct a new empty queue using a doubly-linked list
     */
    public DLLQueue() {
        this.list = new DList<>();
    }

    /**
     * Construct a new queue using a doubly-linked list with an initial element
     *
     * @param item the data to be added to the queue
     */
    public DLLQueue(Item item) {
        this.list = new DList<>(item);
    }

    /**
     * Update the queue by adding the item to the end of the queue
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        if (list.size() == 0) {
            list.addBeforeElement(item, null);
        } else {
            DList<Item>.D_Element last = list.findLast(list.getTail());
            list.addAfterElement(item, last);
        }
    }

    /**
     * Update the queue by removing the item from the front of the queue.
     *
     * @return the item that is removed
     */
    public Item dequeue() {
        if (list.isEmpty())
            return null;
        else {
            DList<Item>.D_Element first = list.findFirst(list.getHead());
            Item dequeue = first.item;
            list.remove(first);
            return dequeue;
        }
    }


    /**
     * Function to check whether the queue is empty or not
     *
     * @return boolean true if the queue is empty, false otherwise
     */
    public boolean isEmpty() { return list.isEmpty(); }

    /**
     * Function to return the size of the queue
     * @return integer length of the queue
     */
    public int size() { return list.size(); }

    public Iterator<Item> iterator() { return list.iterator(); }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DLLQueue)) return false;
        DLLQueue<?> dllQueue = (DLLQueue<?>) o;
        return Objects.equals(list, dllQueue.list);
    }

    public int hashCode() {
        return Objects.hash(list);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item i : this) sb.append(i).append(", ");
        return sb.toString();
    }

    private final DList<Item> list;
}
