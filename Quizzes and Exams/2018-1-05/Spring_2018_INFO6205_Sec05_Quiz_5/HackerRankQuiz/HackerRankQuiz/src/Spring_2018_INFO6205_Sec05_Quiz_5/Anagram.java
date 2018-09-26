/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spring_2018_INFO6205_Sec05_Quiz_5;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Anagram {
    public static boolean isAnagram(String s, String t) {
        // put your implementation here
        HashMap<Character,Integer>
                map=new HashMap<>();
        char[]sc=s.toCharArray();
        for(char i:sc){
            if(map.get(i)==null)
                map.put(i,0);
            map.put(i,map.get(i)+1);
        }
        for(int i=0;i<t.length();i++){
            if(map.get(t.charAt(i))==null||map.get(t.charAt(i))<=0)
                return false;
            map.put(t.charAt(i),map.get(t.charAt(i))-1);
        }
        return true;
    }
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String t = scanner.next();
        System.out.println(Anagram.isAnagram(s, t));
    }
}
