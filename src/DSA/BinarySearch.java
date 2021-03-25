package DSA;
import java.util.Random;

public class BinarySearch {
    // Select what version of binary search we want.
    static int binarySearch(int[] a,int x ){
        return binarySearch3(a,x);
    }

    /*
    Binary search algorithm that doesn't use assertion, array needs to be sorted.
    Returns: the index in the array where x is or -1 if the algorithm fails.
     */
    public int binarySearch1(int [] a, int x){
        int l = 0;
        int r = a.length-1;
        while (l<=r){
            // Midpoint of the array.
            int m = (r+l)/2;
            // Check if the value in the middle of the array is smaller then x if so move the left boundary.
            if (a[m]<x) {
                l = m + 1;
            }
            // Check if the value in the middle of the array is greater then x if so move the right boundary.
            if(a[m]>x){
                r=m-1;
            }
            else{
                return m;
            }
            }
        // If the algorithm fails return -1
        return -1;
    }
    /*
    Binary search method that doesn't use assertions, array needs to be sorted.
    Returns: the index in the array where x is or l which is the index where x should be inserted in.
     */
    public int binarySearch2(int []a,int x){
        int l =0;
        int r = a.length-1;
        while(l<=r){
            int m = (r+l)/2;
            if (a[m]<x){
                l= m+1;
            }
            if(a[m]>x){
                r=m-1;
            }
            else{
                return m;
            }
        }
        return l;
    }

}


