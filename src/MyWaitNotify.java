public class MyWaitNotify {

	MonitorObject myMonitorObject = new MonitorObject();
	boolean wasSignalled = false;

	public void doWait() throws InterruptedException {
		synchronized (myMonitorObject) {
			while(!wasSignalled) {
				try {
					System.out.println("Inside wait " + Thread.currentThread().getName());
					myMonitorObject.wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.println("Out of wait " + Thread.currentThread().getName());
			// clear signal and continue running.
			wasSignalled = false;
			System.out.println("Waiting....");
			Thread.sleep(5000);
		}
	}

	public void doNotify() {
		synchronized (myMonitorObject) {
			wasSignalled = true;
			System.out.println("Notifying " + Thread.currentThread().getName());
			myMonitorObject.notifyAll();
		}
	}

	public static void main(String[] args) {
		MyWaitNotify m = new MyWaitNotify();
		Thread[] threads = new Thread[10];
		Thread[] threads2 = new Thread[10];
		
		for(int i=0; i<2; i++) {
			 threads[i] = new Thread(new MyRunnable(m));
			 threads[i].setName("wait " + i);
			 threads[i].start();
		}
		
		for(int i=0; i<1; i++) {
			 threads2[i] = new Thread(new MyRunnableNotify(m));
			 threads2[i].setName("notify " + i);
			 threads2[i].start();
		}
	}
}
