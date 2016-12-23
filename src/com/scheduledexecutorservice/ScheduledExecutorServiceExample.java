package com.scheduledexecutorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService scheduledExecutorService =
		        Executors.newScheduledThreadPool(5);

		ScheduledFuture scheduledFuture =
		    scheduledExecutorService.schedule(new Callable() {
		        public Object call() throws Exception {
		            System.out.println("Executed!");
		            return "Called!";
		        }
		    },
		    5,
		    TimeUnit.SECONDS);

//		System.out.println("result = " + scheduledFuture.get());
//		System.out.println("result = " + scheduledFuture.get());
//		System.out.println("result = " + scheduledFuture.get());
//		System.out.println("result = " + scheduledFuture.get());
//		System.out.println("result = " + scheduledFuture.get());

		scheduledFuture =
			    scheduledExecutorService.scheduleAtFixedRate(new Thread() {
			        public void run() {
			        	System.out.println("Hello !!! I am called with fixed rate.");
			        }
			    },
			    5,
			    10,
			    TimeUnit.SECONDS);
		
//		scheduledFuture1.get();
		
		scheduledFuture =
			    scheduledExecutorService.scheduleWithFixedDelay(new Thread() {
			        public void run() {
			        	System.out.println("Hello !!! I am called with fixed delay.");
			        }
			    },
			    5,
			    10,
			    TimeUnit.SECONDS);
		
		scheduledFuture.get();
		
		scheduledExecutorService.shutdown();
	}
}
