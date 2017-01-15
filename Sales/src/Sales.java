//James Ruska
import java.util.Scanner;


public class Sales {
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		double data[][] = new double[5][4];
		
		while (true){
			int person = Integer.parseInt(ask("What is the employee ID"))-1;
			int product = Integer.parseInt(ask("What is the product number"))-1;
			double money = Double.parseDouble(ask("What is the amount in sales"));
			data[person][product]+=money;
			if(ask("Add another employee? y/n").equals("n")) break;
		}
		System.out.printf("%40s %20s %20s %20s  =%7s"
				,"Employee 1", "Employee 2", "Employee 3", "Employee 4", "Total");
		
		double ptotals=0;
		double[] eTotals = new double[4];
		for(int i=0; i<5; i++){
			System.out.printf("\n%-20s", "Product "+i);
			double ptotal=0;
			for (int k=0; k<4; k++){
				eTotals[k]+=data[i][k];
				ptotal+=data[i][k];
				System.out.printf("%20.2f ", data[i][k]);
			}
			ptotals+=ptotal;
			System.out.printf(" =%7.2f", ptotal);
		}
		System.out.printf("\n%-20s%20.2f %20.2f %20.2f %20.2f  =%7.2f"
				, "Total", eTotals[0], eTotals[1], eTotals[2], eTotals[3],
				ptotals);
		
		
	}
	public static void print(String Str){
		System.out.println("\n"+Str);
	}
	public static String ask(String question){
		System.out.print(question + "\n> ");
		return input.nextLine();
	}
}
