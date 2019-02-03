package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.graphs.gis.Kruskal;
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

    @Test
    public void testKruskal() {
        EdgeGraph<String, Double> kalimantan = kalimantan();
        ArrayList<Edge<String, Double>> edgeList = new ArrayList<>();
        for (Edge<String, Double> edge : kalimantan.edges()) edgeList.add(edge);
//        System.out.println(edgeList);
        Edge<String, Double> edge1 = edgeList.get(5);
        Edge<String, Double> edge2 = edgeList.get(0);
        Kruskal<String, Double> kruskal = new Kruskal<>(kalimantan);
        Iterator<Edge> iterator = kruskal.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(edge1, iterator.next());
        assertEquals(edge2, iterator.next());
        assertEquals(5,SizedIterableImpl.getSize(kruskal.iterator()));
    }

    private EdgeGraph<String, Double> kalimantan() {
//        Po	    Pa	Ban	Bal	S	T
//        Po	 	80	101	123	237	417
//        Pa	 	 	56	64	83	187
//        Ban	 	 	 	73	95	203
//        Bal	 	 	 	 	23	89
//        S	 	 	 	 	 	63
//        T
        Graph_Edges<String, Double> g = new Graph_Edges<>();
        g.addEdge(createEdge("Po", "Pa", 80.));
        g.addEdge(createEdge("Po", "Ban", 101.));
        g.addEdge(createEdge("Po", "Bal", 123.));
        g.addEdge(createEdge("Po", "S", 237.));
        g.addEdge(createEdge("Po", "T", 417.));
        g.addEdge(createEdge("Pa", "Ban", 56.));
        g.addEdge(createEdge("Pa", "Bal", 64.));
        g.addEdge(createEdge("Pa", "S", 83.));
        g.addEdge(createEdge("Pa", "T", 187.));
        g.addEdge(createEdge("Ban", "Bal", 73.));
        g.addEdge(createEdge("Ban", "S", 95.));
        g.addEdge(createEdge("Ban", "T", 203.));
        g.addEdge(createEdge("Bal", "S", 23.));
        g.addEdge(createEdge("Bal", "T", 89.));
        g.addEdge(createEdge("S", "T", 63.));
        return g;
    }
}
