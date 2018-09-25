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
        return last-first == 0;
    }

    public void enqueue(int x){
        //implement here
        if(last >= queue.length){
            for(int i = 0; i<last-first;i++){
                queue[i] = queue[i+first];
            }
            last-=first;
            first = 0;
        }
        queue[last] = x;
        last ++;
    }
    public int dequeue(){
        //implement here
        int r = queue[first];
        first++;
        return r;
    }
    public int[] result(){
        //This is the function that will be called in test, please return the array here.
       int[] result = new int[deckSize];
        int index = -1;
        while(!isEmpty()){
            index++;
            result[index] = dequeue();
            enqueue(dequeue());
            
        }
        return result;
    }
}
