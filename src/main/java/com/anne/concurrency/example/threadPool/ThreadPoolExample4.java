package com.anne.concurrency.example.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
//        exec.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("do sth");
//            }
//        }, 3, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(() -> System.out.println("schedule run."), 1, 3, TimeUnit.SECONDS);
//        exec.shutdown();
    }
}