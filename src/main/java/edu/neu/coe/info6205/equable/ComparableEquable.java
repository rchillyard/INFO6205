/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.equable;

import java.util.Collection;
import java.util.Iterator;

public class ComparableEquable extends Equable implements Comparable<ComparableEquable> {

    public ComparableEquable(Collection<?> elements) {
        super(elements);
    }

    public int compareTo(ComparableEquable o) {
        Iterator<?> thisIterator = elements.iterator();
        Iterator<?> thatIterator = o.elements.iterator();
        while (thisIterator.hasNext()) {
            if (thatIterator.hasNext()) {
                final Object next1 = thisIterator.next();
                final Object next2 = thatIterator.next();
                if (next1 instanceof Comparable<?>) {
                    //noinspection unchecked
                    final Comparable<Object> comparable1 = (Comparable<Object>) next1;
                    int cf = comparable1.compareTo(next2);
                    if (cf != 0)
                        return cf;
                } else
                    throw new ComparableEquableException("ComparableEquable can only compare elements which are themselves Comparable");
            } else
                throw new ComparableEquableException("ComparableEquable can only compare Equables of the same length");
        }
        return 0;
    }

    public static class ComparableEquableException extends RuntimeException {
        public ComparableEquableException(String s) {
            super(s);
        }
    }
}