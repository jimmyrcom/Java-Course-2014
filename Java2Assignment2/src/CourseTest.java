// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CourseTest.java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CourseTest
{
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();

	public static void main(String[] args)
	{
		// Read from files
		readCustomers();
		readCourses();
		

		generateInvoice();
	}

	/*** Constructors ***********/
	CourseTest()
	{
		setCustomerList(new ArrayList<Customer>());
	}

	CourseTest(ArrayList<Customer> customerList)
	{
		setCustomerList(customerList);
	}

	/*** Helper Classes ***********/
	private interface FakeLambda
	{

		public void process(String data);
	}

	/*** Methods ***********/
	// I'm using FakeLambda just as a function container so I don't have to
	// duplicate code.
	private static void readCourses()
	{
		FakeLambda action = new FakeLambda()
		{

			public void process(String data)
			{
				CreateFiles.generateCourse(data, customerList);
			}
		};
		read(action, CreateFiles.courseFile, 0);
	}

	private static void readCustomers()
	{
		FakeLambda action = new FakeLambda()
		{

			public void process(String data)
			{
				CreateFiles.generateCustomer(data, customerList);
			}
		};
		read(action, CreateFiles.customerFile, 0);
	}

	private static void read(FakeLambda action, String file, int tries)
	{
		try
		{
			Scanner input = new Scanner(new File(file));
			read(input, action);
		} catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			// try again in src/ path if using an IDE
			if (tries == 0)
				read(action, "src/" + file, tries + 1);
		}
	}

	private static void read(Scanner input, FakeLambda action)
	{
		try
		{
			while (input.hasNext())
			{
				action.process(input.nextLine());
			}

		} catch (NoSuchElementException elementException)
		{
			System.err.println("File improperly formed.");
			input.close();
		} catch (IllegalStateException stateException)
		{
			System.err.println("Error reading from file.");
			System.exit(1);
		} // end catch

	}

	private static void generateInvoice()
	{
		String alert = String.format("%-10s %-20s %s\n", "Name", "Account",
				"Charge");
		for (Customer person : customerList)
		{
			try
			{
				person.createInvoice();
				alert += person.createInvoice();
				// System.out.println(person + "\r\n");
			} catch (CustomerNotEnrolledException e)
			{
				System.out.println("Customer " + person.getName()
						+ " is not enrolled!");
			}
		}
		JOptionPane.showMessageDialog(null, alert);

	}



	/*** Getters and Setters ***********/
	public ArrayList<Customer> getCustomerList()
	{
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList)
	{
		CourseTest.customerList = customerList;
	}

	public void addToCustomerList(Customer... customers)
	{
		for (Customer customer : customers)
			customerList.add(customer);
	}

	@Override
	public String toString()
	{
		return "CourseTest(customerList:ArrayList<Customer> = " + customerList
				+ ")";
	}

}
/*
 * $-customerList:ArrayList<Customer> $-readCustomers() // reads customers.txt,
 * updates customerList $-readCourses() // reads courses, adds to customerList
 * $-generateInvoice() // handles CustomerNotEnrolled
 */