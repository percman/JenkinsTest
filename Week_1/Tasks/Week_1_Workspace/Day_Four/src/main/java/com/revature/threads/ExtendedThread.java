package com.revature.threads;

public class ExtendedThread extends Thread {

	private Resource r;
	
	public ExtendedThread(Resource r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		r.populate();
	}
	
}
