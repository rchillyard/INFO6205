package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.SizedIterableImpl;
import edu.neu.coe.info6205.graphs.gis.Boruvka;
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

    static class Route implements Comparable<Route>, Sequenced {

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

        // Added a method  getCost() to get the cost of this route
        public int getCost() {
            return (int) this.cost;
        }

        private final double cost;
        private int sequence;
    }
/*
    @Test
    public void testKruskal() {
        EdgeGraph<String, Route> kalimantan = kalimantan();
        ArrayList<Edge<String, Route>> edgeList = new ArrayList<>();
        for (Edge<String, Route> edge : kalimantan.edges()) edgeList.add(edge);
        Edge<String, Route> edge1 = edgeList.get(5);
        Edge<String, Route> edge2 = edgeList.get(0);
        Iterable<Edge<String, Route>> iterable = new Kruskal<>(kalimantan);
        Iterator<Edge<String, Route>> iterator = iterable.iterator();
        //assertTrue(iterator.hasNext());
        //assertEquals(edge1, iterator.next());
        //assertEquals(edge2, iterator.next());
        //assertEquals(5, SizedIterableImpl.getSize(iterable.iterator()));
    }
*/

    @Test
    public void testKruskal_kalimantan_with_cost() {
        // build an edge graph kalimantan, with 6 vertices and 15 edges
        EdgeGraph<String, KruskalTest.Route> kalimantan = kalimantan();
        // keep all the edges in a list called edgeList
        ArrayList<Edge<String, KruskalTest.Route>> edgeList = new ArrayList<>();
        for (Edge<String, KruskalTest.Route> edge : kalimantan.edges()) edgeList.add(edge);
        try {
            Iterable<Edge<String, KruskalTest.Route>> iterable = new Boruvka<>(kalimantan);
            Iterator<Edge<String, KruskalTest.Route>> iterator = iterable.iterator();
            assertTrue(iterator.hasNext());
            assertEquals(5, SizedIterableImpl.getSize(iterable.iterator()));
            double cost = 0;
            for (Edge<String, KruskalTest.Route> edge : iterable) {
                cost += edge.getAttribute().getCost();
                String v = edge.get(), w = edge.getOther(v);
                System.out.println("v1= " + v + " v2= " + w + " cost = " + edge.getAttribute().getCost());
            }
            System.out.println("Total cost = " + cost);
        } catch (Exception e) {
            e.printStackTrace();
            // Test will fail due to the uncaught exception.
        }
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


    private EdgeGraph<String, Route> ChinaShippingCost() {
        //          BeiJing  ShangHai  GuangZhou  ShenZhen  XiAn  WuHan  ZhengZhou  FuZhou
        // BeiJing            3000      5000       5100     3500   3000     1000      5100
        // ShangHai                    3500       3400     2300   1200     2300      1600
        // GuangZhou                               400      2800   3200     3200      400
        // ShenZhen                                          2200   2200     2200      1900
        // XiAn                                                     3500     2000      3500
        // WuHan                                                              1100      1500
        // ZhengZhou
        // FuZhou
        Graph_Edges<String, Route> g = new Graph_Edges<>();
        g.addEdge(createEdge("BeiJing", "ShangHai", new Route(3000.)));
        g.addEdge(createEdge("BeiJing", "ZhengZhou", new Route(1000.)));
        g.addEdge(createEdge("XiAn", "BeiJing", new Route(3500.)));
        g.addEdge(createEdge("XiAn", "ZhengZhou", new Route(2000.)));
        g.addEdge(createEdge("ZhengZhou", "WuHan", new Route(1100.)));
        g.addEdge(createEdge("GuangZhou", "XiAn", new Route(2800.)));
        g.addEdge(createEdge("WuHan", "ShangHai", new Route(1200.)));
        g.addEdge(createEdge("ShangHai", "ZhengZhou", new Route(2300.)));
        g.addEdge(createEdge("GuangZhou", "ZhengZhou", new Route(3200.)));
        g.addEdge(createEdge("GuangZhou", "ShenZhen", new Route(400.)));
        g.addEdge(createEdge("ShenZhen", "WuHan", new Route(2200.)));
        g.addEdge(createEdge("ShenZhen", "FuZhou", new Route(1900.)));
        g.addEdge(createEdge("FuZhou", "ShangHai", new Route(1600.)));
        g.addEdge(createEdge("FuZhou", "WuHan", new Route(1500.)));
        return g;
    }

    @Test
    public void testKruskal_ChinaShippingCost_with_cost() {
        EdgeGraph<String, KruskalTest.Route> chinaShippingCost = ChinaShippingCost();
        ArrayList<Edge<String, KruskalTest.Route>> edgeList = new ArrayList<>();
        for (Edge<String, KruskalTest.Route> edge : chinaShippingCost.edges()) edgeList.add(edge);
        try {
            Iterable<Edge<String, KruskalTest.Route>> iterable = new Kruskal<>(chinaShippingCost);
            Iterator<Edge<String, KruskalTest.Route>> iterator = iterable.iterator();
            double cost = 0;
            for (Edge<String, KruskalTest.Route> edge : iterable) {
                cost += edge.getAttribute().getCost();
                String v = edge.get(), w = edge.getOther(v);
                System.out.println("v1= " + v + " v2= " + w + " cost = " + edge.getAttribute().getCost());
            }
            System.out.println("Total cost = " + cost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


