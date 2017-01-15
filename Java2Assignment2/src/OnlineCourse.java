// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      OnlineCourse.java

public class OnlineCourse extends Course
{

	private String examProctor = "";
	private boolean video = false;
	private int numberOfChapters = 0;

	/*** Constructors ***********/
	OnlineCourse()
	{
		setNumberOfChapters(0);
		setVideo(false);
		setExamProctor("");
	}

	OnlineCourse(String title, String instructor, double price, Date startDate,
			Date endDate, String examProctor, boolean video,
			int numberOfChapters)
	{
		super(title, instructor, price, startDate, endDate);
		setNumberOfChapters(numberOfChapters);
		setVideo(video);
		setExamProctor(examProctor);
	}

	/*** Methods ***********/
	@Override
	public double calculateCharge(Customer.CustomerType c)
	{
		switch (c)
		{
		case FACULTY:
			return super.calculateCharge(c) + 25;
		case STUDENT:
			return super.calculateCharge(c) + 20;
		default:
			return super.calculateCharge(c);
		}
	}

	/*** Getters and Setters ***********/
	public int getNumberOfChapters()
	{
		return numberOfChapters;
	}

	public boolean getVideo()
	{
		return video;
	}

	public String getExamProctor()
	{
		return examProctor;
	}

	public void setNumberOfChapters(int numberOfChapters)
	{
		this.numberOfChapters = numberOfChapters;
	}

	public void setVideo(boolean video)
	{
		this.video = video;
	}

	public void setExamProctor(String examProctor)
	{
		this.examProctor = examProctor;
	}

	@Override
	public String toString()
	{
		return "OnlineCourse " + getTitle() + " " + getInstructor() + " "
				+ getPrice() + " " + getCType() + " " + getStartDate() + " "
				+ getEndDate() + " " + examProctor + " " + video + " "
				+ numberOfChapters;
	}

	/***
	 * Tester *********** public static void main(String[] args){ OnlineCourse
	 * test = new OnlineCourse(); System.out.println(test); } Tester
	 **********/

}