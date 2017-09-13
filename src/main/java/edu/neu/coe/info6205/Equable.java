/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import java.util.Iterator;

public class Equable {

    protected final Iterable<?> elements;

    public Equable(Iterable<?> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equable equable = (Equable) o;

        Iterator<?> thisIterator = elements.iterator();
        Iterator<?> thatIterator = equable.elements.iterator();
        while (thisIterator.hasNext()) {
            if (thatIterator.hasNext()) {
                boolean same = thisIterator.next().equals(thatIterator.next());
                if (!same)
                    return false;
            } else
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Iterator<?> iterator = elements.iterator();
        while (iterator.hasNext()) {
            result = 31 * result + iterator.next().hashCode();
        }
        return result;
    }
}
