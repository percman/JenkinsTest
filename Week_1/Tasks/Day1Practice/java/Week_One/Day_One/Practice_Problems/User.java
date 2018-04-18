package Week_One.Day_One.Practice_Problems;

import java.util.Scanner;

//Fibonacci (recursively / iteratively)
//Factorial / Combination / Permutation
//Pascal's triangle (print the nth row, triangle)
//Reverse a string (only using charAt)
//Program to test if string is a palindrome
//Write a method that swaps two int's without using a third
//Write a method that determines if a number is even or odd without modulus


public class User {
    // Variables
    static int select;
    // Create scanner for user input.
    static Scanner input = new Scanner(System.in);

    // Methods
    public static void menu(){
        System.out.println("Hello User, which of the following practice problem would you like to test?" +
                "\n1 - Fibonacci (Recursive)" +
                "\n2 - Fibonacci (Iteratively)" +
                "\n3 - Factorial" +
                "\n4 - Combination" +
                "\n5 - Permutation" +
                "\n6 - Pascal's Triangle" +
                "\n7 - Palindrome" +
                "\n8 - Swaps Two Ints" +
                "\n9 - Odd or Even?" +
                "\n0 - Exit Program");
        System.out.print("\nEnter number: ");
    }

    public static void retrieveProblem(int num) {
        int n, r, x, y;
        switch(num){
            case 1:
                // Fibonacci (Recursive)
                System.out.print("\nEnter the number of the place within the Fibonacci sequence you wish to receive: ");
                System.out.println("\nAnswer: " + Fibonacci.fibonacciRecursive(input.nextInt()));
                break;
            case 2:
                // Fibonacci (Iteratively)
                System.out.print("\nEnter the number of the place within the Fibonacci sequence you wish to receive: ");
                System.out.println("\nAnswer: " + Fibonacci.fibonacciIterative(input.nextInt()));
                break;
            case 3:
                // Factorial
                System.out.print("\nEnter the number you want to use factorial: ");
                System.out.println("\nAnswer: " + FacComPer.factorial(input.nextInt()));
                break;
            case 4:
                // Combination
                System.out.print("\nEnter the number of objects(n): ");
                n = input.nextInt();
                System.out.print("Enter the number of the sample set(r): ");
                r = input.nextInt();
                System.out.println("\nAnswer: " + FacComPer.combination(n, r));
                break;
            case 5:
                // Permutation
                System.out.print("\nEnter the number of objects(n): ");
                n = input.nextInt();
                System.out.print("Enter the number of the sample set(r): ");
                r = input.nextInt();
                System.out.println("\nAnswer: " + FacComPer.permutation(n, r));
                break;
            case 6:
                // Pascal's Triangle
                System.out.print("\nEnter the number of rows: ");
                Pascal.pascal(input.nextInt());
                break;
            case 7:
                // Palindrome
                System.out.print("\nEnter word(lower-case only): ");
                Palindrome.palindrome(input.next());
                break;
            case 8:
                // Swaps Two Ints
                System.out.print("\nEnter value for x: ");
                x = input.nextInt();
                System.out.print("Enter value for y: ");
                y = input.nextInt();
                IntsSwap.swap(x, y);
                break;
            case 9:
                // Odd or Even?
                System.out.print("\nEnter a number: ");
                OddEven.oddeven(input.nextInt());
                break;
            default:
                System.out.println("Test complete.\n");
                User.menu();
        }
    }

    public static void main(String[] args) {
        // Create user.
        User user = new User();
        // Menu for user
        do {
            user.menu();
            // Get user's input
            user.retrieveProblem(input.nextInt());
            System.out.println("\nWould you like to test another practice problem?" +
                    "\n1 - Yes" +
                    "\n0 - No");
            System.out.print("\nEnter number: ");
            select = input.nextInt();
        }
        while(select != 0);
        System.out.println("\nProgram Terminated.");
    }
}
