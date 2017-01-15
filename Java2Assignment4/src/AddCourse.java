// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Address.java

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class AddCourse extends JPanel
{

	public static ArrayList<Course> courseList = new ArrayList<Course>();

	private static JLabel cNameLabel = new JLabel("  Student Name");
	private static JTextField cName = new JTextField("");
	private static String[] courseArray;
	private static JButton enroll = new JButton("Enroll");
	private static JComboBox<String> combo;

	public AddCourse()
	{
		setLayout(new GridLayout(0, 2));
		add(cNameLabel);
		add(cName);
		AddCustomer.readCourses(courseList);
		courseArray = new String[courseList.size() + 1];
		for (int i = 0; i < courseList.size(); i++)
		{
			courseArray[i + 1] = courseList.get(i).getTitle();
		}
		courseArray[0] = "Select a course";
		combo = new JComboBox<String>(courseArray);
		combo.setMaximumRowCount(3);
		add(new JLabel("  Course"));
		add(combo);
		add(new JLabel("  Enroll"));
		add(enroll);

		enroll.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				if (cName.getText().isEmpty())
				{
					CourseGUI.invalid(cName);
					return;
				}
				if (combo.getSelectedIndex() == 0)
				{
					CourseGUI.invalid(combo);
					return;
				}

				int response = JOptionPane.showConfirmDialog(null,
						courseString((String) combo.getSelectedItem()),
						"Select an Option", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION)
				{
					try
					{
						addCourse(cName.getText(),
								(String) combo.getSelectedItem());
						CourseGUI.message("Course enrollment successful!");
						cName.setText("");
						combo.setSelectedIndex(0);
					} catch (CustomerNotFoundException err)
					{
						CourseGUI
								.message("Customer not found or is already enrolled in that course!");
					}
				}
			}
		});
	}

	public Boolean addCourse(String name, String course)
			throws CustomerNotFoundException
	{
		name = name.toLowerCase();
		for (Customer customer : AddCustomer.customerList)
		{
			if (customer.getName().toLowerCase().equals(name))
			{
				// check if we already enrolled in it
				for (Course c : customer.getCourseListReal())
					if (c.getTitle().equals(course))
						throw new CustomerNotFoundException();
				return addCourse(name, course, customer);
			}

		}
		throw new CustomerNotFoundException();
	}

	public Boolean addCourse(String name, String courseName, Customer customer)
			throws CustomerNotFoundException
	{
		for (Course course : courseList)
			if (course.getTitle().equals(courseName))
			{
				customer.addCourse(course);
				return true;
			}
		throw new CustomerNotFoundException();
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

}