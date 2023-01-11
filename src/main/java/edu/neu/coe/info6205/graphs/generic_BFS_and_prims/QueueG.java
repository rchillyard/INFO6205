package edu.neu.coe.info6205.graphs.generic_BFS_and_prims;

public class QueueG<T> {
    private T[] arr;
    private int capacity;
    private int front;
    private int rear;
    private int count;


    public QueueG(int size) {
        arr = (T[]) new Object[size];
        capacity = size;
        front =0;
        rear = -1;
        count = 0;

    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (size()==0);
    }

    public boolean isFull() {
        return (size()==capacity);

    }
    public void enqueue(T t) {
        if(isFull()) {
            System.exit(1);
        }
        rear = (rear+1)%capacity;
        arr[rear]=t;
        count++;
    }

    public T dequeue() {
        if(isEmpty()) {
            System.exit(1);
        }

        T e = arr[front];
        front = (front+1)%capacity;
        count--;
        return e;
    }

    public T peek() {
        if (isEmpty()) {
            System.exit(1);
        }
        return arr[front];
    }
}
