// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CourseFiles.java

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.IllegalFormatException;

public final class CreateFiles implements Serializable
{

	public final static String customerFile = "customers.txt";
	public final static String courseFile = "courses.txt";

	private static ArrayList<Customer> customerList = new ArrayList<Customer>();

	private static final String customerData = "Jones 786 Cooper Arlington Texas 76019 12345 student"
			+ "\nSmith 921 Bowen Arlington Texas 76006 65489 faculty"
			+ "\nBarker 621 Lancaster FortWorth Texas 76090 54367 student"
			+ "\nCallan 978 Lowe Dallas Texas 75009 98712 faculty"
			+ "\nWillis 123 King Tulas Oklahoma 56909 25968 government"
			+ "\nJames 109 Baker Dallas Texas 75010 73674 faculty"
			+ "\nParsons 4523 Azalea Burleson Texas 76134 26789 government";

	private static final String coursesData = "Online Java1 Davis 125.00 1 1 2015 2 1 2015 programming UTA true 12 Jones"
			+ "\nOnline Java2 Davis 125.00 2 2 2015 3 1 2015 programming UTA true 12 Jones"
			+ "\nOnline RelieveStress Jones 35.00 3 2 2015 3 31 2015 misc none false 5 Smith"
			+ "\nOnline Painter2015 Vikram 59.00 3 2 2015 3 31 2015 painting TCU false 8 Parsons"
			// +
			// "\nOnline Java1 Davis 125.00 1 1 2015 2 1 2015 programming UTA true 12 Jones"
			+ "\nInclass CanonPictures Long 75.00 1 15 2015 2 3 2015 photography COB142 17 30 18 50 Jones"
			+ "\nInclass PlaythePiano Smith 40.00 4 1 2015 5 1 2015 music UH105 13 30 15 30 Smith"
			+ "\nInclass PlaythePiano Smith 40.00 4 1 2015 5 1 2015 music UH105 13 30 15 30 Willis"
			+ "\nInclass Acting101 Simon 69.00 4 5 2015 6 1 2015 misc CH106 8 0 10 0 Parsons";

	public static void main(String[] args)
	{
		// generate the customer arraylist from a customerData string and add to
		// a customerList
		generateCustomers(customerData, customerList); // ArrayList<Customer>
		generateCourses(coursesData, customerList); // ArrayList<Customer>

		// Write to files
		CreateFiles.writeCustomers(customerList);
		CreateFiles.writeCourses(customerList);
	}

	// WriteFiles will have methods writeCustomers and writeCourses.
	public static void writeCustomers(ArrayList<Customer> customers)
	{
		try
		{
			Formatter output = new Formatter(customerFile);
			for (Customer customer : customers)
				output.format("%s", customerToString(customer));
			output.close();
		} catch (IllegalFormatException | FormatterClosedException
				| FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private static String customerToString(Customer customer)
	{
		Address address = customer.getAddress();
		return customer.getName() + " " + address.getStreet() + " "
				+ address.getCity() + " " + address.getState() + " "
				+ address.getZip() + " " + customer.getAccountNumber() + " "
				+ customer.getCType().toString().toLowerCase() + "\n";
	}

	public static void writeCourses(ArrayList<Customer> customers)
	{
		try
		{
			Formatter output = new Formatter(courseFile);
			for (Customer customer : customers)
				for (Course course : customer.getCourseListReal())
					output.format("%s",
							courseToString(course) + " " + customer.getName()
									+ "\n");
			output.close();
		} catch (IllegalFormatException | FormatterClosedException
				| FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private static String courseToString(Course course)
	{
		if (course instanceof InClassCourse)
		{
			InClassCourse c = (InClassCourse) course;
			return "Inclass " + c.getTitle() + " " + c.getInstructor() + " "
					+ String.format("%.2f", c.getPrice()) + " "
					+ c.getStartDate() + " " + c.getEndDate() + " "
					+ c.getCType().toString().toLowerCase() + " " + c.getRoom()
					+ " " + c.getStartTime() + " " + c.getEndTime();
		} else
		{
			OnlineCourse c = (OnlineCourse) course;
			return "Online " + c.getTitle() + " " + c.getInstructor() + " "
					+ String.format("%.2f", c.getPrice()) + " "
					+ c.getStartDate() + " " + c.getEndDate() + " "
					+ c.getCType().toString().toLowerCase() + " "
					+ c.getExamProctor() + " " + c.getVideo() + " "
					+ c.getNumberOfChapters();
		}

	}

	public static void generateCustomers(String data, ArrayList<Customer> cList)
	{
		String[] customers = data.split("\n");
		for (int i = 0; i < customers.length; i++)
			generateCustomer(customers[i], cList);
	}

	public static void generateCustomer(String data, ArrayList<Customer> cList)
	{
		String[] words = data.split(" ");
		Customer c = new Customer(words[0], new Address(words[1] + " "
				+ words[2], words[3], words[4], toInt(words[5])),
				toInt(words[6]));
		c.setCType(Customer.CustomerType.valueOf((words[7].toUpperCase())));
		cList.add(c);
	}

	public static void generateCourses(String data, ArrayList<Customer> cList)
	{
		String[] customers = data.split("\n");
		for (int i = 0; i < customers.length; i++)
			generateCourse(customers[i], cList);
	}

	public static void generateCourse(String data, ArrayList<Customer> cList)
	{
		String[] words = data.split(" ");
		Course course;
		String customer;

		if (words[0].equals("Online"))
		{
			course = new OnlineCourse(
					words[1],
					words[2],
					toDouble(words[3]),
					new Date(toInt(words[4]), toInt(words[5]), toInt(words[6])),
					new Date(toInt(words[7]), toInt(words[8]), toInt(words[9])),
					words[11], Boolean.parseBoolean(words[12]),
					toInt(words[13]));
			customer = words[14];
		} else
		{
			course = new InClassCourse(
					words[1],
					words[2],
					toDouble(words[3]),
					new Date(toInt(words[4]), toInt(words[5]), toInt(words[6])),
					new Date(toInt(words[7]), toInt(words[8]), toInt(words[9])),
					words[11], new Time(toInt(words[12]), toInt(words[13])),
					new Time(toInt(words[14]), toInt(words[15])));
			customer = words[16];
		}
		course.setCType(Course.CourseType.valueOf(words[10].toUpperCase()));
		for (int i = 0; i < cList.size(); i++)
		{
			if (cList.get(i).getName().equals(customer))
			{
				cList.get(i).addCourse(course);
				break;
			}
		}
	}

	private static int toInt(String stringNum)
	{
		return Integer.parseInt(stringNum);
	}

	private static double toDouble(String stringNum)
	{
		return Double.parseDouble(stringNum);
	}

}
