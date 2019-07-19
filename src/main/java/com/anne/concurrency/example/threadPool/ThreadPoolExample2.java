package com.anne.concurrency.example.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i=0; i<10; i++) {
            final int index = i;
            exec.execute(() -> System.out.println(index));
        }
        exec.shutdown();
    }
}
