/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

import java.util.Objects;

public class Element<Item> {
    Element(Item x, Element<Item> n) {
        item = x;
        next = n;
    }

    Element(Item x) {
        this(x, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element<?> element = (Element<?>) o;
        return item.equals(element.item) && Objects.equals(next, element.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, next);
    }

    final Item item;
    Element<Item> next;

    @Override
    public String toString() {
        return item + (next == null ? " (last)" : "");
    }
}
