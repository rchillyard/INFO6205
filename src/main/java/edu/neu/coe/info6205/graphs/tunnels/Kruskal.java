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

import java.util.Comparator;
import java.util.Iterator;

/**
 * This is a generic solution for Kruskal's algorithm to find the minimum spanning tree of an edge-weighted graph
 *
 * @tparam Vertex is the type of each vertex.
 *
 */
public class Kruskal<Vertex> implements Iterable<Edge> {

    private final Queue<Edge> mst;
    private final PriorityQueue<Edge<Vertex, Double>> pq;
    private final TypedUF<Vertex> uf;
    private final int size;

    public Kruskal(EdgeGraph<Vertex, Double> graph) {
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
            Edge<Vertex, Double> edge = pq.take();
            Vertex s1 = edge.get(), s2 = edge.getOther(s1);
            // Maybe we should generalize UF to take a type rather than int.
            if (!uf.connected(s1, s2)) {
                uf.union(s1, s2);
                mst.enqueue(edge);
            }
        }
    }

    private TypedUF<Vertex> createUF(SizedIterable<Vertex> vertices) {
        return new TypedUF_HWQUPC<>(vertices);
    }

    private PriorityQueue<Edge<Vertex, Double>> createPQ(SizedIterable<Edge<Vertex, Double>> edges) {
        PriorityQueue<Edge<Vertex, Double>> result = new PriorityQueue<>(edges.size(), false, Comparator.comparing(Edge::getAttribute));
        for (Edge<Vertex, Double> e : edges) result.give(e);
        return result;
    }

    @Override
    public Iterator<Edge> iterator() {
        return mst.iterator();
    }

    public static <Vertex> Edge<Vertex, Double> createEdge(Vertex v1, Vertex v2, double x) {
        return new Edge<>(v1, v2, x);
    }
}
