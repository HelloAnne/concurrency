package com.anne.concurrency.example.aqs;

import java.util.concurrent.*;

public class FutureExample1 {
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws InterruptedException {
            System.out.println("do something in myCallable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> future = exec.submit(new MyCallable());
        System.out.println("do something in main");
        System.out.println("return message: " + future.get());
    }
}
