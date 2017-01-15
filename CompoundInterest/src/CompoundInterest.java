import java.util.Scanner;
public class CompoundInterest 
{
   private static Scanner input =new Scanner(System.in);
   public static void main( String args[] )
   {
	  // amount on deposit at end of each year
      double amount =ask("Enter amount on deposit at the end of the year");
       // initial amount before interest	
      double principal = ask("Enter initial amount before interest");
      // interest rate	
      double rate =  ask("Enter interest rate");
      // Years
      int years = (int)ask("Enter number of years");
      
      System.out.printf( "Input Values\n---------------\n%-20s: %f\n","End of Year Amount", amount);
      System.out.printf( "%-20s: %f\n","Principal", principal);
      System.out.printf( "%-20s: %f\n","Interest rate", rate);
      System.out.printf( "%-20s: %d\n----------------\n\n","Years", years);

      
      // display headers
      System.out.printf( "%s%20s\n", "Year", "Amount on deposit" );

      // calculate amount on deposit for each of ten years
      for ( int year = 1; year <= years; year++ ) 
      {
         // calculate new amount for specified year
         amount = principal * Math.pow( 1.0 + rate, year );

         // display the year and the amount
         System.out.printf( "%4d%,20.2f\n", year, amount );
      } // end for
   } // end main
	public static double ask(String question){
		System.out.print(question + "\n> ");
		return input.nextDouble();
	}
} // end class Interest