// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Address.java

public class Address
{

	private String street = "";
	private String city = "";
	private String state = "";
	private int zip = 0;

	/*** Constructors ***********/
	Address()
	{
		setZip(0);
		setState("");
		setCity("");
		setStreet("");
	}

	Address(String street, String city, String state, int zip)
	{
		setZip(zip);
		setState(state);
		setCity(city);
		setStreet(street);
	}

	/*** Getters and Setters ***********/
	public int getZip()
	{
		return zip;
	}

	public String getState()
	{
		return state;
	}

	public String getCity()
	{
		return city;
	}

	public String getStreet()
	{
		return street;
	}

	public void setZip(int z)
	{
		this.zip = z;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	@Override
	public String toString()
	{
		return " Address(Street = " + "\"" + street + "\"" + ", City = " + "\""
				+ city + "\"" + ", State = " + "\"" + state + "\"" + ", Zip = "
				+ zip + ")";
	}


}