/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205;

public class MyDate implements Comparable<MyDate> {

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public int compareTo(MyDate that) {
        int cfy = Integer.compare(this.year, that.year);
        if (cfy!=0) return cfy;
        int cfm = Integer.compare(this.month, that.month);
        if (cfm!=0) return cfm;
        return Integer.compare(this.day, that.day);
    }

    private final int year;
    private final int month;
    private final int day;
}
