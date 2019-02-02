package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.undirected.Position;
import edu.neu.coe.info6205.graphs.undirected.GeoPoint;

// create class vertex to contain all the attributes of a vertex
public class Building implements GeoPoint {

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    final String name; // Building name
    final Position position;
    final boolean isRuggleT; // is it trans-Ruggle?
    final boolean isMassAveT; // is it trans-Mass Ave?
    final boolean isHuntAve; // is it trans-Huntington
    final boolean isAlreadyTunneled; // is it already a node on an existing tunnel

    public Building(String name, double lon, double lat, boolean isRuggleT, boolean isMassAveT, boolean isAlreadyTunneled, boolean isHuntAve) {
        this.name = name;
        this.position = new Position(lat, lon);
        this.isRuggleT = isRuggleT;
        this.isMassAveT = isMassAveT;
        this.isAlreadyTunneled = isAlreadyTunneled;
        this.isHuntAve = isHuntAve;
    }

    @Override
    public String toString() {
        return name;
    }
}
