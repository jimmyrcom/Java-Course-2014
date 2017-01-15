// James ruska
// checks if your number is a prime
	import java.util.Scanner;
	public class IsPrime {
	    public static Scanner input = new Scanner (System.in);
	    public static void main(String[] args){
	    	// forever
	    	for(;;){
	    		System.out.print("Enter a number or 'q' to quit!\n> ");
	    		String line= input.nextLine().trim();
	    		// prompt output will be a number or 'q'
	    		if (line.equals("q")) return;
	    		//parse string as int. I could have used scanner nextInt but then
	    		// I can't check for 'q'
	    		int number = Integer.parseInt(line);
	    		System.out.println("Is "+number+" a prime? " + isPrime(number));
	    	}
	    	
	    }
	    public static boolean isPrime(int num){
	    	// lol http://www.noulakaz.net/weblog/2007/03/18/a-regular-expression-to-check-for-prime-numbers/
	    	// this is horrible and probably pretty slow but it's short
	    	return !new String(new char[num]).matches(".?|(..+?)\\1+");
	    }
	}
	
