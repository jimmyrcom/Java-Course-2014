import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Foobar
{
	
	public static Connection connection;
	
	public static PreparedStatement foobar;
	public static Statement moo;

	
	public Foobar()
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/courses", "root", "");
			foobar = connection.prepareStatement("select ? from customer");
			moo = connection.createStatement();
		}
		catch (SQLException e)
		{
			
		}
	}
	
	public static void runFoobar()
	{
		try
		{
			foobar.setString(1, "Customer");
			ResultSet out = foobar.executeQuery();
			ResultSetMetaData x = out.getMetaData();
			int y = x.getColumnCount();
		}
		catch (SQLException e)
		{
			
		}
	}
	
	

}
