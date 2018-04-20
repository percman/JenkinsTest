package com.revature.threads;

public class Resource {

	public synchronized void populate() {
		for(int i = 0; i < 15; i++) {
			System.out.println("Current thread: " + Thread.currentThread().getName() + " => " + i);
//			try {
//				Thread.sleep(250);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
