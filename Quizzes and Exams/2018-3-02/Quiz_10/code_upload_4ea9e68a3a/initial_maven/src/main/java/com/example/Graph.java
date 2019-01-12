package com.example;
import java.util.ArrayList;

public class Graph {
    private final int V;
    private int E;
    private ArrayList<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public Graph(int V, int[][] in) {
        this(V);
        for (int i = 0; i < in.length; i++) {
            int v = in[i][0];
            int w = in[i][1];
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
