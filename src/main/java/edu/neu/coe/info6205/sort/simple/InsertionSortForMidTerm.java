package edu.neu.coe.info6205.sort.simple;

public class InsertionSortForMidTerm {

		public void sort(String[] a) {
				for (int i = 0; i < a.length; i++) {
						swap(i, a);
				}
		}

		public void swap(int i, String[] a) {
				for (int j = i - 1; j >= 0; j--) {
						if (a[j].compareTo(a[i]) <= 0) break;
				}

		}

		public static void main(String[] args) {
				InsertionSortForMidTerm sorter = new InsertionSortForMidTerm();
				sorter.sort(args);
				for (String arg : args) System.out.println(arg);
		}
}
