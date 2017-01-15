// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Address.java

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class AddCustomer extends JPanel
{

	public static ArrayList<Customer> customerList = new ArrayList<Customer>();

	private FakeMap map = new FakeMap();

	public AddCustomer()
	{
		// super(name);
		setLayout(new GridLayout(0, 2));

		// readCourses(courseList);

		addText("name", "Enter Name");
		addText("street", "Enter Street");
		addText("city", "Enter City");
		addText("state", "Enter State");
		addText("zip", "Enter Zip");
		addText("account", "Enter Account Number");
		addRadio("type", "Enter Type of Customer", "STUDENT", "FACULTY",
				"GOVERNMENT");

		addButton("submit", "Click Submit when Done", "Submit",
				new ActionListener()
				{

					public void actionPerformed(ActionEvent e)
					{
						if (CourseGUI.errorEmpty(map.get("name"))
								|| CourseGUI.errorEmpty(map.get("street"))
								|| CourseGUI.errorEmpty(map.get("city"))
								|| CourseGUI.errorEmpty(map.get("state"))
								|| CourseGUI.errorInt(map.get("zip"))
								|| CourseGUI.errorInt(map.get("account")))
						{
							System.out.println("Invalid Data!");
							return;
						}
						if (get("type").isEmpty())
						{
							CourseGUI.message("Select a student type!");
							return;
						}

						int x = DatabaseMethods.customerExists(
								(String) get("name"),
								Integer.parseInt(get("account")));
						if (x == -1)
						{
							DatabaseMethods.addCustomer(get("name"),
									get("street") + " " + get("city") + " "
											+ get("state"),
									Integer.parseInt(get("account")),
									get("type"));
							try
							{
								addCustomer();
							} catch (CustomerAlreadyExistsException e1)
							{}
							clearFields();
							CourseGUI.message("Customer added!");

						} else
						{
							CourseGUI
									.message("Customer already exists with the same name or account number! ID: "
											+ x);
						}
						/*
						 * try { addCustomer();
						 * CourseGUI.message("Customer added!"); clearFields();
						 * } catch (CustomerAlreadyExistsException err) {
						 * CourseGUI.message("Customer already exists!"); }
						 */
					}
				});
	}

	public void addRadio(String name, String label, String... radios)
	{
		add(new JLabel("     " + label));
		ButtonGroup group = new ButtonGroup();
		JPanel frame = new JPanel();
		for (String r : radios)
		{
			JRadioButton radio = new JRadioButton(r);
			group.add(radio);
			frame.add(radio);
		}
		add(frame);
		map.put(name, group);
	}

	public void addText(String name, String label)
	{
		JTextField text = new JTextField(20);
		text.setToolTipText(label);
		add1(name, label, text);
	}

	public void addButton(String name, String label, String text,
			ActionListener act)
	{
		JButton button = new JButton(text);
		button.addActionListener(act);
		add1(name, label, button);
	}

	public void add1(String name, String label, Container widget)
	{
		map.put(name, widget);
		add(new JLabel("     " + label + " "));
		add(widget);
	}

	public void clearFields()
	{
		map.clearValues();
	}

	public void addCustomer() throws CustomerAlreadyExistsException
	{
		String name = get("name").toLowerCase();
		for (Customer c : customerList)
		{
			if (c.getName().toLowerCase().equals(name))
				throw new CustomerAlreadyExistsException();
		}
		Customer c = new Customer(get("name"), new Address(get("street"),
				get("city"), get("state"), parseInt(get("zip"))),
				parseInt(get("account")));
		c.setCType(Customer.CustomerType.valueOf(get("type").toUpperCase()));
		customerList.add(c);

	}

	public String get(String key)
	{
		return map.getValue(key);
	}

	public static int parseInt(String str)
	{
		try
		{
			return Integer.parseInt(str);
		} catch (Exception e)
		{
			return 0;
		}
	}

	public static void generateInvoice()
	{
		String alert = String.format("%-10s %-20s %s\n", "Name", "Account",
				"Charge");
		for (Customer person : customerList)
		{
			try
			{
				alert += person.createInvoice();
				System.out.println(person + "\r\n");
			} catch (CustomerNotEnrolledException e)
			{
				System.out.println("Customer " + person.getName()
						+ " is not enrolled!");
			}
		}
		JOptionPane.showMessageDialog(null, alert);

	}

	/*** Methods ***********/
	static void readCourses(ArrayList<Course> courseList)
	{
		
		try
		{
			Scanner input = new Scanner(new File("courses.txt"));
			while (input.hasNext())
			{
				String str = input.nextLine();
				String[] words = str.split(";");
				Course course;
				// System.out.println(str);
				if (words[0].equals("Online"))
				{
					course = new OnlineCourse(words[1], words[2],
							Double.parseDouble(words[3]), new Date(
									parseInt(words[4]), parseInt(words[5]),
									parseInt(words[6])), new Date(
									parseInt(words[7]), parseInt(words[8]),
									parseInt(words[9])), words[11],
							Boolean.parseBoolean(words[12]),
							parseInt(words[13]));
				} else
				{
					course = new InClassCourse(words[1], words[2],
							Double.parseDouble(words[3]), new Date(
									parseInt(words[4]), parseInt(words[5]),
									parseInt(words[6])), new Date(
									parseInt(words[7]), parseInt(words[8]),
									parseInt(words[9])), words[11], new Time(
									parseInt(words[12]),
									Integer.parseInt(words[13])), new Time(
									parseInt(words[14]), parseInt(words[15])));
				}
				course.setCType(Course.CourseType.valueOf(words[10]
						.toUpperCase()));
				courseList.add(course);
			}
			input.close();
		} catch (IOException e)
		{
			try
			{
				ResultSet r = DatabaseMethods.connection.prepareStatement("SELECT * FROM Customer").executeQuery();
				String type = r.getString(8);
				Course course;
					course = new Course();
					course.setTitle(r.getString(2));
				courseList.add(course);

			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			System.err.println("Error opening file.");
		}
	}

}
