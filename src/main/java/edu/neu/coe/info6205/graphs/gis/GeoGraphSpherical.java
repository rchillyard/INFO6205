package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.Position;

/**
 * This is a GeoGraph which is in spherical coordinates (i.e. latitude, longitude) on the Earth's surface.
 *
 * @param <V> the vertex type
 * @param <E> the edge type
 */
public class GeoGraphSpherical<V extends GeoPoint, E> extends BaseGeoGraph<V, E> {
    public double length(Edge<V, E> edge) {
        V v1 = edge.get();
        V v2 = edge.getOther(v1);
        return getDistance(v1, v2);
    }

    public double getDistance(GeoPoint p1, GeoPoint p2) {
        return distance(p1.getPosition(), p2.getPosition());
    }

    /**
     * Method to calculate distance between given longitudes and latitudes.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return the distance between the points in meters.
     */
    private static double distance(Position p1, Position p2) {
        Position_Spherical v1 = (Position_Spherical) p1;
        Position_Spherical v2 = (Position_Spherical) p2;
        double latArc = Math.toRadians(v2.getLatitude() - v1.getLatitude());
        double lonArc = Math.toRadians(v2.getLongitude() - v1.getLongitude());
        double a = Math.sin(latArc / 2) * Math.sin(latArc / 2)
                + Math.cos(Math.toRadians(v1.getLatitude())) * Math.cos(Math.toRadians(v2.getLatitude()))
                * Math.sin(lonArc / 2) * Math.sin(lonArc / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    private final static int R = 6378100; // Radius of the earth (meters)
}