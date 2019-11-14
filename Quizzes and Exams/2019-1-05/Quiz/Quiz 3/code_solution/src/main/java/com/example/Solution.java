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
        return N <=0;
    }

    public void enqueue(int x){
        if(last==queue.length-1)
            last = 0;
        else
            last++;
        queue[last] = x;
        if(isEmpty())
            first = last;
        N++;
    }
    public int dequeue(){
        int tmp = queue[first];
        N--;
        if(first==queue.length-1)
            first = 0;
        else
            first++;
        return tmp;
    }
    public int[] result(){
        int[] result = new int[deckSize];
        int i = 0;
        while(deckSize>0){
            result[i] = dequeue();
            enqueue(dequeue());
            i++;
            deckSize--;
        }
        return result;
    }
}

