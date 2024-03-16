package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class MST<V, X extends Comparable<X> & Sequenced> implements Iterable<Edge<V, X>> {
    public MST() {
    }

    abstract EdgeGraph<V, X> getMST();

    public Iterator<Edge<V, X>> iterator() {
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        for (Edge<V, X> edge : mst) result.add(edge);
        return result.iterator();
    }

    protected Iterable<Edge<V, X>> mst;

}