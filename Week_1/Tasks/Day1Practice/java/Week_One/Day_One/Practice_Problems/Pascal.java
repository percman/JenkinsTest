package Week_One.Day_One.Practice_Problems;
// Run Class User for console application.
public class Pascal {
    public static void pascal(int rows){
        for(int i = 0; i < rows; i++)
        {
            for(int k = rows; k > i; k--)
            {
                System.out.print(" ");
            }
            int number = 1;
            for(int j = 0; j <= i; j++)
            {
                System.out.print(number + " ");
                number = number * (i - j) / (j + 1);
            }

            System.out.println();
        }
    }
}
