package com.anne.concurrency.example.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample1 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<10; i++) {
            final int index = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        exec.shutdown();
    }
}
