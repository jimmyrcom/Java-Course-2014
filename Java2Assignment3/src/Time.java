// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Time.java

public class Time
{

	private int hours = 0;
	private int minutes = 0;

	/*** Constructors ***********/
	Time()
	{
		setMinutes(0);
		setHours(0);
	}

	Time(int hours, int minutes)
	{
		setMinutes(minutes);
		setHours(hours);
	}

	/*** Getters and Setters ***********/
	public int getHours()
	{
		return hours;
	}

	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}

	public void setHours(int hours)
	{
		this.hours = hours;
	}

	public int getMinutes()
	{
		return minutes;
	}

	@Override
	public String toString()
	{
		return hours + " " + minutes;
	}

	/***
	 * Tester *********** public static void main(String[] args){ Time test =
	 * new Time(); System.out.println(test); } Tester
	 **********/

}