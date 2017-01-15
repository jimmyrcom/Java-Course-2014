// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CourseGui.java

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CourseGUI extends JFrame
{

	private JDesktopPane theDesktop;

	public CourseGUI(String str)
	{
		super(str);
		theDesktop = new JDesktopPane();
		JMenuBar bar = new JMenuBar();

		JMenu addMenu = new JMenu("Add");
		JMenuItem addCustomer = new JMenuItem("Add Customer");
		JMenuItem addCourse = new JMenuItem("Add Course");
		addMenu.add(addCustomer);
		addMenu.add(addCourse);
		bar.add(addMenu);

		JMenu invoiceMenu = new JMenu("Invoice");
		JMenuItem invoiceItem = new JMenuItem("Generate Invoice");
		invoiceMenu.add(invoiceItem);
		bar.add(invoiceMenu);

		JMenu exitMenu = new JMenu("Exit");
		JMenuItem writeItem = new JMenuItem("Write Files");
		JMenuItem exitItem = new JMenuItem("Exit program");
		exitMenu.add(writeItem);
		exitMenu.add(exitItem);
		bar.add(exitMenu);

		add(theDesktop);
		setJMenuBar(bar);

		addCustomer.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				addInternal(theDesktop, new AddCustomer());
			}
		});

		addCourse.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				addInternal(theDesktop, new AddCourse());
			}
		});

		writeItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				SerFile.write("customers.ser", AddCustomer.customerList);
				SerFile.write("courses.ser", AddCourse.courseList);
				message("Course files written!");
			}
		});

		exitItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				AddCustomer.customerList = SerFile
						.<Customer> read("customers.ser");
				AddCourse.courseList = SerFile.<Course> read("courses.ser");
				// message("Course files loaded!");
				for (Customer person : AddCustomer.customerList)
				{
					try
					{
						System.out.println("----------- Student Invoice: "
								+ person.getName() + "------------");
						System.out.println(person.createInvoice()); // just for
																	// the
																	// exception
						System.out.println(person + "\r\n");
					} catch (CustomerNotEnrolledException err)
					{
						System.out.println("Customer " + person.getName()
								+ " is not enrolled!");
					}
				}
				
				System.out.println("----- customers.ser ----------");
				System.out.println(AddCustomer.customerList);
				System.out.println("----- courses.ser ----------");
				System.out.println(AddCourse.courseList);

				System.exit(0);
			}
		});

		invoiceItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				AddCustomer.generateInvoice();
			}
		});
	}

	public static void message(String x)
	{
		JOptionPane.showMessageDialog(null, x);
	}

	public static void invalid(JComponent x)
	{
		x.requestFocus();
		message("Invalid or no data!");
	}

	public static void invalid(Object x)
	{
		invalid((JComponent) x);
	}

	public static Boolean errorInt(JTextField x)
	{
		try
		{
			Integer.parseInt(x.getText());
		} catch (Exception e)
		{
			invalid(x);
			return true;
		}
		return false;
	}

	public static Boolean errorInt(Object x)
	{
		return errorInt((JTextField) x);

	}

	public static Boolean errorEmpty(JTextField x)
	{
		if (x.getText().isEmpty())
		{
			invalid(x);
			return true;
		}
		return false;
	}

	public static Boolean errorEmpty(Object x)
	{
		return errorEmpty((JTextField) x);
	}

	public static void addInternal(JDesktopPane desktop, JPanel op)
	{
		JInternalFrame frame = new JInternalFrame("Add Customer", true, true,
				true, true);
		op.setVisible(true);
		frame.add(op);
		frame.pack(); // fit the size of panel
		desktop.add(frame);
		// set maximized
		/*
		 * try { frame.setMaximum(true); } catch (Exception ex) { }
		 */
		frame.setVisible(true);
	}
}
