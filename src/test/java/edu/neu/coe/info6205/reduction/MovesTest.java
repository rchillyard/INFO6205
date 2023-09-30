package edu.neu.coe.info6205.reduction;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MovesTest {

    @Test
    public void test2_1() {
        System.out.println("test1: 1,1->3,5");
        assertTrue(new Moves2(3, 5).valid(1, 1));
    }

    @Test
    public void test2_2() {
        System.out.println("test2: 1,1->2,2");
        assertFalse(new Moves2(2, 2).valid(1, 1));
    }

    @Test
    public void test2_3() {
        System.out.println("test3: 1,1->1,1");
        assertTrue(new Moves2(1, 1).valid(1, 1));
    }

    //    @Test
    public void test2_4() {
        System.out.println("test3: 1,1->99,100");
        assertTrue(new Moves2(99, 100).valid(1, 1));
    }

    //    @Test
    public void test2_5() {
        System.out.println("test3: 35,13->455955547,420098884");
        assertFalse(new Moves2(455955547, 420098884).valid(35, 13));
    }

    @Test
    public void test3_1() {
        System.out.println("test1: 1,1->3,5");
        assertTrue(new Moves3(1, 1).valid(3, 5));
    }

    @Test
    public void test3_2() {
        System.out.println("test2: 1,1->2,2");
        assertFalse(new Moves3(1, 1).valid(2, 2));
    }

    @Test
    public void test3_3() {
        System.out.println("test3: 1,1->1,1");
        assertTrue(new Moves3(1, 1).valid(1, 1));
    }

    @Test
    public void test3_4() {
        System.out.println("test3: 1,1->99,100");
        assertTrue(new Moves3(1, 1).valid(99, 100));
    }

    @Test
    public void test3_5() {
        System.out.println("test3: 35,13->455955547,420098884");
        assertFalse(new Moves3(35, 13).valid(455955547, 420098884));
    }
}