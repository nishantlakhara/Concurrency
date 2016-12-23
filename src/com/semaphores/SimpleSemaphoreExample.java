package com.semaphores;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleSemaphoreExample {

    static int counter = 0;
    static Semaphore semaphore = new Semaphore(3);
    
    public static void incrementCounter(int i){
        try {
            semaphore.acquire();
            counter++;
            System.out.println("I am critical : " + i);
            Thread.sleep(10);
            System.out.println("You should know that :  " + i);
            semaphore.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(SimpleSemaphoreExample.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public static void main(String[] args) {

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                for (int i = 5001; i < 10000; i++) {
                     incrementCounter(i);
                }

            }
        };


        Thread thread2 = new Thread() {

            @Override
            public void run() {
                for (int i = 10001; i < 15000; i++) {
                    incrementCounter(i);
                }

            }
        };
        
        Thread thread3 = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    incrementCounter(i);
                }

            }
        };

        thread1.start();
        thread2.start();
        thread3.start();

        while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {
        }

        System.out.println("Counter : " + counter);


    }
}