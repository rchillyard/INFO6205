package edu.neu.coe.StudentRank;


public class Student {
	private String name;
	private int id;
	private double gpa;
	
	public Student(String name, int id, double gpa) {
		super();
		this.name = name;
		this.id = id;
		this.gpa = gpa;
	}
	
	public int compareTo(Student that) {
		if (this.gpa < that.gpa) return -1;
		else if (this.gpa > that.gpa) return 1;
		else return 0;
	}
	
	public String toString() {
		return name;
	}
}
