package Week_One.Day_One.Practice_Problems;

// Run Class User for console application.
public class Palindrome {
    public static void palindrome(String word) {
        int length = word.length();
        String reverse = "";

        for (int i = length - 1; i >= 0; i--)
            reverse = reverse + word.charAt(i);

        if (word.equals(reverse))
            System.out.println(word + " is a palindrome.");
        else
            System.out.println(word + " isn't a palindrome.");

    }
}
