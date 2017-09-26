/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import edu.neu.coe.info6205.equable.BaseEquable;
import edu.neu.coe.info6205.equable.Equable;

import java.util.ArrayList;
import java.util.Collection;

public class Tuple extends BaseEquable {

    public Tuple(int x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Tuple(" + x + ", " + y + ")";
    }


    @Override
    public Equable getEquable() {
        Collection<Object> elements = new ArrayList<>();
        elements.add(x);
        elements.add(y);
        return new Equable(elements);
    }

    private final int x;
    private final double y;

}
