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

		// fetch table names
		ArrayList<String> tableNames = backup.fetchTable(null);
		for (String name : tableNames) {
			System.out.println(name);
		}
		System.out.println("----------------------------------------------------");

		System.out.println("Please enter a valid table name");

		System.out.println("----------------------------------------------------");

		// Enter data using Scanner
		scanner = new Scanner(System.in);

		name = scanner.next();

		tableExists(backup.fetchTable(name));
	}

	public static ArrayList<String> tableExists(ArrayList<String> tableData) {

		ArrayList<String> showTable = new ArrayList<String>();
		// check if queried table exists
		if (tableData.size() > 0) {
			System.out.println(tableData);
			System.out.println("----------------------------------------------------");
//			showTable = tableExists(backup.fetchTable(null));
			
			// close database connection
			backup.close();
		} else {
			System.out.println("----------------------------------------------------");
			System.out.println("Please enter a valid table name");
			System.out.println("----------------------------------------------------");
			name = scanner.next();

			// recursive method call
			showTable = tableExists(backup.fetchTable(name));
		}
		return showTable;
	}
}