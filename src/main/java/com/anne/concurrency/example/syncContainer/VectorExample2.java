package com.anne.concurrency.example.syncContainer;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class VectorExample2 {
    public static int clientTotal = 5000;
    public static int threadTotal = 200;
    private static Vector<Integer> vector;
    public static void main(String[] args) throws InterruptedException {


        while(true) {
            vector = new Vector<>();
            for (int i=0; i<10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                @Override
                public void run(){
                    for (int i=0; i<vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for (int i=0; i<vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }

    private static void update(int i) {
        vector.add(i);
    }
}
