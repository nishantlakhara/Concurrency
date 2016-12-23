package com.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		for(int i=0 ; i<1000 ; i++)
		executorService.execute(new Runnable() {
		    public void run() {
		        System.out.println("Asynchronous task");
		        try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});

		executorService.shutdown();
	}
}
