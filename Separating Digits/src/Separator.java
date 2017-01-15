// By James Ruska
// 2.3 Digit Separator

import java.util.Scanner;
public class Separator {
	public static Scanner input = new Scanner (System.in);
	public static void main(String[] args) {
		Integer number = getNumber();
		String n1 = number.toString();
		for (int i = 0; i < n1.length(); i++) System.out.printf("%-4s",n1.charAt(i));
	}
	// helper function to add text to nextDouble
	public static Integer getNumber(){
		System.out.print("\nPlease enter a number: ");
		return input.nextInt();
	}
}
