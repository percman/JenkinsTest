package Week_One.Day_One.Practice_Problems;
// Run Class User for console application.
public class IntsSwap {
    public static void swap(int x, int y) {
        System.out.println("\nSwapping without a third variable...");
        System.out.println("\nx = " + x + " + " + y);
        x = x + y;
        System.out.println("x is now " + x + ".\ny is now " + y + ".");
        System.out.println("\ny = " + x + " - " + y);
        y = x - y;
        System.out.println("x is now " + x + ".\ny is now " + y + ".");
        System.out.println("\nx = " + x + " - " + y);
        x = x - y;
        System.out.println("x is now " + x + ".\ny is now " + y + ".");
    }
}
