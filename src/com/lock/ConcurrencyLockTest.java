package com.lock;

public class ConcurrencyLockTest {
	public static void main(String[] args) {
		Resource r = new Resource();
		ConcurrencyLockExample cle = new ConcurrencyLockExample(r);
		Thread t1 = new Thread(cle);
		Thread t2 = new Thread(cle);
		Thread t3 = new Thread(cle);
		t1.start();
		t2.start();
		t3.start();
	}
}
