// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      CustomerNotFoundException.java

public class CustomerNotFoundException extends Exception
{
	CustomerNotFoundException(String message) {
        super(message);
    }
	CustomerNotFoundException() {
        super();
    }
}
