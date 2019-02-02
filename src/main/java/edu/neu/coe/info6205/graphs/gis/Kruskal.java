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
 *
 */
public class Kruskal<V> implements Iterable<Edge> {

    // CONSIDER having a simpler constructor which just sets up the necessary structures, then having a run method which takes a graph and outputs an Iterable.
    public Kruskal(EdgeGraph<V, Double> graph) {
        this.queue = new Queue_Elements<>();
        this.pq = createPQ(graph.edges());
        this.uf = createUF(graph.vertices());
        this.size = uf.size();
        try {
            mst = runKruskal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EdgeGraph<V, Double> getMST() {
        EdgeGraph<V, Double> result = new Graph_Edges<>();
        for (Edge edge : queue)  //noinspection unchecked
            result.addEdge(edge);
        return result;
    }

    @Override
    public Iterator<Edge> iterator() {
        ArrayList<Edge> result = new ArrayList<>();
        for (Edge<V, Double> edge : mst) result.add(edge);
        return result.iterator();
    }


    private Iterable<Edge<V, Double>> runKruskal() throws PQException, UFException {
        while (!pq.isEmpty() && ((SizedIterable) queue).size() < size - 1) {
            Edge<V, Double> edge = pq.take();
            V s1 = edge.get(), s2 = edge.getOther(s1);
            if (!uf.connected(s1, s2)) {
                uf.union(s1, s2);
                queue.enqueue(edge);
            }
        }
        ArrayList<Edge<V, Double>> result = new ArrayList<>();
        //noinspection unchecked
        for (Edge<V, Double> edge : queue) result.add(edge);
        return result;
    }

    private TypedUF<V> createUF(SizedIterable<V> vertices) {
        return new TypedUF_HWQUPC<>(vertices);
    }

    private PriorityQueue<Edge<V, Double>> createPQ(SizedIterable<Edge<V, Double>> edges) {
        PriorityQueue<Edge<V, Double>> result = new PriorityQueue<>(edges.size(), false, Comparator.comparing(Edge::getAttribute));
        for (Edge<V, Double> e : edges) result.give(e);
        return result;
    }

    private final Queue<Edge> queue;
    private final PriorityQueue<Edge<V, Double>> pq;
    private final TypedUF<V> uf;
    private final int size;
    private Iterable<Edge<V, Double>> mst;


    public static <V> Edge<V, Double> createEdge(V v1, V v2, double x) {
        return new Edge<>(v1, v2, x);
    }
}
