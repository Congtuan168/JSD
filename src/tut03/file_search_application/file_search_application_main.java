package tut03.file_search_application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class file_search_application_main {
    private static final int THREAD_COUNT = 3;
    private static int taskDoneCount = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<String> allFiles = new ArrayList<>();
        File dir = new File("src");
        File[] files = dir.listFiles();
        int length = files.length;
        int onePart = length / THREAD_COUNT;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            int startIndex = i * onePart; // the start index of the file list
            int endIndex = onePart * (i + 1);// the end index of the file list
            if (i == THREAD_COUNT - 1) {
                endIndex = files.length;
            }
            System.out.println("Thread#" + (i + 1) + " start index:" + startIndex + ", end index:" + (endIndex - 1));
            executor.execute(new file_search_application(startIndex, endIndex, files, fileList -> {
                synchronized (file_search_application_main.class) {
                    taskDoneCount++;
                    allFiles.addAll(fileList);
                    if (taskDoneCount == THREAD_COUNT) {// check if all tasks finished
                        executor.shutdown(); // shutdown the thread pool
                        System.out.println("allFiles = " + allFiles);
                        System.out.println("allFiles.size() = " + allFiles.size());
                        System.out.println("Time used: " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                }
            }));
        }
    }
}
