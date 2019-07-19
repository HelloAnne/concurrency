package com.anne.concurrency.example.commenUnsafe;

import com.anne.concurrency.annoations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * StringBuilder线程不安全，对应的线程安全类是StringBuffer
 */
@NotThreadSafe
public class StringExample1 {
    public static int clientTotal = 5000;
    public static int threadTotal = 200;
    public static StringBuilder string = new StringBuilder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i<clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(string.length());
    }

    private static void update() {
        string.append("a");
    }
}
