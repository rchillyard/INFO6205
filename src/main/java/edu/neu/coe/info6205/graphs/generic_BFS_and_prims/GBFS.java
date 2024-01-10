/* @author Urvi Aryamane */

package edu.neu.coe.info6205.graphs.generic_BFS_and_prims;

import java.util.*;

public class GBFS<T> {
    //private T[] edgeTo;
    private HashMap<T, T> edgeTo;
    private final T s; // Source vertex
    //private boolean[] marked;
    private HashMap<T,Boolean> marked;

    public GBFS(Graph G, T s) {
        //marked = new boolean[G.V()];
        marked = new HashMap<>();
        //edgeTo = (T[]) new Object[G.V()];
        edgeTo= new HashMap<>();
        this.s=s;
        //bfs(G,s);

    }
    public List<T> bfs(Graph G, T s){
        //QueueG<T> queue = new QueueG<T>(G.V());
        LinkedList<T> queue = new LinkedList<T>();
        //marked[s]=true;
        marked.put(s,true);
        queue.add(s);
        List<T> output = new ArrayList<>();
        while(queue.size() != 0) {
            s = queue.poll();
            output.add(s);
            Iterator<T> i =  G.adj(s);
            while (i.hasNext()) {
                T n = i.next();
//                System.out.println(n);
                if (!marked.containsKey(n)) {
                    marked.put(n,true);
                    queue.add(n);
                }
            }
        }
        return output;
    }
}