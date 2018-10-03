/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.hashCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class HashCodeSortTest {
    public class Date implements Comparable<Date> {
        private final int year;
        private final int month;
        private final int day;
        private final int hour;
        private final int min;
        private final int sec;
        private final int millis;

        public Date(int year, int month, int day, int hour, int min, int sec, int millis) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.min = min;
            this.sec = sec;
            this.millis = millis;
        }

        @Override
        public String toString() {
            return "Date{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    ", hour=" + hour +
                    ", min=" + min +
                    ", sec=" + sec +
                    ", millis=" + millis +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Date date = (Date) o;

            if (year != date.year) return false;
            if (month != date.month) return false;
            if (day != date.day) return false;
            if (hour != date.hour) return false;
            if (min != date.min) return false;
            if (sec != date.sec) return false;
            return millis == date.millis;
        }

        @Override
        public int hashCode() {
            int result = year;
            result = 12 * result + month;
            result = 31 * result + day;
            result = 24 * result + hour;
            result = 60 * result + min;
            result = 60 * result + sec;
            result = 1000 * result + millis;
            return result >> 3;
        }

        @Override
        public int compareTo(Date o) {
            int[] thisArray = new int[]{year, month, day, hour, min, sec, millis};
            int[] thatArray = new int[]{o.year, o.month, o.day, o.hour, o.min, o.sec, o.millis};
            for (int i = 0; i < thisArray.length; i++) {
                int cf = Integer.compare(thisArray[i], thatArray[i]);
                if (cf != 0) return cf;
            }
            return 0;
        }
    }

    @Test
    public void sort() throws Exception {
        final Date d1 = new Date(2017, 12, 10, 11, 30, 5, 723);
        final Date d2 = new Date(2017, 12, 10, 11, 38, 17, 42);
        final Date d3 = new Date(2017, 12, 10, 11, 38, 17, 47);
        assertTrue(d1.hashCode() < d2.hashCode());
        assertTrue(d3.hashCode() == d2.hashCode());
        assertTrue(d1.compareTo(d2) < 0);
        assertTrue(d2.compareTo(d3) < 0);
        assertTrue(d1.compareTo(d3) < 0);

        final HashCodeSort sorter = new HashCodeSort();
        final List<Date> dates = new ArrayList<>();
        dates.add(d3);
        dates.add(d2);
        dates.add(d1);
        List<Date> sorted = sorter.sort(dates);
        assertEquals(d1, sorted.get(0));
        assertEquals(d2, sorted.get(1));
        assertEquals(d3, sorted.get(2));
    }

}