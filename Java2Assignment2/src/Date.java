// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Date.java

public class Date
{

	private int month = 0;
	private int day = 0;
	private int year = 0;

	/*** Constructors ***********/
	Date()
	{
		setYear(0);
		setDay(0);
		setMonth(0);
	}

	Date(int month, int day, int year)
	{
		setYear(year);
		setDay(day);
		setMonth(month);
	}

	/*** Getters and Setters ***********/
	public int getYear()
	{
		return year;
	}

	public int getDay()
	{
		return day;
	}

	public int getMonth()
	{
		return month;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	@Override
	public String toString()
	{
		return month + " " + day + " " + year;
	}

	/***
	 * Tester *********** public static void main(String[] args){ Date test =
	 * new Date(); System.out.println(test); } Tester
	 **********/

}