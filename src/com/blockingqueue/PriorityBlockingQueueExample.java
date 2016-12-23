package com.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue   = new PriorityBlockingQueue<String>();

	    //String implements java.lang.Comparable
	    queue.put("Value");

	    String value = queue.take();
	    System.out.println("Value = " + value);
	}
}
