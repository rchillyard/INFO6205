package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Queue_Elements;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;
import edu.neu.coe.info6205.union_find.TypedUF;
import edu.neu.coe.info6205.union_find.TypedUF_HWQUPC;
import edu.neu.coe.info6205.union_find.UFException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a generic solution for Boruvka's algorithm to find the minimum spanning tree of an edge-weighted graph
 *
 * @param <V> is the type of each vertex.
 */
public class Boruvka<V, X extends Comparable<X> & Sequenced> extends MST<V, X> {

    // The graph to find the MST of.
    private final EdgeGraph<V, X> graph;

    // The queue to store the edges in the MST.
    private final Queue_Elements<Edge<V, X>> queue;

    // The union-find data structure.
    private final TypedUF<V> uf;  // Change the type from Integer to V.

    // size: number of vertices in the input graph.
    private final int size;
    // The mapping from vertices to integers.
    private final Map<V, Integer> vertexToInteger;


    public Boruvka(EdgeGraph<V, X> graph) {
        this.vertexToInteger = new HashMap<>();
        int count = 0;
        for (V vertex : graph.vertices()) {
            vertexToInteger.put(vertex, count++);
        }
        this.graph = graph;
        this.queue = new Queue_Elements<>();
        SizedIterable<V> vertices = graph.vertices();
        this.uf = createUF(vertices);  // Pass the vertices directly to createUF.
        this.size = vertices.size();
        this.mst = new ArrayList<>();  // Initialize mst to an empty list.
        try {
            mst = runBoruvka();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private Iterable<Edge<V, X>> runBoruvka() throws UFException {
        // Create an array to hold the cheapest edge for each component.
        // Initially, there are as many components as the vertices
        // and all cheapest edge of each component is null, at this stage,
        // to be added
        //ArrayList<Edge<V, X>> cheapest = new ArrayList<Edge<V, X>>();
        //Edge<V, X>[] cheapest = new Edge[size];
        // queue is a queue of all edges in current mst
        // size is total number vertices in the graph
        // since MST is a DAG, the number of edges = number of vertices - 1
        // once this condition is fulfilled, we get a MST
        // before the queue of edges has size-1 elements, keep adding edges to the MST.
        for (int t = 1; t < size && this.queue.size() < size - 1; t = t + t) {

            // foreach tree in forest, find the closest edge
            // if edge weights are equal, ties are broken in favor of first edge in G.edges()
            //noinspection unchecked
            Edge<V, X>[] closest = new Edge[size];
            for (Edge<V, X> e : graph.edges()) {
                V v = e.get(), w = e.getOther(v);
                int vi = vertexToInteger.get(v), wi = vertexToInteger.get(w);
                int i = uf.find(vi), j = uf.find(wi);
                if (i == j) continue;   // same tree
                if (closest[i] == null || e.getAttribute().compareTo(closest[i].getAttribute()) < 0) closest[i] = e;
                if (closest[j] == null || e.getAttribute().compareTo(closest[j].getAttribute()) < 0) closest[j] = e;
            }

            // add newly discovered edges to MST
            for (int i = 0; i < size; i++) {
                Edge<V, X> e = closest[i];
                if (e != null) {
                    V v = e.get(), w = e.getOther(v);
                    int vi = vertexToInteger.get(v), wi = vertexToInteger.get(w);
                    // don't add the same edge twice
                    if (uf.find(vi) != uf.find(wi)) {
                        queue.offer(e);
                        //weight += e.weight();
                        uf.union(v, w);
                    }
                }
            }
        }
        // Convert the queue to an ArrayList and return it.
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        for (Edge<V, X> edge : queue) result.add(edge);
        return result;
    }

    private TypedUF<V> createUF(SizedIterable<V> vertices) {
        return new TypedUF_HWQUPC<>(vertices);
    }

    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }
}


/*
        while (((SizedIterable<?>) queue).size() < size - 1) {
            //System.out.println("Doing search round: "+ round++);
            // Find the cheapest edge for each component.
            for (Edge<V, X> edge : graph.edges()) { // iterate over the edge list in the graph
                V v = edge.get(), w = edge.getOther(v); // v and w are the 2 vertices of it
                int vi = vertexToInteger.get(v);
                int wi = vertexToInteger.get(w);
                //System.out.println(uf.find(vi) + uf.find(wi));
                if (uf.connected(v,w)) {//If v and w are in the same component, set both to lowest
                    //System.out.println(uf.find(vi) +" and "+ uf.find(wi)+" are connected");
                }
                else {
                    // if they are not in the same component yet, get the indices vi of v and wi of w
                    //int vi = vertexToInteger.get(v);
                    //int wi = vertexToInteger.get(w);
                    //System.out.println(uf.find(vi) +" and "+ uf.find(wi) + " are not connected");
                    if (cheapest[vi] == null || edge.getAttribute().compareTo(cheapest[vi].getAttribute()) < 0) cheapest[vi] = edge;
                    if (cheapest[wi] == null || edge.getAttribute().compareTo(cheapest[wi].getAttribute()) < 0) cheapest[wi] = edge;
                }
            }

            // Add the cheapest edges to the MST and merge their components.
            for (V v : graph.vertices()) {
                int vi = vertexToInteger.get(v);
                Edge<V, X> edge = cheapest[vi];
                if (edge != null) {
                    V w = edge.getOther(v);
                    int wi = vertexToInteger.get(w);
                    if (!uf.connected(v, w)) {  // Ignore if vertices v and w are already in the same component.
                        queue.enqueue(edge);
                        uf.union(v, w);
                        System.out.println(v +" and "+ w+" are "+ uf.connected(v, w) +" connected." );
                    }
                }
            }
 */