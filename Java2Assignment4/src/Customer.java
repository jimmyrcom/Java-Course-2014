// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Customer.java

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Invoice, Serializable
{

	public enum CustomerType
	{
		STUDENT, FACULTY, GOVERNMENT
	};

	private String name = "";
	private Address address = new Address();
	private int accountNumber = 0;
	private CustomerType cType = CustomerType.STUDENT;
	private ArrayList<Course> courseList = new ArrayList<Course>();

	/*** Constructors ***********/
	Customer()
	{
		setCType(CustomerType.STUDENT);
		setAccountNumber(0);
		setAddress(new Address());
		setName("");
		setCourseList(new ArrayList<Course>());
	}

	Customer(String name, Address address, int accountNumber)
	{
		setCType(CustomerType.STUDENT);
		setAccountNumber(accountNumber);
		setAddress(address);
		setName(name);
		setCourseList(new ArrayList<Course>());
	}

	/*** Methods ***********/
	public void addCourse(Course course)
	{
		courseList.add(course);
	}

	public String createInvoice() throws CustomerNotEnrolledException
	{
		if (courseList.size() == 0)
			throw new CustomerNotEnrolledException();

		double total = 0;
		for (Course course : courseList)
		{
			total += course.calculateCharge(cType);
		}
		double baseCost = 0;
		switch (cType)
		{
		case STUDENT:
			baseCost = 25;
			break;
		case FACULTY:
			baseCost = 50;
			break;
		case GOVERNMENT:
			baseCost = 35;
			break;
		}
		total += baseCost;
		// Course.calculateCharge()
		return String.format("%-10s %-20s $%.2f\n", name + ":", accountNumber,
				total);
	}

	/*** Getters and Setters ***********/
	public String getCourseList()
	{
		return courseList.toString();
	}

	public ArrayList<Course> getCourseListReal()
	{
		return courseList;
	}

	public CustomerType getCType()
	{
		return cType;
	}

	public Address getAddress()
	{
		return address;
	}

	public String getName()
	{
		return name;
	}

	public void setCType(CustomerType cType)
	{
		this.cType = cType;
	}

	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAccountNumber()
	{
		return accountNumber;
	}

	public void setCourseList(ArrayList<Course> courseList)
	{
		this.courseList = courseList;
	}

	@Override
	public String toString()
	{
		return "name= " + "\"" + name + "\"" + ", address = " + address
				+ ", accountNumber = " + accountNumber + ", CustomerType = "
				+ cType + ", courseList = \r\n" + courseList + " ";
	}

	/***
	 * Tester *********** public static void main(String[] args){ Customer test
	 * = new Customer(); System.out.println(test); } Tester
	 **********/

}