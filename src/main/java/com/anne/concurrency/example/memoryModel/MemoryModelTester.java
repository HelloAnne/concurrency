package com.anne.concurrency.example.memoryModel;

import java.util.Random;

public class MemoryModelTester {
    volatile int x=0, y=0, x_read, y_read;

    Thread createThread1(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                randomSleep();
                x=1;
                y_read=y;
            }
        });
    }
    Thread createThread2(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                randomSleep();
                y=1;
                x_read=x;
            }
        });
    }

    private void randomSleep() {
        try {
            Thread.sleep(new Random().nextInt(30));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while(true) {
            MemoryModelTester tester = new MemoryModelTester();
            Thread thread1 = tester.createThread1();
            Thread thread2 = tester.createThread2();

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
            System.out.println(String.format("(%d, %d)", tester.x_read, tester.y_read));
            if (tester.x_read==0 && tester.y_read==0) {
                throw new RuntimeException("stop!");
            }
        }
    }
}
