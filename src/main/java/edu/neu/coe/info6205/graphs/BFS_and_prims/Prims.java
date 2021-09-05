package edu.neu.coe.info6205.graphs.BFS_and_prims;

import java.util.LinkedList;
import java.util.Queue;

public class Prims {
    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // crossing (and ineligible) edges
    public Prims(EdgeWeightedGraph G)
    {
        // TO BE IMPLEMENTED
    }
    private void visit(EdgeWeightedGraph G, int v)
    { // Mark v and add to pq all edges from v to unmarked vertices.
        // TO BE IMPLEMENTED
    }

    public Iterable<Edge> edges()
    {
        return mst;
    }
}

