package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;

public interface MST<V, X extends Comparable<X> & Sequenced> extends Iterable<Edge<V, X>> {
    /**
     * Method to get the minimum spanning tree.
     *
     * @return the minimum spanning tree
     */
    EdgeGraph<V, X> getMST();
}
