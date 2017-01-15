// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CustomerNotEnrolledException.java

public class CustomerNotEnrolledException extends Exception
{

	// Keep the IDE happy
	private static final long serialVersionUID = 1L;

	CustomerNotEnrolledException(String message)
	{
		super(message);
	}

	CustomerNotEnrolledException()
	{
		super();
	}
}
