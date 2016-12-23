package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class ConcurrencyLockExample implements Runnable{
 
    private Resource resource;
    private Lock lock;
     
    public ConcurrencyLockExample(Resource r){
        this.resource = r;
        this.lock = new ReentrantLock();
    }
     
    @Override
    public void run() {
        try {
            if(lock.tryLock(999, TimeUnit.MILLISECONDS)){
            	resource.doSomething();
            	// Thread.sleep(999);  //uncommenting it would increase the time from 999 MILLISECONDS to more than 1000 MILLISECONDS and exception would be thrown.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        resource.doLogging();
    }
 
}