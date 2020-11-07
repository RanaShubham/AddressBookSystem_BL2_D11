package com.bridgelabz.addressbook_main;

public class Record {

	String firstName;
	String lastName;
	String address;
	String city;
	String state;
	String email;
	int zip;
	long phoneNumber;
	
	
	public Record (String firstname, String lastName, String address, String city, String state, String email, int zip, long phoneNumber) 
	{
		this.firstName = firstname;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}

	
	/**Returns true if two records have same first name and last name.
	 * @param record
	 * @return
	 */
	public boolean equals(Record record)
	{
		try {

			if (this.firstName.equals(record.firstName) && this.lastName.equals(record.lastName))
				return true;
			else
				return false;
		
		} catch (NullPointerException e) {
			System.out.println("No such record");
			return false;
		}
	}

	
	/**
	 *To convert and print Record type objects.
	 */
	public String toString()
	{
		String recordToPrint = "FIRSTNAME="+this.firstName+" LASTNAME="+this.lastName+" ADDRESS="+this.address+" CITY="+this.city+" STATE=" +this.state+" EMAIL="+this.email+" ZIP="+this.zip+" PHONE_NUMBER="+this.phoneNumber;
		return recordToPrint;	
	}
}
