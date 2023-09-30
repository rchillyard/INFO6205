package edu.neu.coe.info6205.graphs.BFS_and_Prims;

import edu.neu.coe.info6205.graphs.BFS_and_prims.Edge;
import edu.neu.coe.info6205.graphs.BFS_and_prims.EdgeWeightedGraph;
import edu.neu.coe.info6205.graphs.BFS_and_prims.Prims;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimsTest {

    @Test
    public void test1(){
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(9);
        Edge e1 = new Edge(0,1,4);
        Edge e2 = new Edge(0,7,8);
        Edge e3 = new Edge(1,2,8);
        Edge e4 = new Edge(1,7,11);
        Edge e5 = new Edge(7,8,7);
        Edge e6 = new Edge(7,6,1);
        Edge e7 = new Edge(2,8,2);
        Edge e8 = new Edge(2,3,7);
        Edge e9 = new Edge(8,6,6);
        Edge e10 = new Edge(2,5,4);
        Edge e11 = new Edge(5,3,14);
        Edge e12 = new Edge(5,4,10);
        Edge e13 = new Edge(3,4,9);
        Edge e14 = new Edge(6,5,2);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);
        edgeWeightedGraph.addEdge(e10);
        edgeWeightedGraph.addEdge(e11);
        edgeWeightedGraph.addEdge(e12);
        edgeWeightedGraph.addEdge(e13);
        edgeWeightedGraph.addEdge(e14);

        Prims prims = new Prims(edgeWeightedGraph);

        double weight_of_edges = 0;
        double expected = 37.0;
        for(Edge e : prims.edges()){
            weight_of_edges += e.getWeight();
        }
        assertEquals(expected,weight_of_edges,0.1);
    }

    @Test
    public void test2(){
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(7);
        Edge e1 = new Edge(0,1,7);
        Edge e2 = new Edge(0,2,8);
        Edge e3 = new Edge(1,2,3);
        Edge e4 = new Edge(1,3,6);
        Edge e5 = new Edge(2,3,4);
        Edge e6 = new Edge(2,4,3);
        Edge e7 = new Edge(3,5,5);
        Edge e8 = new Edge(4,5,2);
        Edge e9 = new Edge(3,4,2);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);

        Prims prims = new Prims(edgeWeightedGraph);

        double weight_of_edges = 0;
        double expected = 17.0;
        for(Edge e : prims.edges()){
            weight_of_edges += e.getWeight();
        }
        assertEquals(expected,weight_of_edges,0.1);
    }

    @Test
    public void test3(){
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(9);
        Edge e1 = new Edge(0,1,1);
        Edge e2 = new Edge(0,2,1);
        Edge e3 = new Edge(1,2,2);
        Edge e4 = new Edge(1,3,1);
        Edge e5 = new Edge(2,3,2);
        Edge e6 = new Edge(2,4,3);
        Edge e7 = new Edge(3,4,2);
        Edge e8 = new Edge(3,5,4);
        Edge e9 = new Edge(3,6,3);
        Edge e10 = new Edge(1,6,5);
        Edge e11 = new Edge(6,5,3);
        Edge e12 = new Edge(5,4,1);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);
        edgeWeightedGraph.addEdge(e10);
        edgeWeightedGraph.addEdge(e11);
        edgeWeightedGraph.addEdge(e12);

        Prims prims = new Prims(edgeWeightedGraph);

        double weight_of_edges = 0;
        double expected = 9.0;
        for(Edge e : prims.edges()){
            weight_of_edges += e.getWeight();
        }
        assertEquals(expected,weight_of_edges,0.1);
    }

    @Test
    public void test4(){
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(4);
        Edge e1 = new Edge(0,1,6);
        Edge e2 = new Edge(0,3,2);
        Edge e3 = new Edge(1,3,5);
        Edge e4 = new Edge(1,2,2);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);

        Prims prims = new Prims(edgeWeightedGraph);

        double weight_of_edges = 0;
        double expected = 9.0;
        for(Edge e : prims.edges()){
            weight_of_edges += e.getWeight();
        }
        assertEquals(expected,weight_of_edges,0.1);
    }

    @Test
    public void test5(){
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(7);
        Edge e1 = new Edge(0,1,28);
        Edge e2 = new Edge(0,2,10);
        Edge e3 = new Edge(1,5,14);
        Edge e4 = new Edge(1,6,16);
        Edge e5 = new Edge(4,6,12);
        Edge e6 = new Edge(5,4,18);
        Edge e7 = new Edge(3,4,22);
        Edge e8 = new Edge(3,5,24);
        Edge e9 = new Edge(2,3,25);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);

        Prims prims = new Prims(edgeWeightedGraph);

        double weight_of_edges = 0;
        double expected = 99.0;
        for(Edge e : prims.edges()){
            weight_of_edges += e.getWeight();
        }
        assertEquals(expected,weight_of_edges,0.1);
    }

    @Test
    public void test6(){
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(5);
        Edge e1 = new Edge(0,1,4);
        Edge e2 = new Edge(0,3,6);
        Edge e3 = new Edge(0,2,4);
        Edge e4 = new Edge(1,2,2);
        Edge e5 = new Edge(2,3,8);
        Edge e6 = new Edge(3,4,9);
        Edge e7 = new Edge(0,4,6);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);

        Prims prims = new Prims(edgeWeightedGraph);

        double weight_of_edges = 0;
        double expected = 18.0;
        for(Edge e : prims.edges()){
            weight_of_edges += e.getWeight();
        }
        assertEquals(expected,weight_of_edges,0.1);
    }

}