package com.example;
import java.util.Arrays; 
import java.util.Iterator;
public class Bag<Item> {

  public Bag() 
  {
     grow((Item[])new Object[0], 5);
  } 
  public void add(Item item) 
  {
      if(full())
      {
          grow(items,items.length*2);
      }
      items[count]=item;
      count++;
  } 
  private static <T> T[] growFrom(T[] from, int size)
  {
     T[] newBag = (T[])new Object[size]; 
     for(int i=0;i<from.length;i++)
         newBag[i]=from[i];
      return newBag;
  } 
  public boolean isEmpty() 
  {
    return count==0;
  }
  public int size()
  {
    return count;
  }
  public Iterator<Item> iterator() 
  {
    return Arrays.asList(Arrays.copyOf(items,count)).iterator();
  } 
  private void grow(Item[] source, int size) 
  {
    items = growFrom(source, size);
  } 
  public int capacity() 
  {
    return items.length; 
  }
  private boolean full() 
  {
    return size()==capacity();
  }
  private Item[] items = null;
  private int count = 0;
  public static void main (String args[]){
      Bag<Integer> bag = new Bag<>(); 
      System.out.println(bag.size());
  }
}
