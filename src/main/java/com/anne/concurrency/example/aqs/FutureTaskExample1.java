package com.anne.concurrency.example.aqs;

import java.util.concurrent.*;

public class FutureTaskExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something in myCallable");
                Thread.sleep(5000);
                return "Done";
            }
        });
        new Thread(futureTask).start();
        System.out.println("do something in main");
        System.out.println("thread result: " + futureTask.get());
    }
}
