import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    class LinkedNode {
        int value;
        LinkedNode next;

        LinkedNode(int value) {
            this.value = value;
        }
    }

    // Implements this method
    LinkedNode reverse(LinkedNode node) {

        LinkedNode prev = null;
        LinkedNode tmp = node;
        while (tmp != null) {
            LinkedNode nextTemp = tmp.next;
            tmp.next = prev;
            prev = tmp;
            tmp = nextTemp;
        }

        return prev;
    }

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        Solution solution = new Solution();
        int value = sc.nextInt();
        LinkedNode head = solution.new LinkedNode(value);
        LinkedNode mark = head;
        while (sc.hasNext()) {
            value = sc.nextInt();
            LinkedNode node = solution.new LinkedNode(value);
            mark.next = node;
            mark = node;
        }
        LinkedNode re = solution.reverse(head);
        System.out.println(re == mark);
        while (re != null) {
            System.out.println(re.value);
            re = re.next;
        }

    }
}