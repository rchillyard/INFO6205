package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

		@Test
		public void testRender() {
				Matrix target = new Matrix(10, 1);
				assertEquals(" −−−−−−−−−−\n" +
								"|..........|\n" +
								" −−−−−−−−−−\n", target.render());
				target.addCell(new Point(1, 0));
				assertEquals(" −−−−−−−−−−\n" +
								"|.*........|\n" +
								" −−−−−−−−−−\n", target.render());
		}

		@Test
		public void testToString() {
				Matrix target = new Matrix(10, 1);
				assertEquals("0\n", target.toString());
				target.addCell(new Point(1, 0));
				assertEquals("40000000\n", target.toString());
		}

		@Test
		public void testIsCell() {
				Matrix target = new Matrix(3, 3);
				for (int k = 0; k < 3; k++)
						for (int l = 0; l < 3; l++)
								assertFalse(target.isCell(new Point(k, l)));
		}

		@Test
		public void testAddCell() {
				Matrix target = new Matrix(3, 3);
				Point point = new Point(1, 1);
				target.addCell(point);
				assertTrue(target.isCell(point));
		}

		@Test
		public void testCountNeighbors0() {
				Matrix target = new Matrix(3, 3);
				final Matrix.Neighbors neighbors = target.getNeighbors();
				for (int k = 0; k < 3; k++)
						for (int l = 0; l < 3; l++)
								assertEquals(0, neighbors.getCount(new Point(k, l)));
		}

		@Test
		public void testCountNeighbors1() {
				Matrix target = new Matrix(3, 3);
				Point point = new Point(1, 1);
				target.addCell(point);
				final Matrix.Neighbors neighbors = target.getNeighbors();
				for (int k = 0; k < 3; k++)
						for (int l = 0; l < 3; l++)
								assertEquals(k == 1 && l == 1 ? 0 : 1, neighbors.getCount(new Point(k, l)));
		}

		@Test
		public void testCountNeighbors2() {
				Matrix target = new Matrix(4, 4);
				target.addCell(new Point(1, 1));
				target.addCell(new Point(2, 2));
				System.out.println("matrix: \n" + target.render());
				final Matrix.Neighbors neighbors = target.getNeighbors();
				System.out.println(target.getNeighbors());
				for (int k = 0; k < 4; k++)
						for (int l = 0; l < 4; l++) {
								final int expected = (k == 3 && l == 0 || k == 0 && l == 3) ? 0 : (k == 2 && l == 1 || k == 1 && l == 2) ? 2 : 1;
								assertEquals("count for " + k + ", " + l,
												expected, neighbors.getCount(new Point(k, l)));
						}
		}

}