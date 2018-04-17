package com.revature.fibonacci;

public class Fibonacci {
	public void iterative(int times) {
		int twoBefore=1, oneBefore=1, current=0;
		if(times >= 1) System.out.print(twoBefore+ " ");
		if(times>=2) System.out.print(oneBefore+" ");
		if(times>2) {
			for(int i = 2;i<times;i++) {
				current=twoBefore+oneBefore;
				System.out.print(current + " ");
				twoBefore=oneBefore;
				oneBefore=current;
			}
		}
		System.out.println();
	}
	
	public int recursive(int times) {
		if(times==1 || times == 2) return 1;
		else if(times>2) {
			return recursive(times-1)+recursive(times-2);
		}
		else return 0;
	}
}
