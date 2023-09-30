package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.Sequenced;

import java.util.Objects;

public class TunnelProperties implements Sequenced, Comparable<TunnelProperties> {
    final long cost;
    final int length;
    final int phase;
    int sequence;

    @Override
    public int getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public TunnelProperties(long cost, int length, int phase, int sequence) {
        this.cost = cost;
        this.length = length;
        this.phase = phase;
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TunnelProperties that = (TunnelProperties) o;
        return cost == that.cost &&
                length == that.length &&
                sequence == that.sequence &&
                phase == that.phase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, length, phase, sequence);
    }

    @Override
    public int compareTo(TunnelProperties o) {
        return Long.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return ("sequence: " + sequence + ", phase: " + (phase == 0 ? "existing" : "new") + " tunnel of length: " + length +
                "m at cost: $" + String.format("%,d", cost));
    }
}