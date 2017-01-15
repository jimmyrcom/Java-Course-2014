import java.util.Random;

import java.util.Scanner;

public class Coin

{

 private static final Random randomNumbers = new Random();

 

 // flips a coin many times

 public static void main(String args[])

 {

 Scanner input = new Scanner(System.in);

 

 int heads = 0; 

 int tails = 0;

 

 do

 {

 // display a menu

 System.out.println("Y. Toss Coin");

 System.out.println("N. Exit");

 System.out.print("Choice: ");

 int choice = input.nextInt();

 

 if (choice == 1)

 {

 if (flip())

 heads++;

 else

 tails++;

 System.out.printf("Heads: %d, Tails: %d\n", heads, tails);

 } 

 

 } while (choice != 2); // some reason having red here.

 } 

 // simulate flipping

 public static boolean flip()

 {

 return randomNumbers.nextInt(2) == 1;

 } 

} // end class Coin