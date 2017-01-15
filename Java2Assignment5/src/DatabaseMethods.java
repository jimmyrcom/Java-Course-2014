// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Books.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseMethods
{

	private static final String URL = "jdbc:mysql://localhost/courses";
	private static final String USERNAME = "cdavis";
	private static final String PASSWORD = "fall2013";

	public static Connection connection = null; // manages connection

	private static PreparedStatement addCustomer = null;
	private static PreparedStatement addOnlineCourse = null;
	private static PreparedStatement addInclassCourse = null;
	private static PreparedStatement findCourse = null;
	private static PreparedStatement customerAlreadyEnrolled = null;
	private static PreparedStatement getCustomer = null;
	private static PreparedStatement addCourseToCustomer = null;
	private static PreparedStatement getCourse = null;

	// addCustomer, addCourseToCustomer, writeCustomerTable, writeCourseTable,
	// writeCustomerCoursesTable, one to search the customer table

	public static void test(){
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// constructor
	public DatabaseMethods()
	{
		try
		{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			addOnlineCourse = connection
					.prepareStatement("INSERT INTO `Course` (`CourseType`, `title`, `instructor`, `price`, `cType`, "
							+ "`startDate`, `endDate`, `examProctor`, `video`, `numberOfChapters`)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// don't need these...
			addInclassCourse = connection
					.prepareStatement("INSERT INTO `Course` (`CourseType`, `title`, `instructor`, `price`, `cType`, "
							+ "`startDate`, `endDate`, `room`, `startTime`, `endTime`) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			addCustomer = connection
					.prepareStatement("INSERT INTO Customer (name, address, accountNumber,"
							+ " cType) VALUES (?,?,?,?)");

			addCourseToCustomer = connection
					.prepareStatement("INSERT INTO CustomerCourses (customer, course) values (?, ?)");

			getCustomer = connection
					.prepareStatement("SELECT id FROM Customer where name = ? OR accountNumber = ? limit 1");
			findCourse = connection
					.prepareStatement("SELECT id FROM Course where title = ?  limit 1");
			customerAlreadyEnrolled = connection
					.prepareStatement("SELECT id FROM CustomerCourses WHERE customer = ? AND course = ?  limit 1");

		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch
	} // end CustomerQueries constructor

	public static int findCourse(String name)
	{
		try
		{
			findCourse.setString(1, name);
			ResultSet resultSet = findCourse.executeQuery();
			if (resultSet.next())
				return resultSet.getInt("id");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public static void addCourseToCustomer(String customer, String course)
	{
		try
		{
			addCourseToCustomer.setString(1, customer);
			addCourseToCustomer.setString(2, course);
			addCourseToCustomer.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void addCustomer(String name, String address,
			int accountNumber, String cType)
	{
		try
		{
			addCustomer.setString(1, name);
			addCustomer.setString(2, address);
			addCustomer.setInt(3, accountNumber);
			addCustomer.setString(4, cType);
			addCustomer.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static boolean customerAlreadyEnrolled(String name, String course)
	{
		try
		{
			customerAlreadyEnrolled.setString(1, name);
			customerAlreadyEnrolled.setString(2, course);
			ResultSet resultSet = customerAlreadyEnrolled.executeQuery();
			return resultSet.next();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static int customerExists(String name, int account)
	{
		try
		{
			getCustomer.setString(1, name);
			getCustomer.setInt(2, account);
			ResultSet resultSet = getCustomer.executeQuery();
			if (resultSet.next())
				return resultSet.getInt("id");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public void close()
	{
		try
		{
			connection.close();
		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		} // end catch
	} // end method close

	public static void showTable(String table)
	{
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			statement = connection.createStatement();
			// all the code above will always be the same.
			resultSet = statement.executeQuery("SELECT * FROM " + table);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("TABLE: " + table);
			// COLUMN OF THE RESULT SET START AT 0 NOT 1.
			for (int i = 1; i <= numberOfColumns; i++)
			{
				System.out.printf("%-30s\t", metaData.getColumnName(i));
			}
			System.out.println();
			System.out
					.println("-----------------------------------------------------------------");
			System.out.println();

			while (resultSet.next())
			{
				for (int i = 1; i <= numberOfColumns; i++)
				{
					System.out.printf("%-30s\t", resultSet.getObject(i));
					//
				}
				System.out.println();

			}
			System.out.println("\n\n");

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				resultSet.close();
				statement.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}
} // end class CustomerQueries
