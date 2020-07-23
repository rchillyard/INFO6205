package edu.neu.coe.info6205.graphs.gis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class Prim<V, X extends Comparable<X> & Sequenced> implements Iterable<Edge<V, X>> {

    // CONSIDER having a simpler constructor which just sets up the necessary structures, then having a run method which takes a graph and outputs an Iterable.
    public Prim(EdgeGraph<V, X> graph, V startingpoint) {
        this.queue = new Queue_Elements<>();
        this.marked = createMark(graph); //tag which vertices are connected to the spanning
//        showEdgesInSequence(graph);
        this.pq = createPQ(graph.edges());
        //this.uf = createUF(graph.vertices());
        //this.size = uf.size();
        this.numberEdges = graph.vertices().size()-1;//the number of edges to be connected
        try {
            mst = runPrim(graph,startingpoint);
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

    private Map<V, Boolean> createMark(EdgeGraph<V,X> graph) {
        Map<V,Boolean> result = new HashMap<>();
        for(V v:graph.vertices()) result.put(v,false);
        return result;
    }

    private Iterable<Edge<V, X>> runPrim(EdgeGraph<V, X> graph, V startingpoint) throws PQException, UFException {
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        while (!pq.isEmpty() && ((SizedIterable<?>) queue).size() < numberEdges) {
            Edge<V,X> e = pq.take();
            V v = e.get(), w = e.getOther(v);
            //if both vertices are in the spanning, skip the loop
            if(marked.get(v)&&marked.get(w)) continue;
            //otherwise, add the edge and tag the connected vertices
            result.add(e);
            if(!marked.get(v)) visit(graph,v);
            if(!marked.get(w)) visit(graph,w);
        }
        return result;
    }

    private void visit(EdgeGraph<V,X> graph, V v) {
        marked.replace(v, true);
        for(Edge e:graph.adjacent(v)) {
            if(!marked.get(e.getOther(v))) {
                pq.give(e);
            }
        }
    }

    private TypedUF<V> createUF(SizedIterable<V> vertices) {
        return new TypedUF_HWQUPC<>(vertices);
    }

    private PriorityQueue<Edge<V, X>> createPQ(SizedIterable<Edge<V, X>> edges) {
        PriorityQueue<Edge<V, X>> result = new PriorityQueue<>(edges.size(), false, Comparator.comparing(Edge::getAttribute));
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

    private Map<V,Boolean> marked;
    private final Queue<Edge<V, X>> queue;
    private final PriorityQueue<Edge<V, X>> pq;
    //private final TypedUF<V> uf;
    //private final int size;
    private final int numberEdges;
    private Iterable<Edge<V, X>> mst;


    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }
}