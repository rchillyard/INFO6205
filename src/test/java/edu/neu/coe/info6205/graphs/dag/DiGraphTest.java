package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.SizedIterable;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiGraphTest {

    @Test
    public void testVertices() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 3));
        SizedIterable<String> vertices = graph.vertices();
        assertEquals(2, vertices.size());
    }

    @Test
    public void testAdjacent() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        Edge<String, Integer> edgeAB = new Edge<>("A", "B", 3);
        graph.addEdge(edgeAB);
        Iterable<Edge<String, Integer>> edges = graph.adjacent("A");
        Iterator<Edge<String, Integer>> iterator = edges.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(edgeAB, iterator.next());
    }

    @Test
    public void testEdges() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 3));
        SizedIterable<Edge<String, Integer>> edges = graph.edges();
        assertEquals(1, edges.size());
    }

    @Test
    public void testToString() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 3));
        assertEquals("{A=Bag_Array{items=[3: A->B], count=1}, B=Bag_Array{items=[], count=0}}", graph.toString());
    }
}