package edu.neu.coe.info6205;

import java.util.*;

public class Ticket {
    /**
     * When you run this program, you need to provide a value of n, the number of tickets you need.
     *
     * @param args n: how many tickets
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Random r = new Random();
        int factor = getFactor(r);
        System.out.println("Common factor: " + factor);
        Collection<Long> tickets = getTickets(n, factor, r);
        System.out.println(Arrays.deepToString(tickets.toArray()));
    }

    static int getFactor(Random r) {
        return primes[r.nextInt(primes.length)];
    }

    static List<Long> getTickets(int n, int factor, Random random) {
        Long[] products = new Long[n];
        int x = random.nextInt(primes.length - n);
        for (int i = 0; i < n; i++) products[i] = (long) factor * primes[i + x];

        List<Long> tickets = new ArrayList<>(Arrays.asList(products));
        Collections.shuffle(tickets, random);
        return tickets;
    }

    private static final int[] primes = {
            31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
            127, 131, 137, 139, 149, 151, 157, 163, 167,
            173, 179, 181, 191, 193, 197, 199, 211, 223, 227,
            229, 233, 239, 241, 251, 251, 257, 263, 269, 271, 277,
            281, 283, 293, 307, 311, 313, 317, 331, 337, 347,
            349, 353, 359, 367, 373, 379, 383, 389, 397, 401,
            409, 419, 421, 431, 433, 439, 443, 449, 457, 461,
            463, 467, 479, 487, 491, 499, 503, 509, 521, 523,
            541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
            601, 607, 613, 617, 619, 631, 641, 643, 647, 653,
            659, 661, 673, 677, 683, 691, 701, 709, 719, 727,
            733, 739, 743, 751, 757, 761, 769, 773, 787, 797,
            809, 811, 821, 823, 827, 829, 839, 853, 857, 859
    };

}
