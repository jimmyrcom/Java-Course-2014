// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CustomerTest.java

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CustomerTest
{

	static private ArrayList<Customer> customerList = new ArrayList<Customer>();

	public static void main(String[] args)
	{

		Customer first = new Customer("Jones", new Address("786 Cooper",
				"Arlington", "Texas", 76019), 12345);
		Customer second = new Customer("Smith", new Address("921 Bowen",
				"Arlington", "Texas", 76006), 65489);
		Customer third = new Customer("Willis", new Address("123 Love Lane",
				"Dallas", "Texas", 75550), 27589);

		first.setCType(Customer.CustomerType.STUDENT);
		second.setCType(Customer.CustomerType.FACULTY);
		third.setCType(Customer.CustomerType.GOVERNMENT);

		Course java1 = new OnlineCourse("Java 1", "Davis", 125, new Date(1, 1,
				2015), new Date(2, 1, 2015), "UTA", true, 12);
		java1.setCType(Course.CourseType.PROGRAMMING);
		Course java2 = new OnlineCourse("Java 2", "Davis", 125, new Date(2, 2,
				2015), new Date(3, 1, 2015), "UTA", true, 12);
		java2.setCType(Course.CourseType.PROGRAMMING);
		Course photography = new InClassCourse("Canon Pictures PHOTOGRAPHY",
				"Long", 75, new Date(1, 15, 2015), new Date(2, 3, 2015),
				"COB 142", new Time(17, 30), new Time(18, 50));
		photography.setCType(Course.CourseType.PHOTOGRAPHY);

		first.addCourse(java1);
		first.addCourse(java2);
		first.addCourse(photography);

		Course stress = new OnlineCourse("Relieve Stress", "Jones", 35,
				new Date(3, 2, 2015), new Date(3, 31, 2015), "None", false, 5);
		stress.setCType(Course.CourseType.MISC);
		Course piano = new InClassCourse("Play the Piano", "Smith", 40,
				new Date(4, 1, 2015), new Date(5, 1, 2015), "UH 105", new Time(
						13, 30), new Time(15, 30));
		piano.setCType(Course.CourseType.MUSIC);

		second.addCourse(stress);
		second.addCourse(piano);

		third.addCourse(stress);
		third.addCourse(piano);

		addCustomers(first, second, third);

		String alert = String.format("%-10s %-20s %s\n", "Name", "Account",
				"Charge");
		for (Customer person : customerList)
		{
			alert += person.createInvoice();
			System.out.println(person + "\r\n");
		}

		JOptionPane.showMessageDialog(null, alert);
	}

	static public void addCustomers(Customer... customers)
	{
		for (Customer customer : customers)
			customerList.add(customer);
	}

	/*** Constructors ***********/
	CustomerTest(ArrayList<Customer> customerList)
	{
		setCustomerList(customerList);
	}

	CustomerTest()
	{
		setCustomerList(new ArrayList<Customer>());
	}

	/*** Getters and Setters ***********/
	public ArrayList<Customer> getCustomerList()
	{
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList)
	{
		CustomerTest.customerList = customerList;
	}

	@Override
	public String toString()
	{
		return "CustomerTest(customerList:ArrayList<Customer> = "
				+ customerList + ")";
	}

}
