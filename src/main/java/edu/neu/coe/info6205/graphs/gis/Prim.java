package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.pq.PQException;
import edu.neu.coe.info6205.union_find.UFException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prim<V, X extends Comparable<X> & Sequenced> extends MST_Base<V,X> {

    // CONSIDER having a simpler constructor which just sets up the necessary structures, then having a run method which takes a graph and outputs an Iterable.
    public Prim(EdgeGraph<V, X> graph, V startingpoint) {
    	super(graph);
    	this.graph = graph;
    	this.startpoint = startingpoint;
    	this.marked = createMark(graph); //tag which vertices are connected to the spanning
        this.numberEdges = graph.vertices().size()-1;//the number of edges to be connected
        try {
            mst = runAlgorithm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected Iterable<Edge<V, X>> runAlgorithm() throws PQException, UFException {
    	ArrayList<Edge<V, X>> result = new ArrayList<>();
    	visit(graph,startpoint);
    	while (!pq.isEmpty() && ((SizedIterable<?>) queue).size() < numberEdges) {
    		Edge<V,X> e = pq.take();
        	V v = e.get(), w = e.getOther(v);
        	//if both vertices are in the spanning or neither are in the spanning, skip the loop
        	if((marked.get(v)&&marked.get(w))||(!marked.get(v)&&!marked.get(w))) continue;
        	//otherwise, add the edge and tag the connected vertices
        	result.add(e);
        	if(!marked.get(v)) visit(graph,v);
        	if(!marked.get(w)) visit(graph,w);
        }
        return result;
    }
    
    private void visit(EdgeGraph<V,X> graph, V v) {
    	marked.replace(v, true);
    	for(Edge<V,X> e:graph.adjacent(v)) {
    		if(!marked.get(e.getOther(v))) {
    			pq.give(e);
    		}
    	}
    }
    
    private Map<V, Boolean> createMark(EdgeGraph<V,X> graph) {
    	Map<V,Boolean> result = new HashMap<>();
    	for(V v:graph.vertices()) result.put(v,false);
    	return result;
    }

    private Map<V,Boolean> marked;
    private V startpoint;
    private EdgeGraph<V, X> graph;
    private final int numberEdges;
}
