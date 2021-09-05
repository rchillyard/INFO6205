/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import edu.neu.coe.info6205.equable.BaseComparableEquable;
import edu.neu.coe.info6205.equable.BaseEquable;
import edu.neu.coe.info6205.equable.ComparableEquable;
import edu.neu.coe.info6205.equable.Equable;

import java.util.ArrayList;
import java.util.Collection;

public class ComparableTuple extends BaseComparableEquable implements Comparable<BaseEquable> {

    private final int x;
    private final double y;

    public ComparableTuple(int x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tuple(" + x + ", " + y + ")";
    }

    public Equable getEquable() {
        Collection<Object> elements = new ArrayList<>();
        elements.add(x);
        elements.add(y);
        return new ComparableEquable(elements);
    }

    @Override
    public int compareTo(BaseEquable o) {
        return super.compareTo(o);
    }
}
