package tut03.multiple_sorting;

import java.util.Arrays;
import java.util.Random;

public class multiple_sorting implements Runnable {
    private String name;
    private sortAlgorithm sortAlgorithm;

    public multiple_sorting(String name, sortAlgorithm sortAlgorithm) {
        this.name = name;
        this.sortAlgorithm = sortAlgorithm;
    }
//    private
    @Override
    public void run() {
        int[] arr = createShuffleArray();
        long startTime = System.currentTimeMillis();
        sortAlgorithm.sort(arr);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println(name + " completed in " + duration + " milliseconds.");
    }
    public int[] createShuffleArray() {
        int length = 50; // The size of the array
        int[] numbers = new int[length];

        // Fill the array with numbers from 1 to 50
        for (int i = 0; i < length; i++) {
            numbers[i] = i + 1;
        }

        // Shuffle the array
        Random random = new Random();
        for (int i = length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }
    public static void pauseThread() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void insertionSort(int[] arr) {

        System.out.println("In disorder: " + Arrays.toString(arr));
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
        pauseThread();
        System.out.println("After Sort by Insertion Sort: " + Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {

        System.out.println("In disorder: " + Arrays.toString(arr));
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    // Swap
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        pauseThread();
        System.out.println("After Sort by Selection Sort: " + Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {

        System.out.println("In disorder: " + Arrays.toString(arr));
        int n = arr.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
        pauseThread();
        System.out.println("After Sort by Bubble Sort: " + Arrays.toString(arr));
    }
}
