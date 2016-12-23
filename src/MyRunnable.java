
public class MyRunnable implements Runnable {
	
	MyWaitNotify m;
	
	public MyRunnable(MyWaitNotify m) {
		this.m = m;
	}
	
	@Override
	public void run() {
//		while(true) {
			try {
				m.doWait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}
}
