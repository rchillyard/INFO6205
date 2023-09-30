package edu.neu.coe.info6205.graphs.DijkstraTest;

import edu.neu.coe.info6205.graphs.Dijkstra.Dijkstra;
import edu.neu.coe.info6205.graphs.Dijkstra.DirectedEdge;
import edu.neu.coe.info6205.graphs.Dijkstra.EdgeWeightedDigraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DijkstraTest {
    @Test
    public void test1(){

        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(9);
        DirectedEdge e = new DirectedEdge(0, 1, 4);
        DirectedEdge e1 = new DirectedEdge(0, 7, 8);
        DirectedEdge e2 = new DirectedEdge(1, 2, 8);
        DirectedEdge e3 = new DirectedEdge(1, 7, 11);
        DirectedEdge e4 = new DirectedEdge(7, 6, 1);
        DirectedEdge e5 = new DirectedEdge(7, 8, 11);
        DirectedEdge e6 = new DirectedEdge(2, 8, 2);
        DirectedEdge e7 = new DirectedEdge(2, 3, 7);
        DirectedEdge e8 = new DirectedEdge(2, 5, 4);
        DirectedEdge e9 = new DirectedEdge(8, 6, 6);
        DirectedEdge e10 = new DirectedEdge(6, 5, 2);
        DirectedEdge e11 = new DirectedEdge(3, 5, 14);
        DirectedEdge e12 = new DirectedEdge(3, 4, 9);
        DirectedEdge e13 = new DirectedEdge(5, 4, 10);

        ewd.addEdge(e);
        ewd.addEdge(e1);
        ewd.addEdge(e2);
        ewd.addEdge(e3);
        ewd.addEdge(e4);
        ewd.addEdge(e5);
        ewd.addEdge(e6);
        ewd.addEdge(e7);
        ewd.addEdge(e8);
        ewd.addEdge(e9);
        ewd.addEdge(e10);
        ewd.addEdge(e11);
        ewd.addEdge(e12);
        ewd.addEdge(e13);

        Dijkstra dijkstra = new Dijkstra(ewd);
        final Dijkstra.ShortestPaths shortestPaths = dijkstra.shortestPaths(0);
        List<Double> dist = new ArrayList<>();
        for (int i = 0; i < ewd.V(); i++)
            if (shortestPaths.hasPathTo(i))
                dist.add(shortestPaths.distTo(i));
        assertEquals(dist.get(0), 0, 0);
        assertEquals(dist.get(1), 4, 0);
        assertEquals(dist.get(2), 12, 0);
        assertEquals(dist.get(3), 19, 0);
        assertEquals(dist.get(4), 21, 0);
        assertEquals(dist.get(5), 11, 0);
        assertEquals(dist.get(6), 9, 0);
        assertEquals(dist.get(7), 8, 0);
        assertEquals(dist.get(8), 14, 0);
    }

    @Test
    public void test2(){

        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(8);
        DirectedEdge e = new DirectedEdge(0, 4, 0.38);
        DirectedEdge e1 = new DirectedEdge(6, 4, 0.93);
        DirectedEdge e2 = new DirectedEdge(0, 2, 0.26);
        DirectedEdge e3 = new DirectedEdge(5, 7, 0.28);
        DirectedEdge e4 = new DirectedEdge(4, 7, 0.36);
        DirectedEdge e5 = new DirectedEdge(7, 5, 0.28);
        DirectedEdge e6 = new DirectedEdge(2, 7, 0.34);
        DirectedEdge e7 = new DirectedEdge(7, 3, 0.39);
        DirectedEdge e8 = new DirectedEdge(1, 3, 0.29);
        DirectedEdge e9 = new DirectedEdge(6, 0, 0.58);
        DirectedEdge e10 = new DirectedEdge(6, 2, 0.4);
        DirectedEdge e11 = new DirectedEdge(5, 1, 0.32);
        DirectedEdge e12 = new DirectedEdge(3, 6, 0.52);
        DirectedEdge e13 = new DirectedEdge(4, 5, 0.35);
        DirectedEdge e14 = new DirectedEdge(5, 4, 0.35);

        ewd.addEdge(e);
        ewd.addEdge(e1);
        ewd.addEdge(e2);
        ewd.addEdge(e3);
        ewd.addEdge(e4);
        ewd.addEdge(e5);
        ewd.addEdge(e6);
        ewd.addEdge(e7);
        ewd.addEdge(e8);
        ewd.addEdge(e9);
        ewd.addEdge(e10);
        ewd.addEdge(e11);
        ewd.addEdge(e12);
        ewd.addEdge(e13);
        ewd.addEdge(e14);

        Dijkstra dijkstra = new Dijkstra(ewd);
        final Dijkstra.ShortestPaths shortestPaths = dijkstra.shortestPaths(0);
        List<Double> dist = new ArrayList<>();
        for (int i = 0; i < ewd.V(); i++)
            if (shortestPaths.hasPathTo(i))
                dist.add(shortestPaths.distTo(i));

        assertEquals(dist.get(0), 0, 0.2);
        assertEquals(dist.get(1), 1.05, 0.2);
        assertEquals(dist.get(2), 0.26, 0.2);
        assertEquals(dist.get(3), 0.99, 0.2);
        assertEquals(dist.get(4), 0.38, 0.2);
        assertEquals(dist.get(5), 0.73, 0.2);
        assertEquals(dist.get(6), 1.51, 0.2);
        assertEquals(dist.get(7), 0.60, 0.2);
    }

    @Test
    public void test3(){

        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(7);
        DirectedEdge e = new DirectedEdge(0, 1, 3);
        DirectedEdge e1 = new DirectedEdge(0, 3, 2);
        DirectedEdge e2 = new DirectedEdge(0, 6, 6);
        DirectedEdge e3 = new DirectedEdge(1, 2, 6);
        DirectedEdge e4 = new DirectedEdge(1, 4, 1);
        DirectedEdge e5 = new DirectedEdge(2, 5, 1);
        DirectedEdge e6 = new DirectedEdge(3, 4, 3);
        DirectedEdge e7 = new DirectedEdge(3, 1, 2);
        DirectedEdge e8 = new DirectedEdge(4, 5, 4);
        DirectedEdge e9 = new DirectedEdge(6, 5, 2);

        ewd.addEdge(e);
        ewd.addEdge(e1);
        ewd.addEdge(e2);
        ewd.addEdge(e3);
        ewd.addEdge(e4);
        ewd.addEdge(e5);
        ewd.addEdge(e6);
        ewd.addEdge(e7);
        ewd.addEdge(e8);
        ewd.addEdge(e9);


        Dijkstra dijkstra = new Dijkstra(ewd);
        final Dijkstra.ShortestPaths shortestPaths = dijkstra.shortestPaths(0);
        List<Double> dist = new ArrayList<>();
        for (int i = 0; i < ewd.V(); i++)
            if (shortestPaths.hasPathTo(i))
                dist.add(shortestPaths.distTo(i));

        assertEquals(dist.get(0), 0, 0);
        assertEquals(dist.get(1), 3, 0);
        assertEquals(dist.get(2), 9, 0);
        assertEquals(dist.get(3), 2, 0);
        assertEquals(dist.get(4), 4, 0);
        assertEquals(dist.get(5), 8, 0);
        assertEquals(dist.get(6), 6, 0);
    }

    @Test
    public void test4(){

        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(7);
        DirectedEdge e = new DirectedEdge(0, 1, 2);
        DirectedEdge e1 = new DirectedEdge(0, 2, 6);
        DirectedEdge e2 = new DirectedEdge(1, 3, 5);
        DirectedEdge e3 = new DirectedEdge(2, 3, 8);
        DirectedEdge e4 = new DirectedEdge(3, 5, 15);
        DirectedEdge e5 = new DirectedEdge(3, 4, 10);
        DirectedEdge e6 = new DirectedEdge(5, 6, 6);
        DirectedEdge e7 = new DirectedEdge(4, 6, 2);
        DirectedEdge e8 = new DirectedEdge(4, 5, 6);

        ewd.addEdge(e);
        ewd.addEdge(e1);
        ewd.addEdge(e2);
        ewd.addEdge(e3);
        ewd.addEdge(e4);
        ewd.addEdge(e5);
        ewd.addEdge(e6);
        ewd.addEdge(e7);
        ewd.addEdge(e8);


        Dijkstra dijkstra = new Dijkstra(ewd);
        final Dijkstra.ShortestPaths shortestPaths = dijkstra.shortestPaths(0);
        List<Double> dist = new ArrayList<>();
        for (int i = 0; i < ewd.V(); i++)
            if (shortestPaths.hasPathTo(i))
                dist.add(shortestPaths.distTo(i));

        assertEquals(dist.get(0), 0, 0);
        assertEquals(dist.get(1), 2, 0);
        assertEquals(dist.get(2), 6, 0);
        assertEquals(dist.get(3), 7, 0);
        assertEquals(dist.get(4), 17, 0);
        assertEquals(dist.get(5), 22, 0);
        assertEquals(dist.get(6), 19, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test5(){

        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(4);
        DirectedEdge e = new DirectedEdge(0, 1, 2);
        DirectedEdge e1 = new DirectedEdge(0, 2, -6);
        DirectedEdge e2 = new DirectedEdge(1, 3, 5);
        ewd.addEdge(e);
        ewd.addEdge(e1);
        ewd.addEdge(e2);
        Dijkstra dijkstra = new Dijkstra(ewd);
        dijkstra.shortestPaths(0);
    }
}
