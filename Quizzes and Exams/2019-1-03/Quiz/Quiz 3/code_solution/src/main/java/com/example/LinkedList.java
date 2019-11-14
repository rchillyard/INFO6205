package com.example;

/**
 * Implementation of a linked list
 *
 * @param <Item>
 */
public class LinkedList<Item> {

    private Element head = null;

    /**
     * Implementation of a linked list Element
     *
     * @param x item of an element
     * @param n pointer of the next element
     */
    public class Element {
        Element(Item x, Element n) {
            item = x;
            next = n;
        }
        final Item item;
        final Element next;
    }

    /**
     * Add an item in-front of linkedlist
     *
     * @param item the item to be added.
     */
    public void add(Item item) {
        // TODO: Implement the logic for adding element in front side of linkedlist
        Element tail = head;
        head = new Element(item, tail);
    }

    /**
     *  Remove the first element
     */
    public Item remove() throws Exception {
        // TODO: Implement the logic for removing element from linkedlist
        if (head == null) throw new Exception("empty");
        Item result = head.item; 
        head = head.next; 
        return result;
    }

    /**
     *  Retrieve Nth Element item
     *
     *  @param position the index of the element
     */
    public Item getNthElement(int position) throws IndexOutOfBoundsException {
        // TODO: Implement the logic for retrieving nth element from linkedlist
        if (head == null) throw new IndexOutOfBoundsException("empty");
        
        Element current = head; 
        int count = 0; 
        while (current != null) 
        { 
            if (count == position) 
                return current.item; 
            count++; 
            current = current.next; 
        } 
        
        return null;
    }

    /**
     *  Retrieve the head element item
     */
    public Item getHead() {
        return isEmpty() ? null : head.item ;
    }

    /**
     *  Returns True if LinkedList is empty. Returns False otherwise.
     */
    public boolean isEmpty() {
        return head==null;
    }
}
