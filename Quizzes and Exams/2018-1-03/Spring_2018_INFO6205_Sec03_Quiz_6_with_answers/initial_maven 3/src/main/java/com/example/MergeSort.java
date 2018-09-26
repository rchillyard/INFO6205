package com.example;

public class MergeSort<X extends Comparable<X>> {

    // This class should not be instantiated.
    private MergeSort() { }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    public static <X extends Comparable<X>> void merge(X[] a, X[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++){
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[j],aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        
    }

    public static <X extends Comparable<X>>X[] sort(X[] a) {
        //TODO:: Implement sort
        Comparable[] aux = new Comparable[a.length];
        sort(a,aux,0,a.length-1);
        return a;
    }
    
    private static <X extends Comparable<X>> void sort(X[] a, X[] aux, int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);
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