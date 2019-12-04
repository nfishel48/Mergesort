import java.util.Random;
import java.util.Scanner;

/*
@author Nathaniel Fishel
@assignment Project 2
@semester Fall 2019
@class COSC 311
@professor Dr. Haynes
@description This class will demonstrate that a internal merge sort is O(n log n)
 */
public class Internal {
    static Random r = new Random();
    static Scanner stdIn = new Scanner(System.in);
    static int[] arr;

    /*
    This method is where the merge sort algorithm is used
     */
    static int[] sort(int[] arr){
        //Find the middle of the array and divide it in half
        int m = arr.length-1/2;
        
    }

    /*
    This method will generate a int array of the size specified by the user
     */
    public static void genArr(){
        System.out.println("How large should the array be? ");
        int input = stdIn.nextInt();
        arr =  new int[input];
    }

    /*
    This method will fill the array with random ints
     */
    static void fillArr(){
        for(int i = 0; i<arr.length; i++){
            arr[i] = r.nextInt(100);
            System.out.println("index "+i+" has been filled with "+arr[i]);
        }

    }

    public static void main(String[] args){
        genArr();
        fillArr();
    }
}
