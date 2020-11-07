package com.bridgelabz.addressbook_main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.employepayrollservice.EmployeePayrollData;

public class AddressBookFileIOService 
{	
	public void writeData(ArrayList<Record> book, String bookName) 
	{
		StringBuffer empBuffer = new StringBuffer();
		book.forEach(record -> {
			String recordString = record.toString().concat("\n");
			empBuffer.append(recordString);
		});
		
		try {
			Files.write(Paths.get(bookName), empBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Record> readData(String bookName) 
	{
		List<Record> addressbook = new ArrayList<>();
		try {
			Files.lines(new File(bookName).toPath()).map(record -> record.trim().split(" "))
				 .forEach(recordLineArr -> 
				 {
					 Record record = dataToObject(recordLineArr);
					 addressbook.add(record);
				 });
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressbook;
	}

	private Record dataToObject(String[] recordLineArr) 
	{
		List<String> data = new ArrayList<>();
		for(String cell : recordLineArr )
		{
			String [] processedData = cell.trim().split("=");
			data.add(processedData[1]);
		}
		
		return new Record(data.get(0),
						data.get(1),
						data.get(2),
						data.get(3),
						data.get(4),
						data.get(5),
						Integer.parseInt(data.get(6)),
						Long.parseLong(data.get(7)));
	 }
}
