package com.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class DelayQueueExample {
	public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> queue = new DelayQueue<DelayedElement>();
        DelayedElement element1 = new DelayedElement(5 , 1000l);
        queue.put(element1);
        System.out.println("Put done");
        Delayed element2 = queue.take();
        System.out.println("Take done");
        System.out.println(((DelayedElement)element2).getI());
    }
}