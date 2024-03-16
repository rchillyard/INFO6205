package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Position;

class MockGeoPoint implements GeoPoint {

    private final String name;
    private final Position position;

    public MockGeoPoint(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}