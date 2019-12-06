package com.main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Backup backup;
	private static String name = "";
	private static Scanner scanner;

	public static void main(String[] args) {

		// create new backup instance
		backup = new Backup();
		
		// connect to database
		backup.connect();
		
		// log table names
		logTableNames();

		// format console output
		System.out.println("----------------------------------------------------");
		System.out.println("Please enter a valid table name");
		System.out.println("----------------------------------------------------");

		// Enter console data using scanner
		scanner = new Scanner(System.in);
		name = scanner.next();
		
		// fetch table data
		tableExists(backup.fetchTable(name));
		
		// close database connection
		backup.close();
	}

	/**
	 * Fetch and log table names
	 */
	private static void logTableNames() {
		
		// fetch table names and
		// format console output
		ArrayList<String> tableNames = backup.fetchTable(null);

		System.out.println("----------------------------------------------------");
		
		for (String name : tableNames) {
			// print out table name
			System.out.println(name);
		}
	}

	/**
	 * Check if table exists
	 * 
	 * @param tableData
	 * @return
	 */
	public static ArrayList<String> tableExists(ArrayList<String> tableData) {

		// instantiate table data array list
		ArrayList<String> showTable = new ArrayList<String>();
		
		// check if queried table exists
		if (tableData.size() > 0) {
			System.out.println(tableData.get(1));
			// log table names
			logTableNames();
			
			System.out.println("----------------------------------------------------");
			System.out.println("Please enter a valid table name");
			System.out.println("----------------------------------------------------");
			
			// get console input
			name = scanner.next();
			
			// open database connection
			backup.connect();
			
			// recursive method call
			showTable = tableExists(backup.fetchTable(name));
			
			// close database connection
			backup.close();
		} else {
			// open database connection
			backup.connect();
			
			// log table names
			logTableNames();

			System.out.println("----------------------------------------------------");
			System.out.println("Please enter a valid table name");
			System.out.println("----------------------------------------------------");
			
			// get console input
			name = scanner.next();
			
			// recursive method call
			showTable = tableExists(backup.fetchTable(name));

			// close database connection
			backup.close();
		}
		
		// return collection list
		return showTable;
	}
}