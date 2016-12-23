package com.monitor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerBusyWait {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Lock lock = new ReentrantLock();

		for (int i = 0; i < 10; i++) {
			Thread producer = new Producer(queue, lock);
			Thread consumer = new Consumer(queue, lock);
			producer.start();
			consumer.start();
		}
	}
}

class Producer extends Thread {
	private Queue<Integer> queue;
	private Lock lock;

	public Producer(Queue<Integer> queue, Lock lock) {
		this.queue = queue;
		this.lock = lock;
	}

	public void run() {
		while (true) {
			int i = 1; // Producer makes some new task to be added.

			lock.lock(); // Acquire lock for initial busy-wait check.
			while (queue.size() == 10) { // Busy-wait until the queue is
										// non-full.
				lock.unlock();
				System.err.println("Lock dropped by thread : " + Thread.currentThread().getName());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Drop the lock temporarily to allow a chance for other threads
				// needing queueLock to run so that a consumer might take a
				// task.
				lock.lock(); // Re-acquire the lock for the next call to
								// "queue.isFull()".
			}

			queue.add(i); // Add the task to the queue.
			System.out.println(Thread.currentThread().getName() + " Producer");
			lock.unlock(); // Drop the queue lock until we need it again to add
							// the next task.
		}
	}
}

class Consumer extends Thread {
	private Queue<Integer> queue;
	private Lock lock;

	public Consumer(Queue<Integer> queue, Lock lock) {
		this.queue = queue;
		this.lock = lock;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.lock(); // Acquire lock for initial busy-wait check.
			System.err.println("Lock acquired by : " + Thread.currentThread().getName());
			while (queue.isEmpty()) { // Busy-wait until the queue is non-empty.
				lock.unlock();
				// Drop the lock temporarily to allow a chance for other threads
				// needing queueLock to run so that a producer might add a task.
				lock.lock(); // Re-acquire the lock for the next call to
								// "queue.isEmpty()".
			}
			int i = queue.remove(); // Take a task off of the queue.
			System.out.println(Thread.currentThread().getName() + " Consumer");
			lock.unlock(); // Drop the queue lock until we need it again to take
							// off the next task.
			// Go off and do something with the task.
		}
	}
}
