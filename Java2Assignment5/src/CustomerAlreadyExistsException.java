// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CustomerNotEnrolledException.java

public class CustomerAlreadyExistsException extends Exception
{

	CustomerAlreadyExistsException(String message)
	{
		super(message);
	}

	CustomerAlreadyExistsException()
	{
		super();
	}
}
