package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;
import edu.neu.coe.info6205.pq.PQException;
import edu.neu.coe.info6205.pq.PriorityQueue;
import edu.neu.coe.info6205.union_find.TypedUF;
import edu.neu.coe.info6205.union_find.TypedUF_HWQUPC;
import edu.neu.coe.info6205.union_find.UFException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This is a generic solution for Prim's algorithm to find the minimum spanning tree of an edge-weighted graph
 *
 *
 *
 * @param <V> is the type of each vertex.
 */
public class Prim<V, X extends Comparable<X> & Sequenced> implements Iterable<Edge<V, X>> {

    // CONSIDER having a simpler constructor which just sets up the necessary structures, then having a run method which takes a graph and outputs an Iterable.
    public Prim(EdgeGraph<V, X> graph) {
        this.queue = new Queue_Elements<>();
        // FIXME : finish construction by replacing the following code
        // END 
        try {
            mst = runPrim();
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

    @Override
    public Iterator<Edge<V, X>> iterator() {
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        for (Edge<V, X> edge : mst) result.add(edge);
        return result.iterator();
    }


    private Iterable<Edge<V, X>> runPrim() throws PQException, UFException {
        // FIXME : finish construction by replacing the following code
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        for (Edge<V, X> edge : queue) result.add(edge);
        return result;
        // END 
    }


    private final Queue<Edge<V, X>> queue;
    private Iterable<Edge<V, X>> mst;


    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }
}
