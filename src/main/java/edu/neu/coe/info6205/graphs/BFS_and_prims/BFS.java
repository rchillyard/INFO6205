/* @author Urvi Aryamane */

package edu.neu.coe.info6205.graphs.BFS_and_prims;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);
        List<Integer> output = new ArrayList<>();

        while (queue.size() != 0) {
            s = queue.poll();
            output.add(s);

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
//                System.out.println(n);
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return output;
    }
}