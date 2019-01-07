package edu.neu.coe.StudentRank;

public class StudentRank {
	Student[] roster;
	int size;
	
    public StudentRank(int n) {
		super();
		roster = new Student[n];
		size = 0;
	}
    
    public Student[] getRoster() {
		return roster;
	}

	public void addStudent(Student s) {
    		roster[size++] = s;
    }
    
    public void rankStudents() {
    		sort(roster);
    }
    
    public void sort(Student[] roster) {
    		//TODO Implement insertion sort that sorts the students based on GPA from highest to lowest
    	int N = roster.length;
        for (int i = 0; i < N; i ++){
            
            for (int j = i; j > 0; j --){
                if(higher(roster[j], roster[j-1]))
                    exchange(roster,j,j-1) ;
            }
        }
    }
    
    public static boolean higher(Student s1, Student s2) {
    		//TODO Implement the higher() function used by your sort method. Hint: it should use the Student class's compareTo() method
        return s1.compareTo(s2)>0;
	}
    
    private void exchange(Student[] array, int i, int j) {
    		Student s = array[i];
    		array[i] = array[j];
    		array[j] = s;
    }
    
    
}
