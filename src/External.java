/*
@author Nathaniel Fishel
@assignment Project 2
@semester Fall 2019
@class COSC 311
@professor Dr. Haynes
@description This class will demonstrate that a external merge sort is O(n log n)
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
public class External {
    static Random r = new Random();
    static Scanner stdIn = new Scanner(System.in);
    static RandomAccessFile raf;
    static RandomAccessFile rafTwo;


    /*
    This method will take the appropriate amount of values from the file and place them into an array so it may be sorted
     */
    static void run(int n, int mem) throws IOException {
       int times;
        if(mem>n)
            times = 1;
       else {
           times = n / mem;
        }
        System.out.println("The file contains "+n+" values, only "+mem+" values can be sorted. The algorithm will be ran "+times+ " times.");
       //Create array to load portions of the values into and sort them
       while(times>0){
           int[]run = new int[mem];
           raf.seek(0);
           //load ints into temporary array.
           for(int i = 0; i<mem;i++){
               int t = raf.readInt();
               run[i] = t;
           }
           //sort the array
           sort(run, mem);

           //load the array back into a file
           for(int j = 0; j<mem; j++){
               rafTwo.writeInt(run[j]);
           }
           times--;
       }
    }

    /*
  This method is where the merge sort algorithm is used
   */
    static void sort(int[] arr, int n){
        //if the array is size one return
        if(n<2)
            return;
        //create two arrays find the middle of the array and store each half accordingly
        int m = n/2;
        int[] left = new int[m];
        int[] right = new int[n-m];
        //loop to store left half
        for(int i = 0; i<m; i++) {
            left[i] = arr[i];
        }
        //loop to store right half
        for(int j = m; j<n; j++) {
            right[j - m] = arr[j];
        }
        //recursive call for left side
        sort(left, m);
        //recursive call for right side
        sort(right, n-m);

        //merge the sorted arrays
        merge(arr, left, right, m, n-m);
    }

    /*
    this method is the second half of the merge sort algorithm
     */
    static void merge(int[]arr, int[]l, int[]r, int left, int right){
        int i = 0, j = 0, k = 0;
        //while the left sub array is not to the end and while the right sub array has not reached its end.
        while(i < left && j < right){
            if (l[i] <= r[j])
                arr[k++] = l[i++];
            else
                arr[k++] = r[j++];
        }
        //while the left sub array is the only one left
        while(i<left){
            arr[k++] = l[i++];
        }
        //while the right sub array is the only one left
        while (j<right){
            arr[k++] = r[j++];
        }
    }

    /*
    This method will generate numbers and write them to a file of the size specified by the user
     */
    public static int genArr() throws IOException {
        System.out.println("How large should the array be? ");
        int input = stdIn.nextInt();
        raf.seek(0);
        for(int i = 0; i<input; i++){
           int t = r.nextInt(10);
           raf.writeInt(t);
           System.out.println("Placing "+t+"@ index " +i);
        }
        return input;
    }

    /*
    print the content of the file
     */
    static void printArr(int n) throws IOException {
        System.out.println();
        System.out.println("The sorted file is:");
        rafTwo.seek(0);
        for(int i = 0; i<n; i++){
            int t = rafTwo.readInt();
            System.out.println("index "+i+" is "+ t);

        }
    }

    static int ramSize(){
        System.out.println("How many ints can fit in the 'RAM' for this experiment");
        int memory = stdIn.nextInt();
        return memory;
    }

    public static void main(String[] args) throws IOException {
        try {
            raf = new RandomAccessFile("external.dat", "rw");
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            }
        try {
            rafTwo = new RandomAccessFile("external2.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = genArr();
        int mem = ramSize();
        long start = System.nanoTime();
        run(n,mem);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        printArr(n);
        raf.close();
        System.out.println("Runtime in nanoseconds: "+timeElapsed);
    }
}
