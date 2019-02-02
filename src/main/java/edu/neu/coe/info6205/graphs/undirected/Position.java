package edu.neu.coe.info6205.graphs.undirected;

public class Position {
    public final Double x;
    public final Double y;

    public Position(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x+","+y+",0";
    }
}
