package com.bridgelabz.addressbook_main;

import com.opencsv.bean.CsvBindByName;

public class Record {

	@CsvBindByName(column = "1.FIRST_NAME")
	String firstName;
	@CsvBindByName(column = "2.LAST_NAME")
	String lastName;
	@CsvBindByName(column = "3.HOME_ADDRESS")
	String address;
	@CsvBindByName(column = "4.CITY")
	String city;
	@CsvBindByName(column = "5.STATE")
	String state;
	@CsvBindByName(column = "6.EMAIL")
	String email;
	@CsvBindByName(column = "7.PIN")
	int zip;
	@CsvBindByName(column = "8.PHONE_NUMBER")
	long phoneNumber;
	
	//Default constructor for POJO class is compulsory to read data through an iterator.
	public Record()
	{}
	
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

			if (this.firstName.equals(record.firstName) && this.lastName.equals(record.lastName))
				return true;
			else
				return false;
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
