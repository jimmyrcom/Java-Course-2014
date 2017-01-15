import java.util.Scanner;
public class Badgrade {
		public static Scanner input= new Scanner(System.in);
	public static void main(String[] args) {
		for (int i=0; i<4; i++){
			String name="";
			if (i==0) name="sw";
			else if (i==1) name="se";
			else if (i==2) name="ne";
			else if (i==3) name="nw";
			double sales = getInput("Enter sales for "+name);
			double tax = getInput("Enter avg tax for "+name);
			tax = tax/100;
			System.out.printf("Sales is %f. Avg tax is %f. Sales with tax is %f.",
					sales, tax, sales+(tax*sales));
		}
		System.out.printf("\nEnter 'q' to quit or enter to continue: ");
		String answer=input.nextLine();
		if (answer.equals("q") == false) main(args);
	}
	public static double getInput(String question){
		System.out.printf("\n%s: ",question);
		return input.nextDouble();
	}
}

