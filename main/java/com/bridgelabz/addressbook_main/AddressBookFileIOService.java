package com.bridgelabz.addressbook_main;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class AddressBookFileIOService 
{	
	/**
	 * Writes an address book's data to a file.
	 * @param List of Record objects, address book.
	 * @param Name of the book where data is to written.
	 */
	public void writeData(ArrayList<Record> book, String bookName) 
	{
		final String MY_FILE = "./"+bookName+".csv";
		try {
			Writer writer = Files.newBufferedWriter(Paths.get(MY_FILE));
			StatefulBeanToCsv<Record> beanToCsv = new StatefulBeanToCsvBuilder<Record>(writer)
												  	  .withQuotechar(CSVWriter.NO_ESCAPE_CHARACTER)
												  	  .build();
			try 
			{
				beanToCsv.write(book);
				writer.close();
			} catch (CsvDataTypeMismatchException e) {
				e.printStackTrace();
			} catch (CsvRequiredFieldEmptyException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			System.out.println("File could not be read.");
		}
	}
	
	/**
	 * Reads address book data from a file.
	 * @param Name of the file (without extension).
	 * @return List of Record objects.
	 */
	public void readData(String bookName) 
	{
		final String MY_FILE = "./"+bookName+".csv";
		
		try (Reader reader = Files.newBufferedReader(Paths.get(MY_FILE))) 
		{
				
			CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(reader).withType(Record.class)
											  .withIgnoreLeadingWhiteSpace(true)
											  .build();
			
			Iterator<Record> recordIterator = csvToBean.iterator(); 	
			while(recordIterator.hasNext())
			{
				Record record = (Record) recordIterator.next();
				System.out.println("Name: "+record.firstName+" "+record.lastName);
				System.out.println("Email: "+record.email);
				System.out.println("Address: "+record.address);
				System.out.println("City: "+record.city);
				System.out.println("State: "+record.state);
				System.out.println("Phone number: "+record.phoneNumber);
				System.out.println("PIN: "+record.zip);
			}
			
			reader.close();
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
}
