package com.example;

public class Solution<Key extends Comparable<Key>>  {
    
    public Key[] pq; 
    public int N; 
    
    public Solution(int capacity)  
    {  
        pq = (Key[]) new Comparable[capacity+1];
    } 
    
    public boolean isEmpty()    { 
        return N == 0;  
    } 
    
    private void swim(int k) {
        //ToDo : Implement swim method
        while(k>1){
            if(less(k/2,k))
                exch(k,k/2);
            k=k/2;
        }
    }
    
    public void insert(Key x) {
        pq[++N] = x;    
        swim(N);     
    }
    
    private void sink(int k) { 
        //TODO: Implement sink method
        int j=2*k;
        while(j<=N){
        if(j+1<=N && less(j,j+1))j++;
        exch(k,j);
        j=j*2;
        }
    }
    
    public Key delMax() 
    {    
        Key max = pq[1]; 
        exch(1, N--); 
        sink(1); 
        pq[N+1] = null;
        return max;
    } 

    private boolean less(int i, int j) 
    {   
        return pq[i].compareTo(pq[j]) < 0; 
    }  
    
    private void exch(int i, int j) 
    {  
        Key t = pq[i];
        pq[i] = pq[j]; 
        pq[j] = t;  
    }
    
  public static void main (String args[]){

  }
}
