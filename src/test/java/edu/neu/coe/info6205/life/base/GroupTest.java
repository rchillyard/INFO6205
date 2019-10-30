package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {

		final static Point Origin = new Point(0, 0);

		@Test
		public void testConstructor() {
				Group target = new Group(0L);
				assertNull(target.getExtent1());
				assertFalse(target.overlap(target));
				assertEquals(0, target.getCount());
		}

		@Test
		public void testAdd() throws LifeException {
				Group target = new Group(0L);
				final Point point = Origin;
				assertTrue(target.add(point));
				assertEquals(1, target.getCount());
				assertEquals(point, target.getExtent1());
				assertEquals(point, target.getExtent2());
				assertTrue(target.overlap(target));
		}

		@Test
		public void testAdd0() {
				final int x = 0;
				final int y = 1;
				final Point point = new Point(x, y);
				Group target = new Group(0L);
				assertTrue(target.add(x, y));
				assertEquals(1, target.getCount());
				assertEquals(point, target.getExtent1());
				assertEquals(point, target.getExtent2());
		}

		@Test
		public void testAdd1() {
				final int x = 1;
				final int y = 3;
				String s = x + " " + y;
				final Point point = new Point(x, y);
				Group target = new Group(0L);
				assertTrue(target.add(s));
				assertEquals(1, target.getCount());
				assertEquals(point, target.getExtent1());
				assertEquals(point, target.getExtent2());
		}

		@Test
		public void testAdd2() {
				final String s = "1 2, 2 3";
				List<Point> points = Point.points(s);
				Group target = new Group(0L);
				assertTrue(target.add(points));
				assertEquals(2, target.getCount());
				assertEquals(points.get(0), target.getExtent1());
				assertEquals(points.get(1), target.getExtent2());
		}

		@Test
		public void testOverlap0() {
				Group target = new Group(0L, Origin, null, null, null);
				Group other = new Group(0L, Origin, null, null, null);
				assertFalse(target.overlap(other));
		}

		@Test
		public void testOverlap1() {
				final Point point11 = new Point(1, 1);
				final List<Point> cells1 = new ArrayList<>();
				cells1.add(Origin);
				final List<Point> cells2 = new ArrayList<>();
				cells2.add(point11);
				Group target = new Group(0L, Origin, cells1);
				Group other = new Group(0L, Origin, cells2);
				assertTrue(target.overlap(other));
		}

		@Test
		public void testOverlap2() {
				final Point point11 = new Point(1, 1);
				final Point point33 = new Point(3, 3);
				final Point point44 = new Point(4, 4);
				final List<Point> cells1 = new ArrayList<>();
				cells1.add(Origin);
				cells1.add(point11);
				final List<Point> cells2 = new ArrayList<>();
				cells2.add(point33);
				cells2.add(point44);
				Group target = new Group(0L, Origin, cells1);
				Group other = new Group(0L, Origin, cells2);
				assertFalse(target.overlap(other));
		}

		@Test
		public void testOverlap3() {
				final Point point22 = new Point(2, 2);
				final Point point33 = new Point(3, 3);
				final Point point44 = new Point(4, 4);
				final List<Point> cells1 = new ArrayList<>();
				cells1.add(Origin);
				cells1.add(point22);
				final List<Point> cells2 = new ArrayList<>();
				cells2.add(point33);
				cells2.add(point44);
				Group target = new Group(0L, Origin, cells1);
				Group other = new Group(0L, Origin, cells2);
				assertTrue(target.overlap(other));
		}

		@Test(expected = LifeException.class)
		public void testMerge0() throws LifeException {
				Group target = new Group(0L);
				target.merge(target);
		}

		@Test
		public void testMerge1() throws LifeException {
				Group target = new Group(0L);
				final Point point = Origin;
				assertTrue(target.add(point));
				assertEquals(1, target.getCount());
				assertEquals(point, target.getExtent1());
				assertEquals(point, target.getExtent2());
				assertTrue(target.overlap(target));
				Group merged = target.merge(new Group(0L));
				assertEquals(target, merged);
		}

		@Test
		public void testRemove() {
				final List<Point> cells = new ArrayList<>();
				cells.add(Origin);
				Group target = new Group(0L, Origin, Origin, Origin, cells);
				assertEquals(1, target.getCount());
				assertTrue(target.remove(Origin));
				assertEquals(0, target.getCount());
		}

		@Test
		public void testForEach() {
				final List<Point> cells = new ArrayList<>();
				cells.add(Origin);
				Group target = new Group(0L, Origin, Origin, Origin, cells);
				final List<Point> result = new ArrayList<>();
				target.forEach(result::add);
				assertEquals(1, result.size());
				assertEquals(Origin, result.get(0));
		}

		@Test
		public void testGetOrigin() {
				Group target = new Group(0L, Origin, Origin, null, null);
				assertEquals(Origin, target.getExtent1());
		}

		@Test
		public void testGetExtent() {
				Group target = new Group(0L, Origin, null, Origin, null);
				assertEquals(Origin, target.getExtent2());
		}

		@Test
		public void testWithinExtents() {
				final Point point11 = new Point(1, 1);
				final Point point01 = new Point(0, 1);
				final Point point02 = new Point(0, 2);
				final Point point03 = new Point(0, 3);
				Group target = new Group(0L);
				assertTrue(target.add(Origin));
				assertTrue(target.add(point11));
				assertTrue(target.withinExtents(Origin));
				assertTrue(target.withinExtents(point11));
				assertTrue(target.withinExtents(point01));
				assertTrue(target.withinExtents(point02));
				assertFalse(target.withinExtents(point03));
		}

		@Test
		public void testNewGeneration0() throws LifeException {
				final List<Point> cells1 = new ArrayList<>();
				cells1.add(Origin);
				cells1.add(new Point(1, 1));
				cells1.add(new Point(2, 0));
				cells1.add(new Point(2, 2));
				cells1.add(new Point(3, 1));
				cells1.add(new Point(4, 1));
				cells1.add(new Point(4, 2));
				cells1.add(new Point(5, 0));
				cells1.add(new Point(5, 2));
				Group target = new Group(0L, Origin, cells1);
				final Group newGeneration = target.newGeneration(1L);
				System.out.println(newGeneration);
				final int count = newGeneration.getCount();
				assertEquals(8, count);
		}

		@SuppressWarnings("unchecked")
		@Test
		public void testBlock() throws LifeException {
				Group target = new Group(0L);
				final PrivateMethodTester targetTester = new PrivateMethodTester(target);
				target.add("1 1, 1 2, 2 2, 2 1");
				System.out.println(target);
				assertEquals(new Point(1, 1), target.getExtent1());
				assertEquals(new Point(2, 2), target.getExtent2());
				final Group newGeneration = target.newGeneration(1L);
				final PrivateMethodTester ngTester = new PrivateMethodTester(newGeneration);
				assertEquals(target.getExtent1().move(-1, -1), newGeneration.getOrigin());
				System.out.println(newGeneration);
				final int count = newGeneration.getCount();
				assertEquals(4, count);
				final List<Point> cellsTarget = (List<Point>) targetTester.invokePrivate("getCells");
				final List<Point> cellsNG = (List<Point>) ngTester.invokePrivate("getCells");
				for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i), cellsNG.get(i));
				final Group gen2 = newGeneration.newGeneration(1L);
				final PrivateMethodTester gen2Tester = new PrivateMethodTester(gen2);
				assertEquals(newGeneration.getExtent1().move(-1, -1), gen2.getOrigin());
				System.out.println(gen2);
				assertEquals(4, gen2.getCount());
				final List<Point> cellsGen2 = (List<Point>) gen2Tester.invokePrivate("getCells");
				for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i).relative(gen2.getOrigin()), cellsGen2.get(i));
		}
}