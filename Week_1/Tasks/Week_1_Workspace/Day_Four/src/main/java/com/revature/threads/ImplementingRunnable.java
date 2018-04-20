package com.revature.threads;

public class ImplementingRunnable implements Runnable {

	private Resource r;

	public ImplementingRunnable(Resource r) {
		this.r = r;
	}

	public void run() {
		r.populate();
	}

}
