package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

		@Test
		public void testRender() {
				Matrix target = new Matrix(10, 1);
				assertEquals("__________\n" +
								"          |\n" +
								"__________\n", target.render());
				target.addCell(new Point(1, 0));
				assertEquals("__________\n" +
								" *        |\n" +
								"__________\n", target.render());
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
				int[][] counts = target.countNeighbors();
				for (int k = 0; k < 3; k++)
						for (int l = 0; l < 3; l++)
								assertEquals(0, counts[k][l]);
		}

		@Test
		public void testCountNeighbors1() {
				Matrix target = new Matrix(3, 3);
				Point point = new Point(1, 1);
				target.addCell(point);
				int[][] counts = target.countNeighbors();
				for (int k = 0; k < 3; k++)
						for (int l = 0; l < 3; l++)
								assertEquals(k == 1 && l == 1 ? 0 : 1, counts[k][l]);
		}

		@Test
		public void testNeighbors() {
		}
}