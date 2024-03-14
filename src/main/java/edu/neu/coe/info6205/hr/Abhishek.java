package edu.neu.coe.info6205.hr;

public class Abhishek {
    public static void minimumBribes(int[] queue) {
        boolean chaotic = false;
        int bribes = 0;
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            for (int j = 0; j < i; j++) {
                if (queue[j] > queue[i]) {
                    bribes++;
                }
            }
        }
        System.out.println(bribes);
    }
    public static void main(String[] args) {
        int[] queue = {2, 1, 5, 3, 4};
        minimumBribes(queue);
    }
}