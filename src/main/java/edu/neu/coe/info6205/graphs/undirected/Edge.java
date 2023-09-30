package edu.neu.coe.info6205.graphs.undirected;

import java.util.Objects;

/**
 * An undirected Edge with attributes.
 *
 * @param <V> the vertex type
 * @param <E> the attribute type
 */
public class Edge<V, E> {

    /**
     * Edge constructor.
     *
     * @param a         a vertex.
     * @param b         the other vertex.
     * @param attribute the attribute.
     */
    public Edge(V a, V b, E attribute) {
        this.a = a;
        this.b = b;
        this.attribute = attribute;
    }

    /**
     * Get the attribute.
     *
     * @return the attribute.
     */
    public E getAttribute() {
        return attribute;
    }

    /**
     * Get a vertex.
     *
     * @return a vertex.
     */
    public V get() {
        return a;
    }

    /**
     * Get the other vertex.
     *
     * @param v the vertex at one end or the other.
     * @return the vertex at the opposite end of the edge to v.
     */
    public V getOther(V v) {
        return v.equals(a) ? b : a;
    }

    /**
     * NOTE: edge is symmetric
     *
     * @param o the other edge
     * @return true if the edges are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        return ((Objects.equals(a, edge.a) && Objects.equals(this.b, edge.b) ||
                Objects.equals(a, edge.b) &&
                        Objects.equals(this.b, edge.a))) &&
                Objects.equals(attribute, edge.attribute);
    }

    /**
     * NOTE: edge is symmetric
     *
     * @return hashcode of this edge
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b, attribute) + Objects.hash(b, a, attribute);
    }

    @Override
    public String toString() {
        return a + "-" + b + ": " + attribute;
    }

    protected final V a;
    protected final V b;
    protected final E attribute;
}