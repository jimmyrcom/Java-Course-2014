// By Jimmy Ruska

import java.util.Scanner;
public class PI {

	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		double pi =0;
		System.out.print("Enter precision to calculate PI: ");
		int precision= input.nextInt();
		
		// we start at 1 and we're incrementing by 2 so precision*2+1
		for (int i=1; i< (precision*2+1); i+=2)
			pi+= (4.0/i) * (((i/2) % 2) == 1 ? -1 : 1);
		
		System.out.println("\nPI: "+pi);
	}
}
