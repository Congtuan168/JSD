package tut03.multiple_sorting;

import java.util.Arrays;
import java.util.Random;

public class shufffle_array {
    public static void main(String[] args) {
        int n = 50; // The size of the array
        int[] numbers = new int[n];

        // Fill the array with numbers from 1 to 50
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        // Shuffle the array
        Random random = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        // Print the shuffled array
        System.out.println("In disorder: " + Arrays.toString(numbers));
        insertionSort(numbers);
    }
    public static void insertionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int key = arr[i];
            int keyCompare = i - 1;

            while (keyCompare >= 0 && arr[keyCompare] > key) {
                arr[keyCompare + 1] = arr[keyCompare];
                keyCompare = keyCompare - 1;
            }
            arr[keyCompare + 1] = key;
        }
        System.out.println("In DESC disorder: " + Arrays.toString(arr));
    }
}
