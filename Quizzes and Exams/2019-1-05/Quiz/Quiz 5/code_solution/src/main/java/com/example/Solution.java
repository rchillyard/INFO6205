package com.example;

public class Solution {
    int queue[];
    int first;
    int last;
    int N;
    int deckSize;

    public Solution(int maxSize, int[] deck){
        queue = new int[maxSize];
        deckSize = deck.length;
        first = 0;
        last = 0;
        for(int x:deck)
            enqueue(x);
    }

    public boolean isEmpty(){
        //implement here
        return N==0;
    }

    public void enqueue(int x){
        //implement here
        this.queue[N++]=x;
    }
    public int dequeue(){
        //implement here
        int re = this.queue[0];
        for(int i =0;i<N-1;i++){
            queue[i]=queue[i+1];
        }
        queue[N-1]=queue[0];
         for(int i =0;i<N-1;i++){
            queue[i]=queue[i+1];
        }
        N--;
        return re;
    }
    public int[] result(){
        //This is the function that will be called in test, please return the array here.
        int[] res = new int[this.deckSize];
        for(int i =0;i<this.deckSize;i++){
            res[i]= this.dequeue();
        }
        return res;
    }
}
