package com.revature.DayOne.Assignment;

public class PaskalTriangle {

	public static void main(String[] args) {
		triangle(4);

	}
	
	public static void triangle(int n) {
		 for (int line = 1; line <= n; line++)
		  {
		    int col = 1;
		    for (int i = 1; i <= line; i++)  
		    {
		      System.out.print(col+" ");
		      col= col*(line - i) / i;  
		    }
		    System.out.println();
		   
		  }
	}

}
