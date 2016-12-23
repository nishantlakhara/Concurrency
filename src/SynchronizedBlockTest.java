
public class SynchronizedBlockTest {
	private B b = new B();;
	
	public void m1() throws InterruptedException {
		//synchronized(b) {
		while(true) {
//			synchronized(b) {
				b.incrementB();
				System.err.println("b == " + b.b);
//			}
			Thread.sleep(1000);
		}
		//}
	}
	
	public void m2() throws InterruptedException {
			
			while(true) {
				synchronized(b) {
					b.decrementB();
					Thread.sleep(1000);
					b.decrementB();
					Thread.sleep(1000);
					System.err.println("b.b ==" + b.b);
				}
				Thread.sleep(1000);
			}
			
	}
	
	public static void main(String[] args) throws InterruptedException {
		final SynchronizedBlockTest sbt = new SynchronizedBlockTest();
		Thread t1 = new Thread() {
			public void run() {
				try {
					sbt.m1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				try {
					sbt.m2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		t2.start();
		t1.start();
		t1.join();
		t2.join();
		
		
	}
}

class B {
	public int b = 0;
	
	public void incrementB() {
		b++;
	}
	
	public void decrementB() {
		b--;
	}
}
