package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.GeoPoint;
import edu.neu.coe.info6205.graphs.gis.Position_Spherical;
import edu.neu.coe.info6205.graphs.undirected.Position;

import java.util.Objects;

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

    public Building(String name, double lat, double lon, boolean isRailroadT, boolean isMassAveT, boolean isAlreadyTunneled, boolean isHuntAve) {
        this.name = name;
        this.position = new Position_Spherical(lat, lon);
        this.isRailroadT = isRailroadT;
        this.isMassAveT = isMassAveT;
        this.isAlreadyTunneled = isAlreadyTunneled;
        this.isHuntAve = isHuntAve;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return isRailroadT == building.isRailroadT &&
                isMassAveT == building.isMassAveT &&
                isHuntAve == building.isHuntAve &&
                isAlreadyTunneled == building.isAlreadyTunneled &&
                Objects.equals(name, building.name) &&
                Objects.equals(position, building.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, isRailroadT, isMassAveT, isHuntAve, isAlreadyTunneled);
    }

    final boolean isRailroadT; // is it trans-Railroad?
    final boolean isMassAveT; // is it trans-Mass Ave?
    final boolean isHuntAve; // is it trans-Huntington
    final boolean isAlreadyTunneled; // is it already a node on an existing tunnel
    private final String name; // Building name
    private final Position position;
}
