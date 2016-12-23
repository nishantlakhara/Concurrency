
public class MyRunnableNotify implements Runnable {
	
	MyWaitNotify m;
	
	public MyRunnableNotify(MyWaitNotify m) {
		this.m = m;
	}
	
	@Override
	public void run() {
//		while(true) {
			m.doNotify();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
	}
}
