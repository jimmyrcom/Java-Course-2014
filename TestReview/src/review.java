import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class review
{
	
	public static void main()
	{
		
	}
	
	public void someMethod(ArrayList<Integer> xs)
	{
			try {
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("derp.ser"));
				for(Object o : xs)
				{
					output.writeObject(o);
				}
			}
			catch (IOException io)
			{
				io.printStackTrace();
			}

	}

	public ArrayList<Object> someMethod1()
	{
		ObjectInputStream input;
		ArrayList<Object> foo = new ArrayList<Object>();
		try
		{
			input = new ObjectInputStream(new FileInputStream("input.ser"));
			while (true)
			{
				foo.add((Object) input.readObject());
			}
		}
		catch (EOFException e)
		{
			return foo;
			//everything ok
		}
		catch (IOException | ClassNotFoundException e)
		{
			System.err.println("Error");
		}
		return foo;
	}
	
	public ArrayList<Object> someMethod()
	{
		ObjectInputStream input;
		ArrayList<Object> foobar = new ArrayList<Object>();
			try
		{
			input = new ObjectInputStream(new FileInputStream("objects.ser"));
			while (true)
			{
				Object o = (Object) input.readObject();
				foobar.add(o);
			}
		} catch (ClassNotFoundException e)
		{
			// Cannot unserialize class in file
			System.err.println("Bad Class");
		} catch (EOFException e)
		{
			// no more data in .ser file
		} catch (IOException e)
		{
			// could not open or read file
			e.printStackTrace();
		}
		return foobar;
	}

	
	
}
