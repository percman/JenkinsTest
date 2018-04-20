package com.revature.threads;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		Resource r = new Resource();
		
		ExtendedThread t1 = new ExtendedThread(r);
		Thread t2 = new Thread(new ImplementingRunnable(r));
		ExtendedThread t3 = new ExtendedThread(r);
		Thread t4 = new Thread(new ImplementingRunnable(r));
		ExtendedThread t5 = new ExtendedThread(r);
		Thread t6 = new Thread(new ImplementingRunnable(r));
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		
		System.out.println("All threads have completed their execution");
		
	}
}
