package edu.neu.coe.info6205.functions;

import java.util.ArrayList;
import java.util.List;

public class LambertW {

    /**
     * Maximum number of iterations allowed for Newton's approximation.
     */
    public static final int MAX_TRIES = 20;

    /**
     * See <a href="https://en.wikipedia.org/wiki/Lambert_W_function">https://en.wikipedia.org/wiki/Lambert_W_function</a>
     * See also <a href="https://math.stackexchange.com/questions/1700919/how-to-derive-the-lambert-w-function-series-expansion">https://math.stackexchange.com/questions/1700919/how-to-derive-the-lambert-w-function-series-expansion</a>
     *
     * @param j         the branch.
     * @param z         the (real) z-value.
     * @param tolerance the tolerance which controls the convergence of the approximation.
     * @return the value of Wj(z)
     * @throws LambertException if j or z is out of range.
     * @throws RuntimeException if the function doesn't converge to within the required tolerance.
     */
    public double W(int j, double z, double tolerance) throws LambertException {
        final double estimate = estimateW(j, z);
//        System.out.println("W: "+j+", z="+z+", estimate=: "+estimate);
        final Newton newton = new Newton("x exp(x) - z = 0", x -> x * Math.exp(x) - z, x -> (1 + x) * Math.exp(x));
        final Either<String, Double> solution = newton.solve(estimate, MAX_TRIES, tolerance);
        if (solution.isRight()) return solution.getRight();
        else throw new RuntimeException(solution.getLeft());
    }

    public Double[] W(double z, double tolerance) {
        List<Double> result = new ArrayList<>();
        for (int j = 0; j >= -1; j--)
            try {
                result.add(W(j, z, tolerance));
            } catch (LambertException e) {
                // XXX eat this exception;
            }
        return result.toArray(new Double[0]);
    }

    static class LambertException extends Exception {
        public LambertException(String message) {
            super(message);
        }
    }

    private double estimateW(int j, double x) throws LambertException {
        if (j == 0) {
            if (x > -1 && x < 1 / Math.E) {
                double result = 0;
                for (int i = 1; i < MAX_TRIES; i++) result += term0(x, i);
                return result;
            } else if (x >= 1 / Math.E) {
                final double logX = Math.log(x);
                return logX - ((logX > 0) ? Math.log(logX) : 0);
            } else throw new LambertException("LambertW: W(j,x): not supported for j=0, x < -1");
        } else if (j == -1) {
            if (x >= -1 / Math.E && x <= 0) {
                final double log_X = Math.log(-x);
                return log_X - ((log_X > 0) ? Math.log(log_X) : 0);
            } else throw new LambertException("LambertW: W(j,x): not supported for j=-1, x > 0 or x < -1/e");
        } else throw new LambertException("LambertW: W(j,x): not supported for j=" + j);
    }

    private static double term0(double x, int p) {
        return Math.pow(x, p) * Math.pow(-p, p - 1) / factorial(p);
    }

    private static double factorial(int n) {
        if (n < 2) return 1;
        else return n * factorial(n - 1);
    }
}