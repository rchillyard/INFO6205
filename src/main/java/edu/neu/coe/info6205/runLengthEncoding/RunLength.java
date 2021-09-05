package edu.neu.coe.info6205.runLengthEncoding;

public class RunLength {

    public RunLength(double p_pixel_black) {
        this.p_pixel_black = p_pixel_black;
    }

    public double probabilityOfRunLength(int n, boolean black) {
        if (black) return Math.pow(p_pixel_black, n) * (1 - p_pixel_black);
        else return Math.pow(1 - p_pixel_black, n) * p_pixel_black;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args.length > 0 ? args[0] : "25");
        double p_black = Double.parseDouble(args.length > 1 ? args[1] : "0.25");
        System.out.println("RunLength with n = " + n +
                " and p(black) = " + p_black);
        System.out.println("In the following, i is the length of a run of the given color; p(i) is the probability of a run with that length");
        RunLength runLength = new RunLength(p_black);
        double total_black = 0;
        double total_white = 0;
        double expectation_black = 0;
        double expectation_white = 0;
        for (int i = 1; i < n; i++) {
            double p_i_black = runLength.probabilityOfRunLength(i, true);
            double p_i_white = runLength.probabilityOfRunLength(i, false);
            total_black += p_i_black;
            total_white += p_i_white;
            expectation_black += p_i_black * i;
            expectation_white += p_i_white * i;
            System.out.println("i = " + i + " (black), p(i) = " + p_i_black);
            System.out.println("i = " + i + " (white), p(i) = " + p_i_white);
        }
        System.out.println("total = " + total_black + " for black");
        System.out.println("total = " + total_white + " for white");
        System.out.println("expectation = " + expectation_black + " for black");
        System.out.println("expectation = " + expectation_white + " for white");
    }

    private final double p_pixel_black;
}
