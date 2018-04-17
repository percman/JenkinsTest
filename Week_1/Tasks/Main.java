package DayOneHomework;

public class Main 
{
	public static void main(String[]args) 
	{
		int n = 5;
		int r = 3;
		String string = "penguins";
		String palindrome = "tacocat";
		int a = 10;
		int b = 5;
		
		// Fibonacci
		Fibonacci fib = new Fibonacci();
		System.out.println("Recursive method for Fib of size: " + n + " = " + fib.recursiveFib(n));
		System.out.println("Iterative method for Fib of size: " + n + " = " + fib.iterativeFib(n));
		System.out.println();
		
		// Factorial
		Factorial fact = new Factorial();
		System.out.println("nPr where n = " + n + " and r = " + r + " is: " + (fact.Factorial(n))/(fact.Factorial(n-r)));
		System.out.println("nCr where n = " + n + " and r = " + r + " is: " + (fact.Factorial(n))
				/(fact.Factorial(r) *fact.Factorial(n-r)));
		System.out.println();
		
		// Pascal assuming the top of the pyramid starts at 0
		Pascal pas = new Pascal();
		System.out.println("The " + n + "th row of pascals triangle is: " + pas.generateRow(n));
		System.out.println();

		// Reverse String
		ReverseString rs = new ReverseString();
		System.out.println(string + " reversed is " + rs.reverseString(string));
		System.out.println();
		
		// Palindrome
		Palindrome pal = new Palindrome();
		System.out.println("Is " + string + " a palindrome? " + pal.isPalindrome(string));
		System.out.println("is " + palindrome + " a palindrome? " + pal.isPalindrome(palindrome));
		System.out.println();
		
		// Swappero - this just works out better when I don't have to return an array so no extra class here
		System.out.println("Int a = " + a + " and int b = " + b + " before the swap.");
		a = a * b;
		b = a / b;
		a = a / b;
		System.out.println("Int a = " + a + " and int b = " + b + " after the swap.");
		System.out.println();
		
		// Even or odd - possibly my most genius moment in my whole life
		EvenOrOdd eoo = new EvenOrOdd();
		System.out.println("The number " + n + " is " + eoo.evenOrOdd(n));
		System.out.println("The number " + b + " is " + eoo.evenOrOdd(b));
	}
}
