// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CourseTest.java

import java.io.Serializable;

import javax.swing.JFrame;

public class CustomerTest implements Serializable
{

	public static void main(String[] args)
	{
		new DatabaseMethods(); // just initialize
		// System.out.println(Customer.CustomerType.valueOf("STUDENT"));
		// DatabaseMethods.addCustomer("potato", "7300", 999, "Student");
		// System.out.println(DatabaseMethods.customerExists("potato",555));
		// System.out.println(DatabaseMethods.customerAlreadyEnrolled("1",
		// "Java 2"));
		CourseGUI gui = new CourseGUI("Course GUI");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(650, 500);
		gui.setVisible(true);
		gui.setLocationRelativeTo(null);
	}

}