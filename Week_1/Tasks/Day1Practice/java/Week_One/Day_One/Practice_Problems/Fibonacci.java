package Week_One.Day_One.Practice_Problems;
// Run Class User for console application.
public class Fibonacci {
    public static int fibonacciRecursive(int num) {
        if (num == 0) {return 0;}
        else if(num == 1){return 1;}
        else{
            return fibonacciRecursive(num - 1) + fibonacciRecursive(num - 2);
        }
    }
    public static int fibonacciIterative(int num) {
        int x = 0, y = 1, z = 1;
        for (int i = 0; i < num; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return x;
    }
}
