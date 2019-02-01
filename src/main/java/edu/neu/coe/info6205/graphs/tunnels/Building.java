package edu.neu.coe.info6205.graphs.tunnels;

// create class vertex to contain all the attributes of a vertex
public class Building {

    String name; // Building name
    double lat; // latitude
    double lon; // longitude
    boolean isRuggleT; // is it trans-Ruggle?
    boolean isMassAveT; // is it trans-Mass Ave?
    boolean isHuntAve; // is it trans-Huntington
    boolean isAlreadyTunneled; // is it already a node on an existing tunnel

    public Building(String name, double lon, double lat, boolean isRuggleT, boolean isMassAveT, boolean isAlreadyTunneled, boolean isHuntAve) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
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
