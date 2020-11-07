package com.bridgelabz.addressbook_main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.bridgelabz.employepayrollservice.EmployeePayrollFileIOService;
import com.bridgelabz.employepayrollservice.EmployeePayrollService.IOService;

public class Utilities {

	static Scanner input = new Scanner (System.in);
	
	public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}
	
	/**Initializing a new book if not present already.
	 * @param bookName
	 */
	static void addAddressBook(String bookName) 
	{
		if (!AddressBookSystem.addressBookStore.containsKey(bookName))
			AddressBookSystem.addressBookStore.put(bookName, new ArrayList< Record>());
		
		collectDetails(bookName);
	}


	/**Asking user for details of the record to be added
	 * @param bookName
	 */
	static void collectDetails(String bookName)
	{
		while(true)
		{
		System.out.println("Enter details of one user in following order"
				+ "\n1. First Name"
				+ "\n2. Last Name"
				+ "\n3. Address"
				+ "\n4. City"
				+ "\n5. State"
				+ "\n6. email"
				+ "\n7. Pincode"
				+ "\n8. Phone number");
						
			String firstName = input.next();
			String lastName = input.next();
			String address = input.next();
			String city = input.next();
			String state = input.next();
			String email = input.next();
			int pin = input.nextInt();
			long phoneNumber = input.nextLong();
	
			Record newRecord = new Record(firstName, lastName, address, city, state, email, pin, phoneNumber);
			addRecord(AddressBookSystem.addressBookStore.get(bookName), bookName, newRecord);
			
			System.out.println("Enter y to add one more record to this book. Otherwise enter n.");
			String response = input.next();
			
			if (response.equals("n"))
				break;
		}
	}
	
	
	
	/**Adding person details into address book.
	 * @param book
	 * @param bookName
	 * @param newRecord
	 */
	static void addRecord(ArrayList<Record> book, String bookName, Record newRecord)
	{
		boolean recordPresentInBook = false;
		for (Record record : book)
		{
			Predicate<Record> recordCheck = passedRecord -> passedRecord.equals(newRecord);
			
			if (recordCheck.test(record) )
			{
				recordPresentInBook = true;
			}
		}
		
		if (recordPresentInBook)
		{
				System.out.println("Record already exists in address book "+bookName);
		}
		else
		{
			book.add(newRecord);
			System.out.println("Record added to address book "+bookName);
		}
	}
	
	

	/**Deleting a record using first name.
	 * @param nameOfRecordToDelete
	 * @param bookThatHasRecord
	 */
	static void deleteRecord(String nameOfRecordToDelete, String bookThatHasRecord) 
	{
		ArrayList<Record> book = AddressBookSystem.addressBookStore.get(bookThatHasRecord);
		Record recordToBeDeleted = book.stream().filter( record -> record.firstName.equals(nameOfRecordToDelete)).findFirst().get();
		book.remove(recordToBeDeleted);
	}
	
	

	/**Editing a record using first name.
	 * @param nameOfRecordToEdit
	 * @param bookThatHasRecord
	 */
	static void editRecord(String nameOfRecordToEdit, String bookThatHasRecord)
	{
		ArrayList<Record> book = AddressBookSystem.addressBookStore.get(bookThatHasRecord);
		Record recordToBeEdited = book.stream().filter( record -> record.firstName.equals(nameOfRecordToEdit)).findFirst().get();
		
		if(recordToBeEdited.equals(null))
		{
			System.out.println("No record found with name "+nameOfRecordToEdit+" in book: "+bookThatHasRecord);
			System.exit(0);
		}
		
		System.out.println("Pick a feild to edit"+
				"Enter 1 to edit firstName\n" + 
				"Enter 2 to edit lastName\n" + 
				"Enter 3 to edit address\n" + 
				"Enter 4 to edit city\n" + 
				"Enter 5 to edit state\n" + 
				"Enter 6 to edit email\n" +
				"Enter 7 to edit pin\n" +
				"Enter 8 to edit phoneNumber");
		
		int response = input.nextInt();
		
		switch(response)
		{
			case 1: Utilities.editFirstname(recordToBeEdited);
					break;
			case 2: Utilities.editLastname(recordToBeEdited);
					break;
			case 3: Utilities.editAddress(recordToBeEdited);
					break;
			case 4: Utilities.editCity(recordToBeEdited);
					break;
			case 5: Utilities.editState(recordToBeEdited);
					break;
			case 6: Utilities.editEmail(recordToBeEdited);
					break;
			case 7: Utilities.editPin(recordToBeEdited);
					break;
			case 8: Utilities.editPhoneNumber(recordToBeEdited);
					break;
			default: System.exit(0);
		}
	}
	
	

	/**To change pin of existing record.
	 * @param recordToEdit
	 */
	public static void editPin(Record recordToEdit) 
	{
		System.out.println("Enter new zip number");
		int newPin = input.nextInt();
		
		recordToEdit.zip = newPin;		
	}

	

	/**To change last name of existing record
	 * @param recordToEdit
	 */
	public static void editLastname(Record recordToEdit) 
	{
		System.out.println("Enter new last name");
		String newLastName = input.next();
		
		recordToEdit.lastName = newLastName;
	}
	


	/**To change phone number of existing record
	 * @param recordToEdit
	 */
	public static void editPhoneNumber(Record recordToEdit) 
	{
		System.out.println("Enter new phone number");
		long newPhoneNumber = input.nextLong();
		
		recordToEdit.phoneNumber = newPhoneNumber;
	}

	

	/**To change email of existing record.
	 * @param recordToEdit
	 */
	public static void editEmail(Record recordToEdit) 
	{
		System.out.println("Enter new email");
		String newMail = input.next();
		
		recordToEdit.email = newMail;
	}
	

	/**To change state name of existing record.
	 * @param recordToEdit
	 */
	public static void editState(Record recordToEdit) 
	{
		System.out.println("Enter new state");
		String newState = input.next();
		
		recordToEdit.state = newState;		
	}

	

	/**To change city name of existing record
	 * @param recordToEdit
	 */
	public static void editCity(Record recordToEdit) 
	{

		System.out.println("Enter new city");
		String newCity = input.next();
		
		recordToEdit.city = newCity;
	}

	

	/**To change address of existing record.
	 * @param recordToEdit
	 */
	public static void editAddress(Record recordToEdit) 
	{
		System.out.println("Enter new address");
		String newAddress = input.next();
		
		recordToEdit.address = newAddress;		
	}

	

	/**To change first name of existing record.
	 * @param recordToEdit
	 */
	public static void editFirstname(Record recordToEdit) 
	{
		System.out.println("Enter new firstName");
		String newFirstName = input.next();
		
		recordToEdit.firstName = newFirstName;
	}


	/**To search record of users of a city or state.
	 * @param cityOrStateToSearch
	 */
	public static void searchByCityOrState(String cityOrStateToSearch) 
	{
		for (Entry<String, ArrayList<Record>> book : AddressBookSystem.addressBookStore.entrySet())
		{
			ArrayList<Record> recordList = book.getValue();
			Stream<Record> matchingRecords = recordList.stream().filter(record -> record.city.equals(cityOrStateToSearch) | record.state.equals(cityOrStateToSearch));
			matchingRecords.forEach(record -> System.out.println(book.getKey()+": "+record.toString()));
		}
	}
	

	/**To store each record in a two hashMaps of pair name and state AND name and city.
	 * @param city_or_State
	 */
	public static void storeByCityOrState(String city_or_State)
	{
		HashMap<String, String> byCity = null;
		HashMap<String, String> byState = null;
		
		for (Entry<String, ArrayList<Record>> book : AddressBookSystem.addressBookStore.entrySet())
		{
			ArrayList<Record> recordList = book.getValue();
			Stream<Record> matchingRecordsByCity = recordList.stream().filter(record -> record.city.equals(city_or_State));
			Stream<Record> matchingRecordsByState = recordList.stream().filter(record -> record.state.equals(city_or_State));
			
			matchingRecordsByCity.forEach(record -> byCity.put(record.firstName+" "+record.lastName, record.city));
			matchingRecordsByState.forEach(record -> byState.put(record.firstName+" "+record.lastName, record.state));
		}
	}
	

	/**
	 * Method to print the number of records or contacts per city and per state.
	 */
	public static void numberOfRecordsPerStateOrPerCity ()
	{
		HashMap<String, Integer> recordsPerCity = new HashMap<String, Integer>();
		HashMap<String, Integer> recordsPerState = new HashMap<String, Integer>();

		for (Entry<String, ArrayList<Record>> book : AddressBookSystem.addressBookStore.entrySet())
		{
			ArrayList<Record> recordList = book.getValue();
			
			//recordList1.stream().filter(data -> data != null).map(record -> recordsPerCity.get(record.city) != null ? recordsPerCity.put(record.city, recordsPerCity.get(record.city)+1) : recordsPerCity.put(record.city, 1));
			//recordList2.stream().filter(data -> data != null).map(record -> recordsPerState.get(record.state) != null ? recordsPerState.put(record.state, recordsPerState.get(record.state)+1) : recordsPerState.put(record.state, 1));
			recordList.forEach(data -> {
				if (recordsPerCity.get(data.city) != null) {
					recordsPerCity.put(data.city, recordsPerCity.get(data.city)+1);
				}
				else
				{
					recordsPerCity.put(data.city, 1);
				}
			});
			
			recordList.forEach(data -> {
				if (recordsPerState.get(data.state) != null) {
					recordsPerState.put(data.state, recordsPerState.get(data.state)+1);
				}
				else
				{
					recordsPerState.put(data.city, 1);
				}
			});
		}
		
		System.out.println(recordsPerCity);
		System.out.println(recordsPerState);
	}
	

	/**
	 * To sort all the records in every address book by name and print it.
	 */
	public static void sortAddressBooksByName()
	{
		for (Entry<String, ArrayList<Record>> book : AddressBookSystem.addressBookStore.entrySet())
		{
			ArrayList<Record> recordListToSort = book.getValue();
			
			Collections.sort(recordListToSort,(recordObj1, recordObj2) -> (recordObj1.firstName+recordObj1.lastName).compareTo(recordObj2.firstName+recordObj2.lastName));
			System.out.println(recordListToSort);
		}
	}
	

	/**
	 * To sort all the records in every address book by city name.
	 */
	public static void sortByCity()
	{
		for (Entry<String, ArrayList<Record>> book : AddressBookSystem.addressBookStore.entrySet())
		{
			ArrayList<Record> recordListToSort = book.getValue();
			
			Collections.sort(recordListToSort,(recordObj1, recordObj2) -> (recordObj1.city).compareTo(recordObj2.city));
			System.out.println(recordListToSort);
		}
	}
	
 
	/**
	 * To sort all the records in every address book by state name.
	 */
	public static void sortByState()
	{
		for (Entry<String, ArrayList<Record>> book : AddressBookSystem.addressBookStore.entrySet())
		{
			ArrayList<Record> recordListToSort = book.getValue();
			
			Collections.sort(recordListToSort,(recordObj1, recordObj2) -> (recordObj1.state).compareTo(recordObj2.state));
			System.out.println(recordListToSort);
		}
	}
	

	/**
	 * To sort all the records in every address book by ZIP code.
	 */
	public static void sortByZip()
	{
		for (Entry<String, ArrayList<Record>> book : AddressBookSystem.addressBookStore.entrySet())
		{
			ArrayList<Record> recordListToSort = book.getValue();
			
			Collections.sort(recordListToSort,(recordObj1, recordObj2) -> (recordObj1.zip < recordObj2.zip) ? -1 : (recordObj1.zip > recordObj2.zip) ? 1 : 0);
			System.out.println(recordListToSort);
		}
	}

	/**To write address book object data to an address book.
	 * @param Record object name or AddressBook name.
	 */
	public static void writeAddressbookData(String bookName) 
	{
		new AddressBookFileIOService().writeData(AddressBookSystem.addressBookStore.get(bookName), bookName);
	}


	/**To read data from address book.
	 * @param returns number of records in the book.
	 */
	public static long readAddressbookData(String bookName) 
	{
		List<Record> book = new ArrayList<>();
		book = new AddressBookFileIOService().readData(bookName);
		System.out.println(book);
		return book.size();
	}
}