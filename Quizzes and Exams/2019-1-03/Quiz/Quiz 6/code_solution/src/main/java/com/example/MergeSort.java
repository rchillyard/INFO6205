package com.example;

public class MergeSort<X extends Comparable<X>> {
    
    public static <X extends Comparable<X>> void merge(X[] a, X[] aux, int lo, int mid, int hi) {
        //TODO stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]

        for(int k = lo ; k<=hi; k++){
            
            aux[k] = a[k];
            
        }
        
        int i = lo;
        int j = mid+1;
        
        
        for(int k = lo; k<=hi; k++){
            
            if(i>mid)  a[k]= aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(less(aux[j],aux[i])) a[k] = aux[j++];
            else    a[k] = aux[i++];
        }
        
        
    }
   
    public static <X extends Comparable<X>>X[] sort(X[] a) {
       //TODO sorting without recursion
        
        int n  = a.length;
        Comparable aux[] = new Comparable[n];
        //int sz = 1;
            
        for(int sz = 1; sz < n; sz = sz +sz ){
            
            for(int lo=0; lo<n-sz ; lo += sz +sz ){
                
                merge(a,aux,lo,lo+sz-1,Math.min(lo+sz+sz-1,n-1));
                
                
                
            }
            
            
            
            
        }
        
        return a;
        
        
        
    }

    private static <X extends Comparable<X>> boolean less(X v, X w) {
        return v.compareTo(w) < 0;
    }
    
    private static <X extends Comparable<X>> boolean isSorted(X[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    private MergeSort() {
    
    
    
    
    }


}