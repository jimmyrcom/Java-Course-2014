// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CourseGui.java

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

// just a slow version of HashMap because we haven't covered it yet.
class FakeMap
{

	class Entry
	{

		public String key;
		public Object value;

		public Entry(String key, Object value)
		{
			this.key = key;
			this.value = value;
		}
	}

	private ArrayList<Entry> list = new ArrayList<Entry>();

	public void put(String key, Object value)
	{
		list.add(new Entry(key, value));
	}

	public Object get(String key)
	{
		for (Entry e : list)
			if (e.key == key)
				return e.value;
		return new Object();
	}

	public void clearValues()
	{
		for (Entry e : list)
			if (e.value instanceof JTextComponent)
				((JTextComponent) e.value).setText("");
			else if (e.value instanceof JComboBox)
				((JComboBox) e.value).setSelectedIndex(0);
			else if (e.value instanceof ButtonGroup)
				((ButtonGroup) e.value).clearSelection();

		((JTextField) get("name")).requestFocusInWindow();

	}

	public String getValue(String key)
	{
		Object value = get(key);
		if (value instanceof JTextComponent)
			return ((JTextComponent) value).getText();
		else if (value instanceof JComboBox)
			return String
					.valueOf(((JComboBox<String>) value).getSelectedItem());
		else if (value instanceof ButtonGroup)
		{
			Enumeration<AbstractButton> buttons = ((ButtonGroup) value)
					.getElements();
			while (buttons.hasMoreElements())
			{
				JRadioButton button = (JRadioButton) buttons.nextElement();
				if (button.isSelected())
					return button.getText();
			}
		}
		return "";
	}
}

public class CourseGUI extends JFrame
{

	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	private static ArrayList<Course> courseList = new ArrayList<Course>();

	private FakeMap map = new FakeMap();

	public CourseGUI(String name)
	{
		super(name);
		setLayout(new GridLayout(10, 2));

		readCourses(courseList);
		// No duplicates
		Set<String> unique = new HashSet<String>();
		for (int i = 0; i < courseList.size(); i++)
			unique.add(courseList.get(i).getTitle());
		// Array of courses from a hashset. This is because I did the HW early
		// and had courses.txt with dupes.
		String[] courseOptions = unique.toArray(new String[unique.size()]);

		addText("name", "Enter Name");
		addText("street", "Enter Street");
		addText("city", "Enter City");
		addText("state", "Enter State");
		addText("zip", "Enter Zip");
		addText("account", "Enter Account Number");
		addRadio("type", "Enter Type of Customer", "STUDENT", "FACULTY",
				"GOVERNMENT");
		JComboBox<String> combo = new JComboBox<String>(courseOptions);
		combo.setMaximumRowCount(3);
		add1("course", "Select a course", combo);
		addButton("submit", "Click Submit when Done", "Submit",
				new ActionListener()
				{

					public void actionPerformed(ActionEvent e)
					{
						if (get("name").equals(""))
							return;
						String name = get("name").toLowerCase();
						Boolean customerExists = false;
						for (Customer c : customerList)
						{
							if (c.getName().toLowerCase().equals(name))
								customerExists = true;
						}

						int response = 0;
						if (!customerExists)
						{
							addCustomer();
						}

						response = JOptionPane.showConfirmDialog(null,
								courseString(get("course")),
								"Select an Option", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION)
						{
							addCourse(get("name"), get("course"));
							clearFields();
						}
					}
				});
		addButton("finish", "Click when Finished", "Finish",
				new ActionListener()
				{

					public void actionPerformed(ActionEvent e)
					{
						clearFields();
						generateInvoice();
						System.exit(0);
					}
				});

	}

	private String courseString(String course)
	{
		for (Course c : courseList)
		{
			if (c.getTitle().toLowerCase().equals(course.toLowerCase()))
				return courseString(c);
		}
		return "";
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

	public Boolean addCourse(String name, String course)
	{
		name = name.toLowerCase();
		for (Customer customer : customerList)
		{
			if (customer.getName().toLowerCase().equals(name))
				return addCourse(name, course, customer);

		}
		return false;
	}

	public Boolean addCourse(String name, String courseName, Customer customer)
	{
		for (Course course : courseList)
			if (course.getTitle().equals(courseName))
			{
				customer.addCourse(course);
				return true;
			}
		return false;
	}

	public void addCustomer()
	{
		String[] streetArr = get("street").split(" ", 2);
		Integer streetNumber = 0;
		String streetName = "";
		if (streetArr.length < 2)
		{
			streetName = get("street");
		} else
		{
			streetNumber = parseInt(streetArr[0]);
			streetName = streetArr[1].replace(" ", "");
		}
		String customer = get("name") + " " + streetNumber + " " + streetName
				+ " " + get("city") + " " + get("state") + " " + get("zip")
				+ " " + get("account") + " " + get("type").toLowerCase();
		//System.out.println(customer);
		String[] words = customer.split(" ");
		Customer c = new Customer(words[0], new Address(words[1] + " "
				+ words[2], words[3], words[4], parseInt(words[5])),
				parseInt(words[6]));
		c.setCType(Customer.CustomerType.valueOf((words[7].toUpperCase())));
		customerList.add(c);
		// System.out.println();
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
			System.err.println("Error opening file.");
		}
	}

}
