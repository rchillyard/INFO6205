package edu.neu.coe.info6205.dynamicProgramming.coins;

/**
 * @author Palak.
 * @author robinhillyard: Revised with unit tests:
 */
class CoinChanger {
    /**
     * Construct a CoinChanger with certain denomination coins available (unlimited in number).
     *
     * @param denominations the denominations available to make change.
     */
    public CoinChanger(int[] denominations) {
        this.denominations = denominations;
    }

    /**
     * Method to determine the least number of coins that can make up change of the given amount.
     *
     * @param amount the amount of change required
     * @return the minimum number of coins necessary, given the denominations available to this CoinChanger.
     */
    int minimumCoins(int amount) {
        // In DP we break problem to smaller sub-problems, here this array will store the solution of those sub-problems
        double[][] arr = new double[denominations.length + 1][amount + 1];

        for (int j = 0; j <= amount; j++) {     // Initialising first row with an input of upto positive infinity
            arr[0][j] = Double.POSITIVE_INFINITY; //We use double here because Infinity parameter is not supported in Java
        }

        // Initialising first column with 0
        for (int i = 1; i <= denominations.length; i++) {
            arr[i][0] = 0;
        }

        // Adding a recursive loop to calculate the solution for sub-problems
        for (int i = 1; i <= denominations.length; i++)
            for (int j = 1; j <= amount; j++)
                if (denominations[i - 1] <= j)
                    arr[i][j] = Math.min(1 + arr[i][j - denominations[i - 1]], arr[i - 1][j]);
                else
                    arr[i][j] = arr[i - 1][j];

        return (int) arr[denominations.length][amount];
    }

    private final int[] denominations;
}