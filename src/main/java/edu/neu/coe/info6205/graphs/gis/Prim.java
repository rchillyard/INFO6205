package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;
import edu.neu.coe.info6205.pq.PQException;
import edu.neu.coe.info6205.pq.PriorityQueue;
import edu.neu.coe.info6205.union_find.UFException;

import java.util.*;

/**
 * This is a generic solution for Prim's algorithm to find the minimum spanning tree of an edge-weighted graph
 *
 * @param <V> is the type of each vertex.
 */
public class Prim<V, X extends Comparable<X> & Sequenced> extends MST<V, X> implements Iterable<Edge<V, X>> {

    // CONSIDER having a simpler constructor which just sets up the necessary structures, then having a run method which takes a graph and outputs an Iterable.
    public Prim(EdgeGraph<V, X> graph) {
        this.queue = new Queue_Elements<>();
        // TO BE IMPLEMENTED  : finish construction
        // This implementation is a lazy approach,
        // Translated from the code provided by Princeton University
        // https://algs4.cs.princeton.edu/43mst/LazyPrimMST.java.html
        this.vertexToInteger = new HashMap<>();
        int count = 0;
        for (V vertex : graph.vertices()) {
            vertexToInteger.put(vertex, count++);
        }
        this.graph = graph;
        this.size = graph.vertices().size();
        this.pq = createPQ(new Queue_Elements<>());
        marked = new boolean[size];
        try {
            mst = runPrim();
        } catch (Exception e) {
            e.printStackTrace();
        }
throw new RuntimeException("implementation missing");
    }

    public EdgeGraph<V, X> getMST() {
        int sequence = 0;
        EdgeGraph<V, X> result = new Graph_Edges<>();
        for (Edge<V, X> edge : queue) {
            edge.getAttribute().setSequence(sequence++);
            result.addEdge(edge);
        }
        return result;
    }

    @Override
    public Iterator<Edge<V, X>> iterator() {
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        for (Edge<V, X> edge : mst) result.add(edge);
        return result.iterator();
    }


    private Iterable<Edge<V, X>> runPrim() throws PQException {
        // TO BE IMPLEMENTED  : finish construction










throw new RuntimeException("implementation missing");
        //throw new RuntimeException("implementation missing");
    }

    // run Prim's algorithm
    private void prim(V v) throws PQException {
        scan(v); // scan vertex v and add all its edges to the fringe vertices to the pq
        while (!pq.isEmpty()) {                        // better to stop when mst has V-1 edges
            Edge<V, X> e = pq.take();                      // smallest edge on pq
            V u = e.get(), w = e.getOther(u);        // two endpoints
            int ui = vertexToInteger.get(u), wi = vertexToInteger.get(w); // their indices
            assert marked[ui] || marked[wi];
            if (marked[ui] && marked[wi]) continue;      // lazy, both v and w already scanned
            queue.enqueue(e);                            // add e to queue
            //weight += e.weight();
            if (!marked[ui]) scan(v);               // v becomes part of tree
            if (!marked[wi]) scan(w);               // w becomes part of tree
        }
    }

    // add all edges e incident to v onto pq if the other endpoint has not yet been scanned
    private void scan(V v) {
        int vi = vertexToInteger.get(v);
        assert !marked[vi];
        marked[vi] = true;
        for (Edge<V, X> e : graph.adjacent(v)) {
            V w = e.getOther(v);
            int wi = vertexToInteger.get(w);
            if (!marked[wi])
                pq.give(e);
        }
    }

    // This method is copied from Kruskal.java, needed for creating a priority queue of edges
    // those edges connecting the mst to the fringe vertices
    private PriorityQueue<Edge<V, X>> createPQ(SizedIterable<Edge<V, X>> edges) {
        PriorityQueue<Edge<V, X>> result = new PriorityQueue<>(edges.size(), false, Comparator.comparing(Edge::getAttribute), false);
        for (Edge<V, X> e : edges) result.give(e);
        return result;
    }

    private final Queue<Edge<V, X>> queue; // edges in the MST
    private Iterable<Edge<V, X>> mst;
    private final PriorityQueue<Edge<V, X>> pq; // edges with one endpoint in tree
    private final boolean[] marked;    // marked[v] = true iff v on tree
    private final EdgeGraph<V, X> graph;
    private final int size;
    private final Map<V, Integer> vertexToInteger;

    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }
}