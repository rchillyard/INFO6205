package edu.neu.coe.info6205.dynamicProgramming.coins;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinChangerTest {

    @Test
    public void testMinimumCoins0() {
        CoinChanger cc = new CoinChanger(new int[]{1, 2, 5, 7, 9});
        assertEquals(12, cc.minimumCoins(100));
    }

    @Test
    public void testMinimumCoins1() {
        CoinChanger cc = new CoinChanger(new int[]{1, 11, 13, 15});
        assertEquals(4, cc.minimumCoins(40));
    }

    @Test
    public void testMinimumCoins2() {
        CoinChanger cc = new CoinChanger(new int[]{3, 6, 9, 2, 11});
        assertEquals(8, cc.minimumCoins(82));
    }
}