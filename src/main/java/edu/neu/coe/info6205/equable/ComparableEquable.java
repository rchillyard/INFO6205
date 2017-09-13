/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.equable;

import java.util.Iterator;

public class ComparableEquable<T extends Comparable<T>> extends Equable implements Comparable<ComparableEquable<T>> {

    public ComparableEquable(Iterable<T> elements) {
        super(elements);
    }

    @Override
    public int compareTo(ComparableEquable<T> o) {
        Iterator<T> thisIterator = (Iterator<T>) elements.iterator();
        Iterator<T> thatIterator = (Iterator<T>) o.elements.iterator();
        while (thisIterator.hasNext()) {
            if (thatIterator.hasNext()) {
                int cf = thisIterator.next().compareTo(thatIterator.next());
                if (cf != 0)
                    return cf;
            } else
                return -1;
        }
        return 0;
    }
}
