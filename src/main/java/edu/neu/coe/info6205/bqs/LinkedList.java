/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public class LinkedList<Item> {

    class Element<Item> {
        Element(Item x, Element<Item> n) {
            item = x;
            next = n;
        }
        Item item;
        Element<Item> next;
    }

    Element<Item> head = null;

    public void add(Item item) {
        Element<Item> tail = head;
        head = new Element(item, tail);
    }

    public Item remove() {
        Item result = head.item;
        head = head.next;
        return result;
    }

}
