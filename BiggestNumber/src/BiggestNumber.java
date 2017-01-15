// By James Ruska
// 2.7  -- 3 input numbers
import java.util.Scanner;
public class BiggestNumber {
	public static Scanner input = new Scanner (System.in);
	public static void main(String[] args) {
		int[] numbers = new int[3];
		int sum = 0, product=1;
		for (int i = 0; i<3; sum+=numbers[i], product*=numbers[i], i++)
			numbers[i] = getNumber();
		
		// we can put first second and third numbers into a,b,c to do logic instead of call
		// min/max function
		int a = numbers[0], b= numbers[1], c=numbers[2];
		System.out.printf("Sum: %d\nAverage: %d\nProduct: %d\nSmallest: %d\nLargest: %d"
					,sum
					,sum / 3
					,product
					,a < b ? (a < c ? a : c) : (b < c ? b : c) 
				    ,a > b ? (a > c ? a : c) : (b > c ? b : c));		
	}
	// helper function to add text to nextDouble
	public static int getNumber(){
		System.out.print("\nPlease enter a number: ");
		return input.nextInt();
	}
}
