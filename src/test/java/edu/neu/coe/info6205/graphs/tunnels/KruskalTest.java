package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.graphs.gis.Kruskal;
import edu.neu.coe.info6205.graphs.gis.Sequenced;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static edu.neu.coe.info6205.graphs.gis.Kruskal.createEdge;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KruskalTest {

    class Route implements Comparable<Route>, Sequenced {

        @Override
        public int getSequence() {
            return sequence;
        }

        @Override
        public void setSequence(int sequence) {

        }

        @Override
        public int compareTo(Route o) {
            return Double.compare(cost, o.cost);
        }

        public Route(double cost) {
            this.cost = cost;
        }

        private final double cost;
        private int sequence;
    }

    @Test
    public void testKruskal() {
        EdgeGraph<String, Route> kalimantan = kalimantan();
        ArrayList<Edge<String, Route>> edgeList = new ArrayList<>();
        for (Edge<String, Route> edge : kalimantan.edges()) edgeList.add(edge);
        Edge<String, Route> edge1 = edgeList.get(5);
        Edge<String, Route> edge2 = edgeList.get(0);
        Iterable<Edge<String, Route>> iterable = new Kruskal<>(kalimantan);
        Iterator<Edge<String, Route>> iterator = iterable.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(edge1, iterator.next());
        assertEquals(edge2, iterator.next());
        assertEquals(5, SizedIterableImpl.getSize(iterable.iterator()));
    }

    private EdgeGraph<String, Route> kalimantan() {
//        Po	    Pa	Ban	Bal	S	T
//        Po	 	80	101	123	237	417
//        Pa	 	 	56	64	83	187
//        Ban	 	 	 	73	95	203
//        Bal	 	 	 	 	23	89
//        S	 	 	 	 	 	63
//        T
        Graph_Edges<String, Route> g = new Graph_Edges<>();
        g.addEdge(createEdge("Po", "Pa", new Route(80.)));
        g.addEdge(createEdge("Po", "Ban", new Route(101.)));
        g.addEdge(createEdge("Po", "Bal", new Route(123.)));
        g.addEdge(createEdge("Po", "S", new Route(237.)));
        g.addEdge(createEdge("Po", "T", new Route(417.)));
        g.addEdge(createEdge("Pa", "Ban", new Route(56.)));
        g.addEdge(createEdge("Pa", "Bal", new Route(64.)));
        g.addEdge(createEdge("Pa", "S", new Route(83.)));
        g.addEdge(createEdge("Pa", "T", new Route(187.)));
        g.addEdge(createEdge("Ban", "Bal", new Route(73.)));
        g.addEdge(createEdge("Ban", "S", new Route(95.)));
        g.addEdge(createEdge("Ban", "T", new Route(203.)));
        g.addEdge(createEdge("Bal", "S", new Route(23.)));
        g.addEdge(createEdge("Bal", "T", new Route(89.)));
        g.addEdge(createEdge("S", "T", new Route(63.)));
        return g;
    }
}
