package edu.neu.coe.info6205.graphs.dag;

public class Edge<V, E> {

    private final V from;
    private final V to;

    public E getAttributes() {
        return attributes;
    }

    private final E attributes;

    public Edge(V from, V to, E attributes) {
        this.from = from;
        this.to = to;
        this.attributes = attributes;
    }

    public V getFrom() {
        return from;
    }

    public V getTo() {
        return to;
    }

    public Edge<V, E> reverse() {
        return new Edge<>(to, from, attributes);
    }

    @Override
    public String toString() {
        return attributes + ": " + from + "->" + to;
    }
}
