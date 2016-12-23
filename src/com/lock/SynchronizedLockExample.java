package com.lock;

public class SynchronizedLockExample implements Runnable{
	 
    private Resource resource;
     
    public SynchronizedLockExample(Resource r){
        this.resource = r;
    }
     
    @Override
    public void run() {
        synchronized (resource) {
            resource.doSomething();
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        resource.doLogging();
    }
}
