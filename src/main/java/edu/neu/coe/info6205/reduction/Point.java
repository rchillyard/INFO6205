package edu.neu.coe.info6205.reduction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point implements Comparable<Point> {

		public int getX() {
				return x;
		}

		public int getY() {
				return y;
		}

		final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

		public Point move(int x, int y) {
				return new Point(this.x + x, this.y + y);
		}

		public Point move(Point p) {
				return move(p.x, p.y);
		}

		public Point relative(Point point) {
				return move(-point.getX(), -point.getY());
		}


		public boolean valid() {
        return x > 0 && y > 0;
    }

		/**
		 * Method to compare two points
		 *
		 * @param p the other point.
		 * @return as follows:
		 * 0: points are the same; otherwise:
		 * 1: p is due East of this;
		 * 3: p is due North of this;
		 * -1: p is due West of this;
		 * -3: p is due South of this;
		 * 4: p is NE of this;
		 * 2: p is NW of this;
		 * -4: p is SW of this;
		 * -2: p is SE of this;
		 */
		public int compare(Point p) {
				int cfX = Integer.compare(p.getX(), getX());
				int cfY = Integer.compare(p.getY(), getY());
				return cfX + 3 * cfY;
		}

		@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
								'}';
		}

		public static List<Point> points(String s) {
				List<Point> result = new ArrayList<>();
				for (String w : s.split(", *")) result.add(point(w));
				return result;
		}

		public static Point point(String s) {
				String[] ws = s.split(" ");
				int x = Integer.parseInt(ws[0]);
				int y = Integer.parseInt(ws[1]);
				return new Point(x, y);
		}

		/**
		 * Compares this Point with other.
		 * The basis of comparison is the distance to the origin.
		 *
		 * @param other the Point to be compared.
		 * @return a negative integer, zero, or a positive integer as this object
		 * is less than, equal to, or greater than the specified object.
		 * @throws NullPointerException if the specified object is null
		 * @throws ClassCastException   if the specified object's type prevents it
		 *                              from being compared to this object.
		 */
		@Override
		public int compareTo(Point other) {
				return Double.compare(this.distance(), other.distance());
		}

		/**
		 * Calculate the distance this point is from the origin.
		 *
		 * @return the square root of x^2 + y^2.
		 */
		private double distance() {
				return Math.sqrt(x * x + y * y);
		}
}

