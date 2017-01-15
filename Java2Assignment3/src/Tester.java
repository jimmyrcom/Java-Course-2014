import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class Tester implements Serializable
{

	public static void main(String[] args)
	{
		
	}
	
	public void someMethod(Object foobar)
	{
		ObjectOutputStream output;
		try
		{
			output = new ObjectOutputStream(
					new FileOutputStream("accounts.ser"));
			output.writeObject(foobar);
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public ArrayList<Object> someMethod1()
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

	public Boolean someMethod(JTextField foobar)
	{
		try
		{
			Integer.parseInt(foobar.getText());
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

}
