package edu.neu.coe.info6205.graphs.undirected;

import edu.neu.coe.info6205.bqs.Bag_Array;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphSimpleTest {

    @Test
    public void adjacent() {
        final Graph_Simple graph = new Graph_Simple();
        graph.addEdge(1, 2);
        assertEquals(1, ((Bag_Array<?>) graph.adjacent(1)).size());
        assertEquals(1, ((Bag_Array<?>) graph.adjacent(2)).size());
        assertTrue(((Bag_Array<Integer>) graph.adjacent(1)).contains(2));
        assertTrue(((Bag_Array<Integer>) graph.adjacent(2)).contains(1));
    }

    @Test
    public void testToString() {
        final Graph_Simple graph = new Graph_Simple();
        graph.addEdge(1, 2);
        assertEquals("{1=Bag_Array{items=[2], count=1}, 2=Bag_Array{items=[1], count=1}}", graph.toString());
    }
}