// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CourseTest.java

import javax.swing.*;
import javax.swing.text.JTextComponent;

import FakeMap.Entry;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class CourseGUI1 extends JFrame
{

	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	private static ArrayList<Course> courseList = new ArrayList<Course>();
	private static String[] comboArr;
	private static HashMap<String, Object> map= new HashMap<String, Object>();

	private static JComboBox<String> combo;
	private static ButtonGroup group = new ButtonGroup();
	private static JPanel panel = new JPanel();
	private static JRadioButton student = new JRadioButton("STUDENT");
	private static JRadioButton faculty = new JRadioButton("FACULTY");
	private static JRadioButton government = new JRadioButton("GOVERNMENT");
	private static JButton submit = new JButton("Submit");
	private static JButton finish = new JButton("Finish");

	
	public CourseGUI1()
	{
		setLayout(new GridLayout(10, 2));
		readCourses(courseList);
		
		addText("name", "Enter Name");
		addText("street", "Enter Street");
		addText("city", "Enter City");
		addText("state", "Enter State");
		addText("zip", "Enter Zip");
		addText("account", "Enter Account Number");
		addRadio("type", "Enter Type of Customer", "STUDENT", "FACULTY",
				"GOVERNMENT");
		JComboBox<String> combo = new JComboBox<String>(comboArr);
		combo.setMaximumRowCount(3);
		add1("course", "Select a course", combo);
		
		combo = new JComboBox<String>(comboArr);

		submit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				buttonAction();
			}
		});

		finish.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				generateInvoice();
			}
		});

	}
	
	public String get(String key)
	{
		return getValue(map.get(key));
	}
	public String getValue(JComboBox<String> x)
	{
		return (String)x.getSelectedItem();
	}
	public String getValue(ButtonGroup x)
	{
		Enumeration<AbstractButton> buttons = ((ButtonGroup) x)
				.getElements();
		while (buttons.hasMoreElements())
		{
			JRadioButton button = (JRadioButton) buttons.nextElement();
			if (button.isSelected())
				return button.getText();
		}
		return "";
	}
	public String getValue(JTextComponent x)
	{
		return x.getText();
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
	
	private void buttonAction()
	{
		if (get("name").equals(""))
			return;
		Customer customer = addCustomer((JTextField)get("name").getText());
		String selected = comboArr[combo.getSelectedIndex()];
		Course course = findCourse(selected);
		String msg = courseString(course);
		String msgTitle = "Select an Option";
		int response = JOptionPane.showConfirmDialog(null, msg, msgTitle,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION)
		{
			// customer.addCourse(course);
			addCourse(customer.getName(), course.getTitle());
			get("name").requestFocus();
			clearFields();
		}
	}

	private void addCourse(String name, String title)
	{
		// System.out.println(name+" --- "+ title+ " -- " + customerList +
		// " -- "+ courseList);
		for (Customer c : customerList)
		{
			if (c.getName().equals(name))
			{
				for (Course course : courseList)
				{
					if (course.getTitle().equals(title))
					{
						c.addCourse(course);
					}
				}
			}
		}
	}

	private void clearFields()
	{
		nameText.setText("");
		streetText.setText("");
		cityText.setText("");
		stateText.setText("");
		zipText.setText("");
		accountText.setText("");
		combo.setSelectedIndex(0);
		group.clearSelection();
	}

	private String courseString(Course course)
	{
		String str = "";
		if (course instanceof OnlineCourse)
			str += "OnlineCourse\n";
		else if (course instanceof InClassCourse)
			str += "InClassCourse\n";
		str += course.getTitle() + "\n" + course.getPrice() + "\n"
				+ "Dates of Class\n" + course.getStartDate().getMonth() + "/"
				+ course.getStartDate().getDay() + "-"
				+ course.getEndDate().getMonth() + "/"
				+ course.getEndDate().getDay();
		return str;

	}

	private Course findCourse(String title)
	{
		for (Course c : courseList)
		{
			if (c.getTitle().toLowerCase().equals(title.toLowerCase()))
				return c;
		}
		return new Course();
	}

	private Customer addCustomer(String name)
	{
		for (Customer c : customerList)
		{
			if (c.getName().toLowerCase().equals(name.toLowerCase()))
				return c;
		}
		Customer c = new Customer(nameText.getText(), new Address(
				streetText.getText(), cityText.getText(), stateText.getText(),
				parseInt(zipText.getText())),
				parseInt(accountText.getText()));
		Customer.CustomerType customerType = student.isSelected() ? Customer.CustomerType.STUDENT
				: faculty.isSelected() ? Customer.CustomerType.FACULTY
						: Customer.CustomerType.GOVERNMENT;
		c.setCType(customerType);
		customerList.add(c);
		return c;
	}

	private static void readCourses(ArrayList<Course> courseList)
	{

		try
		{
			Scanner input = new Scanner(new File("courses.txt"));
			while (input.hasNext())
			{
				String[] words = input.nextLine().split(";");
				Course course;

				if (words[0].equals("Online"))
				{
					course = new OnlineCourse(words[1], words[2],
							Double.parseDouble(words[3]), new Date(
									parseInt(words[4]),
									parseInt(words[5]),
									parseInt(words[6])), new Date(
									parseInt(words[7]),
									parseInt(words[8]),
									parseInt(words[9])), words[11],
							Boolean.parseBoolean(words[12]),
							parseInt(words[13]));
				} else
				{
					course = new InClassCourse(words[1], words[2],
							Double.parseDouble(words[3]), new Date(
									parseInt(words[4]),
									parseInt(words[5]),
									parseInt(words[6])), new Date(
									parseInt(words[7]),
									parseInt(words[8]),
									parseInt(words[9])), words[11],
							new Time(parseInt(words[12]), Integer
									.parseInt(words[13])), new Time(
									parseInt(words[14]),
									parseInt(words[15])));
				}
				course.setCType(Course.CourseType.valueOf(words[10]
						.toUpperCase()));
				courseList.add(course);
			}
			comboArr = new String[courseList.size()];
			int i = 0;
			for (Course c : courseList)
				comboArr[i++] = c.getTitle();

			input.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

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
	
	private static void generateInvoice()
	{
		String msg = String.format("%-20s %-15s %s\n", "Name", "Account",
				"Charge");
		for (Customer customer : customerList)
		{
			try
			{
				customer.createInvoice();
				msg += customer.createInvoice();
				System.out.println(customer + "\r\n");
			} catch (CustomerNotEnrolledException e)
			{
				System.out.println("Customer " + customer.getName()
						+ " is not enrolled!");
			}
		}
		JOptionPane.showMessageDialog(null, msg);
	}
}
