/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import java.util.ArrayList;
import java.util.Collection;

public class Tuple extends BaseEquable {

    private final int x;
    private final double y;

    public Tuple(int x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tuple(" + x + ", " + y + ")";
    }


    @Override
    public Equable getEquable() {
        Collection<Object> elements = new ArrayList();
        elements.add(new Integer(x));
        elements.add(new Double(y));
        return new Equable(elements);
    }

    public static void main(String[] args) {
        Tuple tuple1 = new Tuple(100, 23);
        Tuple tuple2 = new Tuple(200, 33);
        assertTrue(tuple1.hashCode() == 1077349404, "tuple1.hashCode()==1077349404");
        assertTrue(tuple2.hashCode() == 1077975096, "tuple2.hashCode()==1077975096");
        assertTrue(tuple1.equals(tuple1), "tuple1.equals(tuple1)");
        assertTrue(tuple2.equals(tuple2), "tuple2.equals(tuple2)");
        assertTrue(!tuple1.equals(tuple2), "!tuple1.equals(tuple2)");
        System.out.println(tuple1);
        assertTrue(tuple1.toString().equals("Tuple(100, 23.0)"), "tuple1.toString()==\"Tuple(100, 23.0)\"");
    }

    public static void assertTrue(boolean b, String msg) {
        if (!b) System.out.println(msg + " not true");
    }
}
