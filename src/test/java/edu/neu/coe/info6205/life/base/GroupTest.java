package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {

		@Test
		public void testConstructor() {
				Group target = new Group();
				assertNull(target.getOrigin());
				assertFalse(target.overlap(target));
				assertEquals(0, target.getCount());
		}

		@Test
		public void testAdd() throws LifeException {
				Group target = new Group();
				final Point point = new Point(0, 0);
				target.add(point);
				assertEquals(1, target.getCount());
				assertEquals(point, target.getOrigin());
				assertEquals(point, target.getExtent());
				assertTrue(target.overlap(target));
		}

		@Test
		public void testOverlap0() {
				Group target = new Group(null, null, null);
				Group other = new Group(null, null, null);
				assertFalse(target.overlap(other));
		}

		@Test
		public void testOverlap1() {
				final Point point00 = new Point(0, 0);
				final Point point11 = new Point(1, 1);
				final List<Point> cells1 = new ArrayList<>();
				cells1.add(point00);
				final List<Point> cells2 = new ArrayList<>();
				cells2.add(point11);
				Group target = new Group(cells1);
				Group other = new Group(cells2);
				assertTrue(target.overlap(other));
		}

		@Test(expected = LifeException.class)
		public void testMerge0() throws LifeException {
				Group target = new Group();
				target.merge(target);
		}

		@Test
		public void testMerge1() throws LifeException {
				Group target = new Group();
				final Point point = new Point(0, 0);
				target.add(point);
				assertEquals(1, target.getCount());
				assertEquals(point, target.getOrigin());
				assertEquals(point, target.getExtent());
				assertTrue(target.overlap(target));
				Group merged = target.merge(new Group());
				assertEquals(target, merged);
		}

		@Test
		public void testRemove() {
				final Point point = new Point(0, 0);
				final List<Point> cells = new ArrayList<>();
				cells.add(point);
				Group target = new Group(point, point, cells);
				assertEquals(1, target.getCount());
				target.remove(point);
				assertEquals(0, target.getCount());
		}

		@Test
		public void testForEach() {
				final Point point = new Point(0, 0);
				final List<Point> cells = new ArrayList<>();
				cells.add(point);
				Group target = new Group(point, point, cells);
				final List<Point> result = new ArrayList<>();
				target.forEach(result::add);
				assertEquals(1, result.size());
				assertEquals(point, result.get(0));
		}

		@Test
		public void testGetOrigin() {
				final Point point = new Point(0, 0);
				Group target = new Group(point, null, null);
				assertEquals(point, target.getOrigin());
		}

		@Test
		public void testGetExtent() {
				final Point point = new Point(0, 0);
				Group target = new Group(null, point, null);
				assertEquals(point, target.getExtent());
		}
}