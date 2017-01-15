// James Ruska
// This program insults you
import java.util.Scanner;
public class BMI {
	public static Scanner input = new Scanner (System.in);
	// I think this is pretty self documenting1
	public static void main(String[] args){
		double weight, height, bmi;
		weight=ask("How much do you weigh?");
		height=ask("Enter your height in inches");
		bmi= (weight*703) / (height*height);
		if (bmi>25) System.out.println("Overweight");
		else if (bmi < 18.5) System.out.println("Under weight");
		else System.out.println("Optimal weight");
	}
	// prompt for input and return a double
	public static double ask(String question){
		System.out.print(question + "\n> ");
		return input.nextDouble();
	}
}

