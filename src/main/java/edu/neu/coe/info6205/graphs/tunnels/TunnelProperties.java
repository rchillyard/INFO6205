package edu.neu.coe.info6205.graphs.tunnels;

import java.util.Objects;

public class TunnelProperties implements Comparable<TunnelProperties> {
     final long cost;
     final int length;
     final int phase;

    public TunnelProperties(long cost, int length, int phase) {
        this.cost = cost;
        this.length = length;
        this.phase = phase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TunnelProperties that = (TunnelProperties) o;
        return cost == that.cost &&
                length == that.length &&
                phase == that.phase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, length, phase);
    }

    @Override
    public int compareTo(TunnelProperties o) {
        return Long.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return (phase == 0 ? "existing" : "new") + " tunnel of length: " + length +
                "m at cost: $" + String.format("%,d", cost);
    }
}
