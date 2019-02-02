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

    public Building(int map, String code, String zone, double lat, double lon, boolean isRailroadT, boolean isMassAveT, boolean isHuntAveT, boolean isAlreadyTunneled, String name) {
        this.map = map;
        this.code = code;
        this.zone = zone;
        this.name = name;
        this.position = new Position_Spherical(lat, lon);
        this.isRailroadT = isRailroadT;
        this.isMassAveT = isMassAveT;
        this.isAlreadyTunneled = isAlreadyTunneled;
        this.isHuntAveT = isHuntAveT;
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
                isHuntAveT == building.isHuntAveT &&
                isAlreadyTunneled == building.isAlreadyTunneled &&
                Objects.equals(name, building.name) &&
                Objects.equals(position, building.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, isRailroadT, isMassAveT, isHuntAveT, isAlreadyTunneled);
    }

    final boolean isRailroadT; // is it trans-Railroad?
    final boolean isMassAveT; // is it trans-Mass Ave?
    final boolean isHuntAveT; // is it trans-Huntington
    final boolean isAlreadyTunneled; // is it already a node on an existing tunnel
    final String zone;
    private final int map;
    private final String code;
    private final String name; // Building name
    private final Position position;
}
