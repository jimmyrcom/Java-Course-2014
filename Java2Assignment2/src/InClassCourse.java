// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      InClassCourse.java

public class InClassCourse extends Course
{

	private String room = "";
	private Time startTime = new Time();
	private Time endTime = new Time();

	/*** Constructors ***********/
	InClassCourse()
	{
		setEndTime(new Time());
		setStartTime(new Time());
		setRoom("");
	}

	InClassCourse(String title, String instructor, double price,
			Date startDate, Date endDate, String room, Time startTime,
			Time endTime)
	{
		super(title, instructor, price, startDate, endDate);
		setEndTime(endTime);
		setStartTime(startTime);
		setRoom(room);
	}

	/*** Methods ***********/
	@Override
	public double calculateCharge(Customer.CustomerType c)
	{
		if (c == Customer.CustomerType.FACULTY)
			return super.calculateCharge(c) + 5;
		return super.calculateCharge(c);
	}

	/*** Getters and Setters ***********/
	public Time getEndTime()
	{
		return endTime;
	}

	public Time getStartTime()
	{
		return startTime;
	}

	public String getRoom()
	{
		return room;
	}

	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}

	public void setStartTime(Time startTime)
	{
		this.startTime = startTime;
	}

	public void setRoom(String room)
	{
		this.room = room;
	}

	@Override
	public String toString()
	{
		return "InClassCourse " + getTitle() + " " + getInstructor() + " " + getPrice() + " "
				+ getCType() + " " + getStartDate() + " " + getEndDate() + " "
				+ room + " " + startTime + " " + endTime;
	}

	/***
	 * Tester *********** public static void main(String[] args){ InClassCourse
	 * test = new InClassCourse(); System.out.println(test); } Tester
	 **********/

}