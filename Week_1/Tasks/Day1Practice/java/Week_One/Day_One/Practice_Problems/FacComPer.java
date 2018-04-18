package Week_One.Day_One.Practice_Problems;
// Run Class User for console application.
public class FacComPer {
    public static int factorial(int num){
        if (num == 0)
            return 1;
        else
            return(num * factorial(num-1));
    }

    public static int combination(int n, int r) {
        if ( n < 0 || r < 0 || n < r) {
            System.out.println("\n--> Values must be as follow: n ≥ r ≥ 0" +
                    "\n--> Retry Test!");
            return 0;
        }
        else {
            return factorial(n) / (factorial(r) * factorial(n - r));
        }
    }

    public static int permutation(int n, int r) {
        if ( n < 0 || r < 0 || n < r ) {
            System.out.println("\n--> Values must be as follow: n ≥ r ≥ 0" +
                    "\n--> Retry Test!");
            return 0;
        }
        else {
            return factorial(n) / factorial(n - r);
        }
    }
}
