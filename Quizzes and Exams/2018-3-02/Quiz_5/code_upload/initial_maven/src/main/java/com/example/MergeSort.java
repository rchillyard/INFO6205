package com.example;

public class MergeSort<X extends Comparable<X>> {

    // This class should not be instantiated.
    private MergeSort() { }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    public static <X extends Comparable<X>> void merge(X[] a, X[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for(int l= 0; l<= hi; l++){
            aux[l] = a[l];
        }
        

        // merge back to a[]
        int i = lo, j = mid+1;
        for(int k =lo; k<= hi; k++){
            if(lo>= hi){
                break;
            }
            if(i>mid)
                a[k] = aux[j++];
            else if(j>hi)
                a[k] = aux[i++];
            else if(less(aux[j], aux[i]))
                a[k] = aux[j++];
            else 
                a[k] = aux[i++];
            
        }
        
    }
   
    public static <X extends Comparable<X>>X[] sort(X[] a) {
        int N = a.length; 
        Comparable[] aux = new Comparable[N];  
        for (int sz = 1; sz < N; sz = sz+sz) 
            for (int lo = 0; lo < N-sz; lo += sz+sz)   
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1)); 
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

}