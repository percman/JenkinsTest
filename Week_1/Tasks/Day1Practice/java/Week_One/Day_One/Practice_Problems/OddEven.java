package Week_One.Day_One.Practice_Problems;
// Run Class User for console application.
public class OddEven {
    public static void oddeven(int num) {
        // Odd or even check by bitwise AND operator
        if ((num & 1) == 0) {
            System.out.println("\n" + num + " is an even number.");
        }
        else {
            System.out.println("\n" + num + " is an odd number.");
        }
    }
}
