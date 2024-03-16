/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import edu.neu.coe.info6205.equable.BaseComparableEquable;
import edu.neu.coe.info6205.equable.BaseEquable;
import edu.neu.coe.info6205.equable.ComparableEquable;
import edu.neu.coe.info6205.equable.Equable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

public class ComparableTupleTest {

    /**
     * Test methods for ComparableTuple
     */
    @Test
    public void testComparableTuple1() {
        ComparableTuple tuple1 = new ComparableTuple(1, Math.PI);
        BaseEquable tuple2 = new ComparableTuple(2, Math.E);
        assertEquals(Integer.compare(1, 2), tuple1.compareTo(tuple2));
        BaseEquable tuple3 = new ComparableTuple(1, Math.E);
        assertEquals(Double.compare(Math.PI, Math.E), tuple1.compareTo(tuple3));
    }

    class Incomparable {
        public Incomparable() {
            super();
        }
    }

    class MockIncomparableTuple extends BaseComparableEquable implements Comparable<MockIncomparableTuple> {

        private final Incomparable y;

        public MockIncomparableTuple(Incomparable y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "MockIncomparableTuple(" + y + ")";
        }

        @SuppressWarnings("unchecked")
        public Equable getEquable() {
            Collection<Object> elements = new ArrayList();
            elements.add(y);
            return new ComparableEquable(elements);
        }

        public int compareTo(MockIncomparableTuple o) {
            return super.compareTo(o);
        }
    }

    @Test(expected = ComparableEquable.ComparableEquableException.class)
    public void testComparableTuple2() {
        MockIncomparableTuple tuple1 = new MockIncomparableTuple(new Incomparable());
        MockIncomparableTuple tuple2 = new MockIncomparableTuple(new Incomparable());
        tuple1.compareTo(tuple2);
    }

}