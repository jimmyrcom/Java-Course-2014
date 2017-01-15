// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Date.java

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerFile
{

	// this is the simplest generics, but why can't java just downcast it to
	// object arraylist... if I send arraylist of customer to method with
	// arraylist of object it fails
	public static <T> void write(String file, ArrayList<T> list)
	{
		ObjectOutputStream output;
		try
		{
			output = new ObjectOutputStream(new FileOutputStream(file));
			for (Object items : list)
				output.writeObject(items);
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public static <T> ArrayList<T> read(String file)
	{
		ObjectInputStream input;
		ArrayList<T> output = new ArrayList<T>();

		try
		{
			input = new ObjectInputStream(new FileInputStream(file));
			while (true)
			{
				T o = (T) input.readObject();
				output.add(o);
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
		return output;
	}

}
