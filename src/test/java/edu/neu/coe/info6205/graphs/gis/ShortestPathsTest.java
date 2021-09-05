package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.dag.DiGraph;
import edu.neu.coe.info6205.graphs.dag.Edge;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShortestPathsTest {

    @Test
    public void testShortestPaths1() {
        DiGraph<String, Double> graph = new DiGraph<>();
        Edge<String, Double> edgeAB = new Edge<>("A", "B", 3.0);
        graph.addEdge(edgeAB);
        ShortestPaths<String, Double> shortestPaths = new ShortestPaths<>(graph, "A");
        System.out.println(shortestPaths);
        assertTrue(shortestPaths.hasPathTo("B"));
        assertEquals(3.0, shortestPaths.cost("B"), 0);
    }


    @Test
    public void testShortestPaths2() {
        DiGraph<String, Double> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 1.0));
        graph.addEdge(new Edge<>("B", "C", 2.0));
        graph.addEdge(new Edge<>("C", "D", 1.0));
        graph.addEdge(new Edge<>("A", "E", 4.0));
        graph.addEdge(new Edge<>("A", "F", 8.0));
        graph.addEdge(new Edge<>("B", "F", 6.0));
        graph.addEdge(new Edge<>("B", "G", 6.0));
        graph.addEdge(new Edge<>("C", "G", 2.0));
        graph.addEdge(new Edge<>("D", "G", 1.0));
        graph.addEdge(new Edge<>("D", "H", 4.0));
        graph.addEdge(new Edge<>("E", "F", 5.0));
        graph.addEdge(new Edge<>("G", "F", 1.0));
        graph.addEdge(new Edge<>("G", "H", 1.0));
        ShortestPaths<String, Double> shortestPaths = new ShortestPaths<>(graph, "A");
//        System.out.println(shortestPaths);
        assertTrue(shortestPaths.hasPathTo("H"));
        assertEquals(6.0, shortestPaths.cost("H"), 0);
    }

    @Test
    public void testShortestPaths3() {
        DiGraph<String, Double> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 1.0));
        graph.addEdge(new Edge<>("B", "C", 2.0));
        graph.addEdge(new Edge<>("C", "D", 1.0));
        graph.addEdge(new Edge<>("A", "E", 4.0));
        graph.addEdge(new Edge<>("A", "F", 8.0));
        graph.addEdge(new Edge<>("B", "F", 6.0));
        graph.addEdge(new Edge<>("B", "G", 6.0));
        graph.addEdge(new Edge<>("C", "G", 2.0));
        graph.addEdge(new Edge<>("D", "G", 1.0));
        graph.addEdge(new Edge<>("D", "H", 4.0));
        graph.addEdge(new Edge<>("E", "F", 5.0));
        graph.addEdge(new Edge<>("G", "F", 1.0));
        graph.addEdge(new Edge<>("G", "H", 3.0));
        ShortestPaths<String, Double> shortestPaths = new ShortestPaths<>(graph, "A");
//        System.out.println(shortestPaths);
        assertTrue(shortestPaths.hasPathTo("H"));
        assertEquals(8.0, shortestPaths.cost("H"), 0);
    }

    @Test
    public void testShortestPaths4() {
        DiGraph<String, Double> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 1.0));
        graph.addEdge(new Edge<>("B", "C", 2.0));
        graph.addEdge(new Edge<>("C", "D", 1.0));
        graph.addEdge(new Edge<>("A", "E", 4.0));
        graph.addEdge(new Edge<>("A", "F", 8.0));
        graph.addEdge(new Edge<>("B", "F", 6.0));
        graph.addEdge(new Edge<>("B", "G", 6.0));
        graph.addEdge(new Edge<>("C", "G", 2.0));
        graph.addEdge(new Edge<>("D", "G", 1.0));
        graph.addEdge(new Edge<>("D", "H", 5.0));
        graph.addEdge(new Edge<>("E", "F", 5.0));
        graph.addEdge(new Edge<>("G", "F", 1.0));
        graph.addEdge(new Edge<>("G", "H", 5.0));
        ShortestPaths<String, Double> shortestPaths = new ShortestPaths<>(graph, "A");
//        System.out.println(shortestPaths);
        assertTrue(shortestPaths.hasPathTo("H"));
        assertEquals(9.0, shortestPaths.cost("H"), 0);
    }

    @Test
    public void testShortestPaths5() {
        DiGraph<String, Double> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 1.0));
        graph.addEdge(new Edge<>("B", "C", 2.0));
        graph.addEdge(new Edge<>("C", "D", 3.0));
        graph.addEdge(new Edge<>("A", "E", 4.0));
        graph.addEdge(new Edge<>("A", "F", 8.0));
        graph.addEdge(new Edge<>("B", "F", 6.0));
        graph.addEdge(new Edge<>("B", "G", 3.0));
        graph.addEdge(new Edge<>("C", "G", 2.0));
        graph.addEdge(new Edge<>("D", "G", 1.0));
        graph.addEdge(new Edge<>("D", "H", 5.0));
        graph.addEdge(new Edge<>("E", "F", 5.0));
        graph.addEdge(new Edge<>("G", "F", 1.0));
        graph.addEdge(new Edge<>("G", "H", 3.0));
        ShortestPaths<String, Double> shortestPaths = new ShortestPaths<>(graph, "A");
//        System.out.println(shortestPaths);
        assertTrue(shortestPaths.hasPathTo("H"));
        assertEquals(7.0, shortestPaths.cost("H"), 0);
    }

}