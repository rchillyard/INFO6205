/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public class Element<Item> {
    Element(Item x, Element<Item> n) {
        item = x;
        next = n;
    }

    Element(Item x) {
        this(x, null);
    }

    final Item item;
    Element<Item> next;

    @Override
    public String toString() {
        return item + (next == null ? " (last)" : "");
    }
}
