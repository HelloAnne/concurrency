package com.anne.concurrency.example.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class LockExample3 {
    public static int clientTotal = 5000;
    public static int threadTotal = 200;
    public static int count = 0;
    private static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i<clientTotal; i++) {
            executorService.execute(()->{
                add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count);
    }

    private static void add() {
        long stamp = lock.writeLock();
        try{
            count++;
        } finally {
            lock.unlock(stamp);
        }
    }
}
