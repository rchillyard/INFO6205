/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import edu.neu.coe.info6205.equable.BaseEquable;
import edu.neu.coe.info6205.equable.Equable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class serves as a test harness for Equable.
 */
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

    public Equable getEquable() {
        Collection<Object> elements = new ArrayList<>();
        elements.add(x);
        elements.add(y);
        return new Equable(elements);
    }

    private final int x;
    private final double y;

    public static int index(int hash) {
        System.out.printf("hash: %x\n", hash);
        int x = (hash & 0xFFFF0000) >>> 16 ^ hash & 0x0000FFFF;
        System.out.printf("index: %x\n", x);
        return x;
    }

}
