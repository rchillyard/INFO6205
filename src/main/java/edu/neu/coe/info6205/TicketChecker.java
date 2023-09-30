package edu.neu.coe.info6205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
 * This is program to check whether the tickets have been used by students. It will take input all the tickets.
 * After this user need to input the ticket number used by students.
 * The program can be terminated by entering 7889- Exit code
 *
 * Input Format--
 *
 * [ticket_number_1,ticket_number_2,ticket_number_3,ticket_number_4,ticket_number_5....ticket_number_n ]
 *
 * Output--
 *
 * ========================================================================
 * Final Tally of tickets
 * Tickets                 Used Status
 * ========================================================================
 * 182051                    1
 * 167929                    2
 * 154421                    Not Used
 * 160561                    Not Used
 *
 * */

class Checker {
    public void checkValid(int[] nums) {
        Scanner input = new Scanner(System.in);
        System.out.println("Total tickets: " + nums.length);
        HashMap<Integer, Integer> ticketCounter = new HashMap<>();

        for (int num : nums) {
            ticketCounter.put(num, 0);
        }

        while (true) {
            System.out.println("Enter the ticket number: ");
            int ticket = input.nextInt();
            if (ticket == 7889) break;
            if (!ticketCounter.containsKey(ticket)) {
                System.out.println("Invalid Ticket: " + ticket);
            } else {
                int value = ticketCounter.get(ticket);
                if (value == 0) {
                    ticketCounter.put(ticket, value + 1);
                    System.out.println("Valid Ticket: " + ticket);
                } else {
                    ticketCounter.put(ticket, value + 1);
                    System.out.println("Ticket already used by another User");
                    System.out.println("Number of user: " + ticketCounter.get(ticket));
                }
            }
        }
        System.out.println("========================================================================");
        System.out.println("Final Tally of tickets");
        System.out.println("Tickets                 Used Status");
        System.out.println("========================================================================");


        for (int num : nums) {
            System.out.println(num + "                    " + (ticketCounter.get(num) == 0 ? "Not Used" : ticketCounter.get(num)));
        }

    }
}

public class TicketChecker {

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        for (int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result.append(number).append(", ");
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(Collection<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list : nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the total tickets");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        // TODO figure out what was meant here: while does not loop!
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            new Checker().checkValid(nums);
            break;
        }
    }
}
