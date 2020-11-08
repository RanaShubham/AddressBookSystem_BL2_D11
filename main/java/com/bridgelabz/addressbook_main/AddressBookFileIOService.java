package com.bridgelabz.addressbook_main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class AddressBookFileIOService 
{	
	private Gson gson;

	/**
	 * Writes an address book's data to a file.
	 * @param List of Record objects, address book.
	 * @param Name of the book where data is to written.
	 */
	public void writeData(ArrayList<Record> book, String bookName) 
	{
		final String MY_FILE = "./"+bookName+".json";
		try {
			Writer writer = Files.newBufferedWriter(Paths.get(MY_FILE));
			gson = new Gson();
			gson.toJson(book, writer);
			writer.close();
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
		final String MY_FILE = "./"+bookName+".json";
		try {
			Reader reader = Files.newBufferedReader(Paths.get(MY_FILE));
			@SuppressWarnings("serial")
			List<Record> users = new Gson().fromJson(reader, new TypeToken<List<Record>>() {}.getType());
			users.forEach(System.out::println);
			reader.close();
		} catch (IOException e) {
			System.out.println("File to be read not found.");
		}
		
	}
}
