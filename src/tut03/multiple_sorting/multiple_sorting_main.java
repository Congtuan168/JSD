package tut03.multiple_sorting;

import tut03.task_thread.task_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class multiple_sorting_main {

    public static void main(String[] args) {

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + processors);
        ExecutorService executorService1 = Executors.newFixedThreadPool(processors);

        executorService1.submit(new multiple_sorting("Selection Sort", multiple_sorting::selectionSort));
        executorService1.submit(new multiple_sorting("Insertion Sort", multiple_sorting::insertionSort));
        executorService1.submit(new multiple_sorting("Bubble Sort", multiple_sorting::bubbleSort));

        executorService1.shutdown();

    }
}
