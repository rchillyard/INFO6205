package edu.neu.coe.StudentRank;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class StudentRankTest     extends TestCase{
	
	public void test1() {
		Student tom = new Student("Tom", 1, 3.0);
		Student jane = new Student("Jane", 2, 3.5);
		assertTrue(StudentRank.higher(jane, tom));
	}
	
	public void test2() {
		Student tom = new Student("Tom", 1, 3.0);
		Student jane = new Student("Jane", 2, 2.9);
		assertFalse(StudentRank.higher(jane, tom));
	}
	
	public void test3() {
		Student tom = new Student("Tom", 1, 3.0);
		Student jane = new Student("Jane", 2, 3.0);
		assertFalse(StudentRank.higher(jane, tom));
	}
	
    public void test4() {
    		StudentRank ranking = new StudentRank(6);
    		ranking.addStudent(new Student("Tom", 1, 3.0));
    		ranking.addStudent(new Student("Jane", 2, 3.5));
    		ranking.addStudent(new Student("Sam", 3, 2.9));
    		ranking.addStudent(new Student("Jim", 4, 3.8));
    		ranking.addStudent(new Student("Tina", 5, 2.7));
    		ranking.addStudent(new Student("George", 6, 3.3));
    		ranking.rankStudents();
    		assertEquals(ranking.roster[0].toString(), "Jim");
    		
    }

    public void test5() {
		StudentRank ranking = new StudentRank(6);
		ranking.addStudent(new Student("Tom", 1, 3.0));
		ranking.addStudent(new Student("Jane", 2, 3.5));
		ranking.addStudent(new Student("Sam", 3, 2.9));
		ranking.addStudent(new Student("Jim", 4, 3.8));
		ranking.addStudent(new Student("Tina", 5, 2.7));
		ranking.addStudent(new Student("George", 6, 3.3));
		ranking.rankStudents();
		assertEquals(ranking.roster[5].toString(), "Tina");
		
    }
    
    public void test6() {
		StudentRank ranking = new StudentRank(6);
		ranking.addStudent(new Student("Tom", 1, 3.0));
		ranking.addStudent(new Student("Jane", 2, 3.5));
		ranking.addStudent(new Student("Sam", 3, 2.9));
		ranking.addStudent(new Student("Jim", 4, 3.8));
		ranking.addStudent(new Student("Tina", 5, 2.7));
		ranking.addStudent(new Student("George", 6, 3.3));
		ranking.rankStudents();
		assertEquals(ranking.roster[2].toString(), "George");
		
    }
}
