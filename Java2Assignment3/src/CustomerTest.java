// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CourseTest.java

import javax.swing.JFrame;

public class CustomerTest
{

	public static void main(String[] args)
	{

		CourseGUI gui = new CourseGUI("Course GUI");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(650, 350);
		gui.setVisible(true);
		gui.setLocationRelativeTo(null);
	}

}