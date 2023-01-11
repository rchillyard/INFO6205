package edu.neu.coe.info6205.graphs.generic_BFS_and_prims;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph<T> {
    private int V;   // No. of vertices
    private int E;   // No. of edges
    //private  Bag<T> adj[]; //Adjacency Lists
    private HashMap<T, LinkedList<T>> adj;

    public Graph(int V){
        this.V=V;
        this.E=0;
        adj = new HashMap<>();
//      for(int i = 0; i<V; i++){
//            adj.put(i , new Bag<T>());
//       }

    }

    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    public void addVertex(T v){
        adj.put(v,new LinkedList<T>());
    }

    public void addEdge(T v,T w){
        adj.get(v).add(w);
        adj.get(w).add(v);
        E++;
    }

    public Iterator<T> adj(T v){
        return adj.get(v).iterator();
    }
}
