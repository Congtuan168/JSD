package tut02.palindrome;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class palindrome {

    public static void generateAllBinary(int length, int arr[], int index) {
        //case1: length = index

        if (length == index) {
            printBin(length, arr);

            return;

        }
        //case 2: assign arr[index]=0, index=1
        arr[index] = 0;
        generateAllBinary(length, arr, index + 1);
        System.out.println(arr.toString());

        //case 3: assign arr[index]=1, index = 2
        arr[index] = 1;
        generateAllBinary(length, arr, index + 1);
//        System.out.println(arr);
//        try (PrintWriter writer = new PrintWriter(new FileWriter("src/tut02/palindrome/result.out"))) {
//            writer.println(length);
//            writer.println(arr);
//            writer.println("\n");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


    public static void printBin(int length, int arr[]) {
        for (int i = 0; i < length; i++) {
//            System.out.print(arr[i] + " ");
        }
//        System.out.println();

    }


    public static void main(String[] args) throws Exception {
//        get input from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number: ");
        int length = sc.nextInt();

        //create new arr
        int[] arr = new int[length];
        generateAllBinary(length, arr, 0);

    }

}