// Bank Charges and service fees
// This question was kinda confusing to me so I hope I did this right
// The method endMonth() calculates all service fees and charges.
// I didn't know whether to remove $15 before or after taking away the $10 monthly service fee
// James Ruska
import java.util.Scanner;

public class BankCharges
{

	public double balance = 0;
	public int checksWritten = 0;
	public double serviceFees = 0;

	// constructor
	public BankCharges(double balance1, int checksWritten1)
	{
		balance = balance1;
		checksWritten = checksWritten1;
	}

	public void endMonth()
	{
		// reset the value
		serviceFees = 0;
		if (balance < 400)
			serviceFees += 15; // too little money
		serviceFees += 10; // cost of service
		double cost;
		// cost depending on checks written
		// this is assuming you can write as many checks as you want and the
		// bank charges
		// you only at the end of the month
		if (checksWritten > 60)
			cost = 0.04;
		else if (checksWritten > 40)
			cost = 0.06;
		else if (checksWritten > 20)
			cost = 0.08;
		else
			cost = 0.10;
		serviceFees += cost * checksWritten; // cost of checks
		balance -= serviceFees;
	}

	public double getBalance()
	{
		return balance;
	}

	public double getServiceFees()
	{
		return serviceFees;
	}

	public static void main(String[] args)
	{
		// set balance to 300 and say I wrote 30 checks
		BankCharges someguy = new BankCharges(300, 50);
		// simulates the end of the month
		someguy.endMonth();
		// prints out the amount of service fees
		System.out.println(someguy.getServiceFees());
	}

}
