import java.io.Serializable;

// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Course.java

public class Course implements Serializable
{

	public enum CourseType
	{
		PROGRAMMING, MATHEMATICS, PHOTOGRAPHY, MUSIC, PAINTING, MISC
	};

	private String title = "";
	private String instructor = "";
	private double price = 0.0;
	private CourseType cType = CourseType.PROGRAMMING;
	private Date startDate = new Date();
	private Date endDate = new Date();

	/*** Constructors ***********/
	Course()
	{
		setEndDate(new Date());
		setStartDate(new Date());
		setCType(CourseType.PROGRAMMING);
		setPrice(0.0);
		setInstructor("");
		setTitle("");
	}

	Course(String title, String instructor, double price, Date startDate,
			Date endDate)
	{
		setEndDate(endDate);
		setStartDate(startDate);
		setCType(CourseType.PROGRAMMING);
		setPrice(price);
		setInstructor(instructor);
		setTitle(title);
	}

	/*** Methods ***********/
	public double calculateCharge(Customer.CustomerType c)
	{
		double total = 0;
		switch (this.cType)
		{
		case PROGRAMMING:
		case MATHEMATICS:
			total = 99 + price;
			break;
		case MISC:
			total = 39 + price;
			break;
		default:
			total = 59 + price;
		}
		return total;
	}

	/*** Getters and Setters ***********/
	public Date getEndDate()
	{
		return endDate;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public CourseType getCType()
	{
		return cType;
	}

	public double getPrice()
	{
		return price;
	}

	public String getInstructor()
	{
		return instructor;
	}

	public String getTitle()
	{
		return title;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public void setCType(CourseType cType)
	{
		this.cType = cType;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public void setInstructor(String instructor)
	{
		this.instructor = instructor;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Override
	public String toString()
	{
		return "Course(title:String = " + "\"" + title + "\""
				+ ", instructor:String = " + "\"" + instructor + "\""
				+ ", price:double = " + price + ", cType:CourseType = " + cType
				+ ", startDate:Date = " + startDate + ", endDate:Date = "
				+ endDate + ")";
	}

	/***
	 * Tester *********** public static void main(String[] args){ Course test =
	 * new Course(); System.out.println(test); } Tester
	 **********/

}