package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static edu.neu.coe.info6205.life.base.Grid.Origin;
import static edu.neu.coe.info6205.life.library.Library.*;
import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void testConstructor() {
        Group target = new Group(0L);
        assertNull(target.getExtent1());
        assertFalse(target.overlap(target));
        assertEquals(0, target.getCount());
    }

    @Test
    public void testConstructor1() {
        final List<Point> points = new ArrayList<>();
        final Point point = new Point(1, 1);
        points.add(point);
        Group target = new Group(0L, Origin, points);
        assertEquals(1, target.getCount());
        assertEquals(point.move(-1, -1), target.getExtent1());
        assertEquals(point.move(1, 1), target.getExtent2());
        assertEquals(point, target.pointsAbsolute().get(0));
    }

    @Test
    public void testConstructor2() throws LifeException {
        final List<Point> points = new ArrayList<>();
        points.add(Origin);
        points.add(new Point(1, 1));
        points.add(new Point(2, 0));
        points.add(new Point(2, 2));
        points.add(new Point(3, 1));
        points.add(new Point(4, 1));
        points.add(new Point(4, 2));
        points.add(new Point(5, 0));
        final Point last = new Point(5, 2);
        points.add(last);
        Group target = new Group(0L, Origin, points);
        assertEquals(0L, target.getGeneration());
        assertEquals(9, target.getCount());
        assertEquals(Origin, target.getOrigin());
        assertEquals(Origin.move(-1, -1), target.getExtent1());
        assertEquals(last.move(1, 1), target.getExtent2());
    }

    @Test
    public void testCellsAndNeighbors0() {
        Group target = Group.create(0L, Origin);
        final Group.CellsAndNeighbors can = Group.CellsAndNeighbors.create(target);
        assertEquals("O\n", can.toString());
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        final Point vector = new Point(1, 1);
        final Group changed = (Group) targetTester.invokePrivate("changeOrigin", 1L, vector);
        assertEquals("*\n", Group.CellsAndNeighbors.create(changed).toString());
    }

    @Test
    public void testCellsAndNeighbors1() {
        Group target = Group.create(0L, Origin);
        target.add(new Point(-1, -1));
        final Group.CellsAndNeighbors can = Group.CellsAndNeighbors.create(target);
        assertEquals(".O\n" + "*.\n", can.toString());
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        final Point vector = new Point(1, 1);
        final Group changed = (Group) targetTester.invokePrivate("changeOrigin", 1L, vector);
        assertEquals(".*\n" + "*.\n", Group.CellsAndNeighbors.create(changed).toString());
    }

    @Test
    public void testToString() {
        final List<Point> points = new ArrayList<>();
        final Point point = new Point(1, 1);
        points.add(Origin);
        points.add(point);
        Group target = new Group(0L, Origin, points);
        assertEquals("generation 0, origin = {0, 0}, extents = [{-1, -1}, {2, 2}]\n" +
                "    [{0, 0}, {1, 1}]", target.toString());
    }

    @Test
    public void testToStringInGrid() {
        final List<Point> points = new ArrayList<>();
        final Point point = new Point(1, 1);
        points.add(point);
        Group target = new Group(0L, Origin, points);
        assertEquals("generation 0: extents = [{0, 0}, {2, 2}]\n" +
                "    [{1, 1}]", target.toStringInGrid());
    }

    @Test
    public void testRender() {
        final List<Point> points = new ArrayList<>();
        final Point point = new Point(1, 1);
        points.add(point);
        Group target = new Group(0L, Origin, points);
        assertEquals("O\nOrigin: {1, 1}\n", target.render());
    }

    @Test
    public void testChangeOrigin() {
        Group target = Group.create(0L, Origin);
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        final Point point1 = new Point(1, 1);
        target.add(point1);
        final String targetRendered = target.render();
        assertEquals(".*\n" + "O.\nOrigin: {0, 0}\n", targetRendered);
        final Group changed = (Group) targetTester.invokePrivate("changeOrigin", 1L, point1);
        assertEquals(point1, changed.getOrigin());
        assertEquals(target.getExtent1(), changed.getExtent1());
        assertEquals(target.getExtent2(), changed.getExtent2());
        assertEquals(target.pointsAbsolute(), changed.pointsAbsolute());
        assertEquals(".O\n" + "*.\nOrigin: {1, 1}\n", changed.render());
        assertEquals("generation 1, origin = {1, 1}, extents = [{-2, -2}, {1, 1}]\n" +
                "    [{-1, -1}, {0, 0}]", changed.toString());
    }

    @Test
    public void testAdd() throws LifeException {
        Group target = new Group(0L);
        final Point point = Origin;
        assertTrue(target.add(point));
        assertEquals(1, target.getCount());
        assertEquals(point.move(-1, -1), target.getExtent1());
        assertEquals(point.move(1, 1), target.getExtent2());
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
        assertEquals(point.move(-1, -1), target.getExtent1());
        assertEquals(point.move(1, 1), target.getExtent2());
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
        assertEquals(point.move(-1, -1), target.getExtent1());
        assertEquals(point.move(1, 1), target.getExtent2());
    }

    @Test
    public void testAdd2() {
        final String s = "1 2, 2 3";
        List<Point> points = Point.points(s);
        Group target = new Group(0L);
        assertTrue(target.add(points));
        assertEquals(2, target.getCount());
        assertEquals(points.get(0).move(-1, -1), target.getExtent1());
        assertEquals(points.get(1).move(1, 1), target.getExtent2());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetAbsolute0() throws LifeException {
        Group target = new Group(0L);
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        final Point point0 = new Point(1, 1);
        final Point point1 = new Point(2, 2);
        target.add(point0);
        target.add(point1);
        assertEquals(point0.move(-1, -1), target.getExtent1());
        assertEquals(point1.move(1, 1), target.getExtent2());
        final List<Point> cellsTarget = (List<Point>) targetTester.invokePrivate("getPoints");
        assertEquals(point0.move(-1, -1), cellsTarget.get(0));
        assertEquals(point1.move(-1, -1), cellsTarget.get(1));
    }

    @Test
    public void testGetAbsolute1() throws LifeException {
        final Point origin = new Point(10, 10);
        final Point point0 = new Point(1, 1);
        final Point point1 = new Point(2, 2);
        final List<Point> points = new ArrayList<>();
        points.add(point0);
        points.add(point1);
        Group target = new Group(0L, origin, points);
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        assertEquals(point0.move(origin).move(-1, -1), target.getExtent1());
        assertEquals(point1.move(origin).move(1, 1), target.getExtent2());
        final List<Point> cellsTarget = target.pointsAbsolute();
        assertEquals(point0.move(origin), cellsTarget.get(0));
        assertEquals(point1.move(origin), cellsTarget.get(1));
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
        final List<Point> points1 = new ArrayList<>();
        points1.add(Origin);
        final List<Point> points2 = new ArrayList<>();
        points2.add(point11);
        Group target = new Group(0L, Origin, points1);
        Group other = new Group(0L, Origin, points2);
        assertTrue(target.overlap(other));
    }

    @Test
    public void testOverlap2() {
        final Point point11 = new Point(-1, -1);
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

    @Test
    public void testOverlap4() {
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
        Group other = new Group(0L, new Point(10, 10), cells2);
        assertFalse(target.overlap(other));
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
        assertEquals(point.move(-1, -1), target.getExtent1());
        assertEquals(point.move(1, 1), target.getExtent2());
        assertTrue(target.overlap(target));
        Group merged = target.merge(new Group(0L));
        assertEquals(target, merged);
    }

    @Test
    public void testMerge2() throws LifeException {
        final String s = "1 2, 2 3";
        Collection<Point> points = Point.points(s);
        Group target1 = new Group(0L);
        assertTrue(target1.add(points));
        final Point offset = new Point(10, 10);
        Group target2 = target1.move(offset);
        assertFalse(target1.overlap(target2));
        Group merged = target1.merge(target2);
        assertEquals(4, merged.getCount());
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
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        assertTrue(target.add(Origin));
        assertTrue(target.add(point11));
        assertTrue((Boolean) targetTester.invokePrivate("withinExtents", Origin));
        assertTrue((Boolean) targetTester.invokePrivate("withinExtents", point01));
        assertTrue((Boolean) targetTester.invokePrivate("withinExtents", point02));
        assertFalse((Boolean) targetTester.invokePrivate("withinExtents", point03));
        assertTrue((Boolean) targetTester.invokePrivate("withinExtents", point11));
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
        assertEquals(9, target.getCount());
        assertEquals("..*.**\n" + ".*.**.\n" + "O.*..*\nOrigin: {0, 0}\n", target.render());
        final Group newGeneration = target.newGeneration(1L);
        assertEquals(".*.**\n" + "O....\n" + "****.\nOrigin: {1, 1}\n", newGeneration.render());
        assertEquals(8, newGeneration.getCount());
    }

    @Test
    public void testNewGeneration1() throws LifeException {
        Group target = new Group(0L);
        target.add(Origin);
        target.add(new Point(-1, -1));
        final Group newGeneration = target.newGeneration(1L);
        assertEquals("Origin: {0, 0}\n", newGeneration.render());
        assertEquals(0, newGeneration.getCount());
    }

    @Test
    public void transpose() {
        Group target = new Group(0L);
        target.add(Origin);
        target.add(new Point(0, 1));
        final Collection<Point> points = target.pointsAbsolute();
        final Group transposed = target.transpose();
        final List<Point> pointsT = transposed.pointsAbsolute();
        assertEquals(Origin, pointsT.get(0));
        assertEquals(new Point(1, 0), pointsT.get(1));
        final Group transposedAgain = transposed.transpose();
        assertEquals(target, transposedAgain);
    }

    @Test
    public void testBlock() throws LifeException {
        Group target = Group.create(0L, Block);
        assertEquals(new Point(0, 0), target.getExtent1());
        assertEquals(new Point(3, 3), target.getExtent2());
        final Group newGeneration = target.newGeneration(1L);
        final Point p11 = new Point(1, 1);
        assertEquals(p11, newGeneration.getOrigin());
        final int count = newGeneration.getCount();
        assertEquals(4, count);
        final List<Point> cellsTarget = target.pointsAbsolute();
        final List<Point> cellsNG = newGeneration.pointsAbsolute();
        for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i), cellsNG.get(i));
        final Group gen2 = newGeneration.newGeneration(1L);
        assertEquals(p11, gen2.getOrigin());
        assertEquals(4, gen2.getCount());
        final List<Point> cellsGen2 = gen2.pointsAbsolute();
        for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i), cellsGen2.get(i));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBeehive() throws LifeException {
        Group target = Group.create(0L, Beehive);
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        assertEquals(".**.\n" + "O..*\n" + ".**.\nOrigin: {1, 2}\n", target.render());
        assertEquals(6, target.getCount());
        assertEquals(Origin, target.getExtent1());
        assertEquals(new Point(5, 4), target.getExtent2());
        final Group newGeneration = target.newGeneration(1L);
        final PrivateMethodTester ngTester = new PrivateMethodTester(newGeneration);
        final Point point12 = new Point(1, 2);
        assertEquals(point12, newGeneration.getOrigin());
        final int count = newGeneration.getCount();
        assertEquals(6, count);
        final List<Point> cellsTarget = (List<Point>) targetTester.invokePrivate("getPoints");
        final List<Point> cellsNG = (List<Point>) ngTester.invokePrivate("getPoints");
        for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i), cellsNG.get(i));
        final Group gen2 = newGeneration.newGeneration(1L);
        final PrivateMethodTester gen2Tester = new PrivateMethodTester(gen2);
        assertEquals(point12, gen2.getOrigin());
        assertEquals(".**.\n" + "O..*\n" + ".**.\nOrigin: {1, 2}\n", newGeneration.render());
        assertEquals(6, gen2.getCount());
        final List<Point> cellsGen2 = (List<Point>) gen2Tester.invokePrivate("getPoints");
        for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i), cellsGen2.get(i));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLoaf() throws LifeException {
        Group target = Group.create(0L, Loaf);
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        assertEquals(".**.\n" + "O..*\n" + ".*.*\n" + "..*.\nOrigin: {1, 3}\n", target.render());
        assertEquals(Origin, target.getExtent1());
        assertEquals(new Point(5, 5), target.getExtent2());
        final Group newGeneration = target.newGeneration(1L);
        final PrivateMethodTester ngTester = new PrivateMethodTester(newGeneration);
        final Point p13 = new Point(1, 3);
        assertEquals(p13, newGeneration.getOrigin());
        final int count = newGeneration.getCount();
        assertEquals(7, count);
        final List<Point> cellsTarget = (List<Point>) targetTester.invokePrivate("getPoints");
        final List<Point> cellsNG = (List<Point>) ngTester.invokePrivate("getPoints");
        for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i), cellsNG.get(i));
        final Group gen2 = newGeneration.newGeneration(1L);
        final PrivateMethodTester gen2Tester = new PrivateMethodTester(gen2);
        assertEquals(p13, gen2.getOrigin());
        assertEquals(".**.\n" + "O..*\n" + ".*.*\n" + "..*.\nOrigin: {1, 3}\n", newGeneration.render());
        assertEquals(7, gen2.getCount());
        final List<Point> cellsGen2 = (List<Point>) gen2Tester.invokePrivate("getPoints");
        for (int i = 0; i < count; i++) assertEquals(cellsTarget.get(i), cellsGen2.get(i));
    }

    @Test
    public void testBlinker() throws LifeException {
        Group target = Group.create(0L, Blinker);
        assertEquals("*\n" + "O\n" + "*\nOrigin: {0, 0}\n", target.render());
        assertEquals(new Point(-1, -2), target.getExtent1());
        assertEquals(new Point(1, 2), target.getExtent2());
        final Group newGeneration = target.newGeneration(1L);
        final Point p01 = new Point(0, 1);
        assertEquals(Origin, newGeneration.getOrigin());
        assertEquals("*O*\nOrigin: {0, 0}\n", newGeneration.render());
        final int count = newGeneration.getCount();
        assertEquals(3, count);
        final List<Point> cellsNG = newGeneration.pointsAbsolute();
        for (int i = 0; i < count; i++) assertEquals(0, cellsNG.get(i).getY());
        for (int i = 0; i < count; i++) assertTrue(Math.abs(cellsNG.get(i).getX()) <= 1);
        final Group gen2 = newGeneration.newGeneration(2L);
        assertEquals("*\n" + "O\n" + "*\nOrigin: {0, 0}\n", gen2.render());
        assertEquals(3, gen2.getCount());
        final List<Point> cellsGen2 = gen2.pointsAbsolute();
        for (int i = 0; i < count; i++) assertEquals(0, cellsGen2.get(i).getX());
        for (int i = 0; i < count; i++) assertTrue(Math.abs(cellsNG.get(i).getY()) <= 1);
    }

    @Test
    public void testGlider1() throws LifeException {
        Group glider0 = Group.create(0L, Glider1);
        System.out.println(glider0.toStringInGrid());
        final String renderGlider0 = glider0.render();
        System.out.println(renderGlider0);
        assertEquals(5, glider0.getCount());
        assertEquals("O**\n" + "..*\n" + ".*.\nOrigin: {0, 0}\n", renderGlider0);
        assertEquals(new Point(-1, -3), glider0.getExtent1());
        assertEquals(new Point(3, 1), glider0.getExtent2());
        Group expected = glider0.move(-1, 1);
        final Group glider1 = glider0.newGeneration(1L);
        System.out.println(glider1.toStringInGrid());
        final Point p10 = new Point(1, 0);
        assertEquals(p10, glider1.getOrigin());
        assertEquals(".*.\n" + ".O*\n" + "*.*\nOrigin: {1, 0}\n", glider1.render());
        assertEquals(5, glider1.getCount());
        final Group glider2 = glider1.newGeneration(2L);
        System.out.println(glider2.toStringInGrid());
        assertEquals(".**\n" + "*.O\n" + "..*\nOrigin: {1, 0}\n", glider2.render());
        assertEquals(5, glider2.getCount());
        final Group glider3 = glider2.newGeneration(3L);
        System.out.println(glider3.toStringInGrid());
        assertEquals("**.\n" + ".O*\n" + "*..\nOrigin: {1, 0}\n", glider3.render());
        assertEquals(5, glider3.getCount());
        final Group glider4 = glider3.newGeneration(4L);
        System.out.println(glider4.toStringInGrid());
        final String glider4Render = glider4.render();
        System.out.println(glider4Render);
        assertEquals("O**\n" + "..*\n" + ".*.\n" + "Origin: {-1, 1}\n", glider4Render);
        assertEquals(5, glider4.getCount());
        final List<Point> expectedPoints = expected.pointsAbsolute();
        Collections.sort(expectedPoints);
        final List<Point> glider4Points = glider4.pointsAbsolute();
        Collections.sort(glider4Points);
        assertEquals(expectedPoints, glider4Points);
    }

    @Test
    public void testGlider2() throws LifeException {
        Group glider0 = Group.create(0L, Glider2);
        System.out.println(glider0.toStringInGrid());
        final String renderGlider0 = glider0.render();
        System.out.println(renderGlider0);
        assertEquals(5, glider0.getCount());
        assertEquals("O**\n" + "*..\n" + ".*.\n" + "Origin: {0, 0}\n", renderGlider0);
        assertEquals(new Point(-1, -3), glider0.getExtent1());
        assertEquals(new Point(3, 1), glider0.getExtent2());
        Group expected = glider0.move(-1, 1);
        final Group glider1 = glider0.newGeneration(1L);
        System.out.println(glider1.toStringInGrid());
        final Point p10 = new Point(1, 0);
        assertEquals(Origin, glider1.getOrigin());
        assertEquals(".*.\n" + "O*.\n" + "*.*\n" + "Origin: {0, 0}\n", glider1.render());
        assertEquals(5, glider1.getCount());
        final Group glider2 = glider1.newGeneration(2L);
        System.out.println(glider2.toStringInGrid());
        assertEquals("**.\n" + "O.*\n" + "*..\n" + "Origin: {0, 0}\n", glider2.render());
        assertEquals(5, glider2.getCount());
        final Group glider3 = glider2.newGeneration(3L);
        System.out.println(glider3.toStringInGrid());
        assertEquals(".**\n" + "*O.\n" + "..*\n" + "Origin: {0, 0}\n", glider3.render());
        assertEquals(5, glider3.getCount());
        final Group glider4 = glider3.newGeneration(4L);
        System.out.println(glider4.toStringInGrid());
        final String glider4Render = glider4.render();
        System.out.println(glider4Render);
        assertEquals("**O\n" + "*..\n" + ".*.\n" + "Origin: {1, 1}\n", glider4Render);
        assertEquals(5, glider4.getCount());
        final List<Point> expectedPoints = expected.pointsAbsolute();
        Collections.sort(expectedPoints);
        final List<Point> glider4Points = glider4.pointsAbsolute();
        Collections.sort(glider4Points);
        assertEquals(expectedPoints, glider4Points);
    }

    @Test
    public void testGlider3() throws LifeException {
        Group glider0 = Group.create(0L, Glider3);
//				System.out.println(glider0.toStringInGrid());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(glider0);
        final String renderGlider0 = (String) privateMethodTester.invokePrivate("doRender", false);
//				final String renderGlider0 = glider0.render();
        assertEquals(5, glider0.getCount());
        assertEquals(".*.\n" + "..*\n" + "O**\n", renderGlider0);
        assertEquals(new Point(-1, -1), glider0.getExtent1());
        assertEquals(new Point(3, 3), glider0.getExtent2());
        Group expected = glider0.move(-1, -1);
        final Group glider1 = glider0.newGeneration(1L);
//				System.out.println(glider1.toStringInGrid());
        final Point p10 = new Point(1, 0);
        assertEquals(p10, glider1.getOrigin());
        assertEquals("*.*\n" + ".O*\n" + ".*.\nOrigin: {1, 0}\n", glider1.render());
        assertEquals(5, glider1.getCount());
        final Group glider2 = glider1.newGeneration(2L);
//				System.out.println(glider2.toStringInGrid());
        assertEquals("..*\n" + "*.O\n" + ".**\nOrigin: {1, 0}\n", glider2.render());
        assertEquals(5, glider2.getCount());
        final Group glider3 = glider2.newGeneration(3L);
//				System.out.println(glider3.toStringInGrid());
        assertEquals("*..\n" + ".O*\n" + "**.\nOrigin: {1, 0}\n", glider3.render());
        assertEquals(5, glider3.getCount());
        final Group glider4 = glider3.newGeneration(4L);
//				System.out.println(glider4.toStringInGrid());
        assertEquals(renderGlider0 + "Origin: {-1, -1}\n", glider4.render());
        assertEquals(5, glider4.getCount());
        final List<Point> expectedPoints = expected.pointsAbsolute();
        Collections.sort(expectedPoints);
        final List<Point> glider4Points = glider4.pointsAbsolute();
        Collections.sort(glider4Points);
        assertEquals(expectedPoints, glider4Points);
    }
}