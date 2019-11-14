import java.io.*;
import java.util.Scanner;

public class MedianSort{
    private int[] a;
    private int[] tempA;
    private int length;

    public MedianSort(int[] array){
        this.a = array;
        this.length = array.length;
        this.tempA = new int[length];
    }
public void mergeSort(int low, int high){
        //TODO:: Implement merge sort
        int mid;
        if(high<=low)return;

        mid=low+(high-low)/2;
        mergeSort(low,mid);
        mergeSort(mid+1,high);
        merge(low,mid,high);


        }

public void merge(int low, int mid, int high){
        //TODO:: Implement merge
        int i=low;
        int j=mid+1;
        int temp;
        for(int k=low;k<=high;k++){
        tempA[k]=a[k];
        }
        for(int k=low;k<=high;k++){
        if(i>mid)
        a[k]=tempA[j++];
        else
        if(j>high)
        a[k]=tempA[i++];
        else
        if(tempA[j]<tempA[i])
        a[k]=tempA[j++];
        else
        a[k]=tempA[i++];
        }
        }
    public double findMedian(int low, int high){
        double median = 0.0;
        if(length ==0)
            return median;
        mergeSort(low, high);
        if(length%2 == 0){
            median = (a[length/2]+a[length/2 - 1])/2.0;
        }else{
            median = a[length/2];
        }
        return median;
    }

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int sz = in.nextInt();
        int[] distribution = new int[sz];
        for(int i=0;i<sz;i++){
            distribution[i] = in.nextInt();
        }
        MedianSort ms = new MedianSort(distribution);
        System.out.println(ms.findMedian(0,distribution.length-1));
    }
}