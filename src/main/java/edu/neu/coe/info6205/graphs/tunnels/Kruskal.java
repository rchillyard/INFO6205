package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.pq.PQException;
import edu.neu.coe.info6205.pq.PriorityQueue;
import edu.neu.coe.info6205.union_find.TypedUF;
import edu.neu.coe.info6205.union_find.TypedUF_HWQUPC;
import edu.neu.coe.info6205.union_find.UFException;
import edu.neu.coe.info6205.union_find.UF_HWQUPC;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/**
 * This is a generic solution for Kruskal's algorithm to find the minimum spanning tree of an edge-weighted graph
 */
public class Kruskal implements Iterable<Edge>{

    private final Queue<Edge> mst;
    private final PriorityQueue<Edge<String, Double>> pq;
    private final TypedUF<String> uf;
    private final int size;

    public Kruskal(EdgeGraph<String, Double> graph) {
        this.mst = new Queue_Elements<>();
        this.pq = createPQ(graph.edges());
        this.uf = createUF(graph.vertices());
        this.size = uf.size();
        try {
            runKruskal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runKruskal() throws PQException, UFException {
        while (!pq.isEmpty() && ((SizedIterable)mst).size() < size - 1) {
            Edge<String, Double> edge = pq.take();
            String s1 = edge.get(), s2 = edge.getOther(s1);
            // Maybe we should generalize UF to take a type rather than int.
            if (!uf.connected(s1, s2)) {
                uf.union(s1, s2);
                mst.enqueue(edge);
            }
        }
    }

    private TypedUF<String> createUF(SizedIterable<String> vertices) {
        return new TypedUF_HWQUPC<>(vertices);
    }

    private PriorityQueue<Edge<String, Double>> createPQ(SizedIterable<Edge<String, Double>> edges) {
        PriorityQueue<Edge<String, Double>> result = new PriorityQueue<>(edges.size(), false, new Comparator<Edge<String, Double>>() {
            @Override
            public int compare(Edge<String, Double> o1, Edge<String, Double> o2) {
                return o1.getAttribute().compareTo(o2.getAttribute());
            }
        });
        for (Edge<String, Double> e : edges) result.give(e);
        return result;
    }

    @Override
    public Iterator<Edge> iterator() {
        return mst.iterator();
    }

    public static Edge<String, Double> createEdge(String s1, String s2, double x) {
        return new Edge<String, Double>(s1, s2, x);
    }
}
