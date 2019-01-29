package edu.neu.coe.info6205.graphs.undirected;

import java.util.Objects;

/**
 * An undirected Edge
 * @param <Vertex>
 */
public class Edge<Vertex> {

    /**
     * Edge constructor.
     * @param a a vertex.
     * @param b the other vertex.
     * @param weight the weight.
     */
    public Edge(Vertex a, Vertex b, double weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    /**
     * Get the weight.
     * @return the weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Get a vertex.
     * @return a vertex.
     */
    public Vertex get() {
        return a;
    }

    /**
     * Get the other vertex.
     * @param v the vertex at one end or the other.
     * @return the vertex at the opposite end of the edge to v.
     */
    public Vertex getOther(Vertex v) {
        return v.equals(a) ? b : a;
    }

    @Override
    public String toString() {
        return a + "-" + b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(a, edge.a) && Objects.equals(b, edge.b) || Objects.equals(a, edge.b) && Objects.equals(b, edge.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b) + Objects.hash(b, a);
    }

    private final Vertex a;
    private final Vertex b;
    private final double weight;
}
