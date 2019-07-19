package com.anne.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample1 {
    private static int threadCount = 200;
    public static void main(String[] args) {
        final ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(10);
        for (int i=0; i<threadCount; i++) {
            final int threadNum = i;
            exec.execute(()->{
                try {
                    if(semaphore.tryAcquire(30, TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        System.out.println("Timestamp: "+System.currentTimeMillis()+";  Thread No.: " +threadNum);
        Thread.sleep(10);
    }
}
