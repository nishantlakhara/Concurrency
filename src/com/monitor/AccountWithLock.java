package com.monitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithLock {
	private Lock myLock = new ReentrantLock();
	private int balance;

	public boolean withdraw(int amount) {
		if (amount >= 0) {
			myLock.lock();
			try {
				if (balance < amount) {
					return false;
				} else {
					balance = balance - amount;
					return true;
				}
			} finally {
				myLock.unlock();
			}
		}
		return false;
	}

	public void deposit(int amount) {
		if (amount >= 0) {
			myLock.lock();
			try {
				balance = balance + amount;
			} finally {
				myLock.unlock();
			}
		}
	}

	public static void main(final String[] args) {
		final AccountWithLock account = new AccountWithLock();
		for (int i = 0; i < 100; i++) {
			Thread thread1 = new Thread(new Depositor(account));
			thread1.start();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for (int i = 0; i < 100; i++) {
			Thread thread2 = new Thread(new Withdrawer(account));
			thread2.start();
		}
		
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Balance = " + account.balance);
		}
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}

class Depositor implements Runnable {
	AccountWithLock accountWithLock;

	public Depositor(AccountWithLock accountWithLock) {
		super();
		this.accountWithLock = accountWithLock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			accountWithLock.deposit(100);
			System.out.println(Thread.currentThread().getName() + " balance == " + accountWithLock.getBalance());
		}
	}
}

class Withdrawer implements Runnable {
	AccountWithLock accountWithLock;

	public Withdrawer(AccountWithLock accountWithLock) {
		super();
		this.accountWithLock = accountWithLock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			accountWithLock.withdraw(100);
			System.out.println(Thread.currentThread().getName() + " balance == " + accountWithLock.getBalance());
		}
	}
}
