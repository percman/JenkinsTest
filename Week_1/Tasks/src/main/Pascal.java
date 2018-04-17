package main;

public class Pascal {
	public static int element(int r, int c) {
		if(r == 0 || c <= 0 || c >= r)
			return 1;
		else
			return element(r-1, c-1) + element(r-1, c);
	}
	
	public static int[] row(int r) {
		int[] returnValue = new int[r+1];
		for(int c=0; c<=r; c++) 
			returnValue[c] = Pascal.element(r, c);
		return returnValue;	
	}
	
	public static int[][] triangle(int r){
		int[][] returnValue = new int[r+1][r+1];
		for(int c=0; c<=r; c++)
			returnValue[c] = Pascal.row(c);
		
		return returnValue;
		
	}
}
