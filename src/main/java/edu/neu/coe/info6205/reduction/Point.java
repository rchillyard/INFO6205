package edu.neu.coe.info6205.reduction;

import java.util.Objects;

public class Point {

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

		public boolean valid() {
        return x > 0 && y > 0;
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
}

