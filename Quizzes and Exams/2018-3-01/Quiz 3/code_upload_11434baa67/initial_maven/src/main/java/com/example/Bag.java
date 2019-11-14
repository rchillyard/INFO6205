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
      // TODO :: Implement the add method of bag. 
     
      if(full())
          grow(items,2*capacity());
      items[count++]=item;
          
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
  private static <T> T[] growFrom(T[] from, int size)
  {
     // TODO :: Implement the growFrom method of bag.
      T[]result=(T[])new Object[size];
      System.arraycopy(from,0,result,0,from.length);
      return result;
  } 
  private Item[] items = null;
  private int count = 0;
  public static void main (String args[]){

      Bag<Integer> bag = new Bag<>(); 
      System.out.println(bag.size());
  }
}
