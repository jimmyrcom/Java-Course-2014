// By Jimmy Ruska

import java.util.Scanner;
import java.util.Random;
public class CoinToss {
	private enum Coin {Heads, Tails};
	private static Scanner input =new Scanner(System.in);

	public static void main(String[] args) {
		double pi =0;
		int heads=0, tails=0;
		
		while(true){
			System.out.print("Would you like to toss a coin? (y/n) ");
			String answer= input.nextLine();
			if (answer.equals("y")==false) break;
			if (flip()==Coin.Heads){
				heads++;
				System.out.println("You flipped Heads!");
			}
			else{
				tails++;
				System.out.println("You flipped Tails!");
			}
		}
		System.out.println("You flipped heads "+heads+" times and tails "+tails+" times!");

	}
	public static Coin flip() {
	    Random rand = new Random();
	    return rand.nextBoolean() ? Coin.Heads : Coin.Tails;	    
	}
}
