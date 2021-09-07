package edu.neu.coe.info6205.graphs.BFS_and_prims;

import java.util.*;

public class BFS {
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists

    public BFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public List<Integer> BFS(int s) {
        // TO BE IMPLEMENTED
        return null;
    }
}
