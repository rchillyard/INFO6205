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
 * This is a generic solution for Kruskal's algorithm to find the minimum spanning tree of an edge-weighted graph
 *
 * @tparam V is the type of each vertex.
 */
public class Kruskal<V, X extends Comparable<X> & Sequenced> implements Iterable<Edge<V, X>> {

    // CONSIDER having a simpler constructor which just sets up the necessary structures, then having a run method which takes a graph and outputs an Iterable.
    public Kruskal(EdgeGraph<V, X> graph) {
        this.queue = new Queue_Elements<>();
//        showEdgesInSequence(graph);
        this.pq = createPQ(graph.edges());
        this.uf = createUF(graph.vertices());
        this.size = uf.size();
        try {
            mst = runKruskal();
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


    private Iterable<Edge<V, X>> runKruskal() throws PQException, UFException {
        while (!pq.isEmpty() && ((SizedIterable<?>) queue).size() < size - 1) {
            Edge<V, X> edge = pq.take();
            V s1 = edge.get(), s2 = edge.getOther(s1);
            if (!uf.connected(s1, s2)) {
                uf.union(s1, s2);
                queue.enqueue(edge);
            }
        }
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        for (Edge<V, X> edge : queue) result.add(edge);
        return result;
    }

    private TypedUF<V> createUF(SizedIterable<V> vertices) {
        return new TypedUF_HWQUPC<>(vertices);
    }

    private PriorityQueue<Edge<V, X>> createPQ(SizedIterable<Edge<V, X>> edges) {
        PriorityQueue<Edge<V, X>> result = new PriorityQueue<>(edges.size(), false, Comparator.comparing(Edge::getAttribute), false);
        for (Edge<V, X> e : edges) result.give(e);
        return result;
    }

    private void showEdgesInSequence(EdgeGraph<V, X> graph) {
        // TODO remove this debugging code
        PriorityQueue<Edge<V, X>> tempPQ = createPQ(graph.edges());
        while (!tempPQ.isEmpty()) {
            try {
                System.out.println(tempPQ.take());
            } catch (PQException e) {
                e.printStackTrace();
            }
        }
    }

    private final Queue<Edge<V, X>> queue;
    private final PriorityQueue<Edge<V, X>> pq;
    private final TypedUF<V> uf;
    private final int size;
    private Iterable<Edge<V, X>> mst;


    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }
}
