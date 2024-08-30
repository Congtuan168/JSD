package tut03.task_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class task_thread_main extends task_thread {
    public task_thread_main(String characters) {
        super(characters);
    }

    public static void main(String[] args) {
        //Thread 1
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 50; i++) {
            Runnable worker1 = new task_thread("F");
            executorService1.execute(worker1);
        }
        executorService1.shutdown();

        //Thread 2
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 50; i++) {
            Runnable worker2 = new task_thread("I");
            executorService2.execute(worker2);
        }
        executorService2.shutdown();

        //Thread 3
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 50; i++) {
            Runnable worker3 = new task_thread("T");
            executorService3.execute(worker3);
        }
        executorService3.shutdown();

        //Thread 4
        ExecutorService executorService4 = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 50; i++) {
            Runnable worker4 = new task_thread("i = " + i);
            executorService4.execute(worker4);
        }
        executorService4.shutdown();
    }
}
