package com.example;

public class Solution {

    public static int[] solution(int[][] list){
        //please do not edit this part
        return mergeSort(list,0,list.length-1);
    }

    public static int[] mergeSort(int[][] list,int start, int end){
        //Please implement this part
         int[] left={};
        for (int i=0;i<list.length;i++){
            left=merge(left,list[i]);
        }
        return left;
    }

    public static int[] merge(int[] l1,int[] l2){
        //Please implement this part
        int a=0;
        int b=0;
        int[] result=new int[l1.length+l2.length];
        int now=0;
        while (a<l1.length || b<l2.length){
            if (a<l1.length){
                if (b>=l2.length || l1[a]<l2[b]){
                    result[now]=l1[a];
                    now++;
                    a++;
                }else{
                    result[now]=l2[b];
                    now++;
                    b++;
                }
            }else{
                result[now]=l2[b];
                b++;
                now++;
            }
        }
        
        return result;
}
}
