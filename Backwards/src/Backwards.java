// This class reverses text
import java.util.Scanner;
public class Backwards {
	public static Scanner input = new Scanner (System.in);
	// what I do is loop through the string backwards prepending into an accumulator string
	// that starts empty. Maybe not most efficient way but it works
	public static String reverse(String word){
		String output="";
		for (int i=word.length()-1; i>-1; i--){
			output=output+word.charAt(i);
		}
		return output;
	}
	// Prompt and reverse string
    public static void main(String[] args){
    	String input=ask("Enter some text and I will reverse it.");
    	System.out.println(reverse(input));
    }
    // prompt function
	public static String ask(String question){
		System.out.print(question + "\n> ");
		return input.nextLine();
	}
}
