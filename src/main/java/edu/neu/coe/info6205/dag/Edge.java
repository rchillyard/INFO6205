package edu.neu.coe.info6205.dag;

public class Edge<Vertex> {

    private final Vertex from;
    private final Vertex to;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public Edge<Vertex> reverse() {
        return new Edge<>(to, from);
    }

    @Override
    public String toString() {
        return from + "->" + to;
    }
}
