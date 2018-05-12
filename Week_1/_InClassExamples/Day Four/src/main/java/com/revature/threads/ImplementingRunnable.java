package com.revature.threads;

public class ImplementingRunnable implements Runnable {

	private Resource r;
	
	public ImplementingRunnable(Resource r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		r.populate();
	}
}
