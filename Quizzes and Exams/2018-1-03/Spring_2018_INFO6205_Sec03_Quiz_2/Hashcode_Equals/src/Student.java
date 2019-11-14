import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;

public class Student{
    String name;
    int id;

    public Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student "+name+" has id "+id+"";
    }
    //TODO:: implement equals
    @Override
    public boolean equals(Object obj){

        //TODO:: add the logic if this == obj. uncomment the return line and add your logic
        if(this == obj){
            return true;
        }

        //TODO:: add the logic if the object is null and not of same class type.uncomment the return line and add your logic
        if(obj == null || obj.getClass()!= this.getClass()){
            return false;
        }

        //TODO:: uncomment the below line. then add the logic if the ids dont match.uncomment the return line and add your logic

        Student student = (Student) obj;
        if(id != student.id || !(name.equals(student.name))){
            return false;
        }

        //TODO:: change the return false.
        return true;
    }

    @Override
    public int hashCode(){
        int result = 7;
        char c[]=this.name.toCharArray();
        //TODO:: implement your logic for hashcode. Remember, the hashcode must be unique
        for(int i=0;i<c.length;i++){
            result += (int)c[i] * this.id;
        }
        return result;
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scan = new Scanner(System.in);

        String studentName = scan.next();
        int id = scan.nextInt();
        Student s1 = new Student(studentName, id);

        studentName = scan.next();
        id = scan.nextInt();
        Student s2 = new Student(studentName, id);

        studentName = scan.next();
        id = scan.nextInt();
        Student s3 = new Student(studentName, id);

        studentName = scan.next();
        id = scan.nextInt();
        Student s4 = new Student(studentName, id);

        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode() == s2.hashCode());
        System.out.println(s1.equals(s3));
        System.out.println(s1.hashCode() == s3.hashCode());
        System.out.println(s1.equals(s4));
        System.out.println(s1.hashCode() == s4.hashCode());
    }
}