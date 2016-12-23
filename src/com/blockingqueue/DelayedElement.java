package com.blockingqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement implements Delayed {

	private int i;
	private long startTime;
	public DelayedElement(int i, long delay) {
		this.setI(i);
		this.startTime = delay + System.currentTimeMillis();
	}

	@Override
	public int compareTo(Delayed o) {
		if(o instanceof DelayedElement) {
			return this.i - ((DelayedElement)o).i;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = startTime - System.currentTimeMillis();
		System.out.println("Diff == " + diff);
        return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
