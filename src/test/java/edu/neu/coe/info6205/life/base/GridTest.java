package edu.neu.coe.info6205.life.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.neu.coe.info6205.life.library.Library.Glider1;
import static edu.neu.coe.info6205.life.library.Library.Glider2;
import static org.junit.Assert.assertEquals;

public class GridTest {

    @Test
    public void add() {
        // TESTME
    }

    @Test
    public void getCount() {
        // TESTME
    }

    @Test
    public void remove() {
        // TESTME
    }

    @Test
    public void forEach() {
        // TESTME
    }

    @Test
    public void generation() {
        // TESTME
    }

    @Test
    public void testMergeGroups() {
        Group glider1 = Group.create(0L, Glider1);
        Group glider2 = Group.create(0L, Glider2);
        List<Group> groups = new ArrayList<>();
        groups.add(glider1);
        groups.add(glider2);
        List<Group> target = Grid.mergeGroups(groups);
        assertEquals(1, target.size());
        System.out.println(target.get(0));
    }

    @Test
    public void render() {
        final Grid target = new Grid(0L);
        target.add(Group.create(0L, Glider1));
        assertEquals("O**\n" + "..*\n" + ".*.\n" + "Origin: {0, 0}\n", target.render());
    }
}