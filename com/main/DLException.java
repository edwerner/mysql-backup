package com.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class DLException extends Exception {

	public DLException(Exception e) {
		super(e);
	}

	public DLException(Exception e, String... values) {
		super(e);
		try {
			writeLog(e, values);
		} catch (IOException e1) {
			System.out.println("There was an error completing an operation.");
		}
	}

	// write errors to log file by
	// parsing exception and string 
	// values
	public void writeLog(Exception e, String... values) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("ErrorLog.txt", true));
		try {
			// create new simple date format
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss a z");
			Date date = new Date(System.currentTimeMillis());
			writer.append("DateTime: " + formatter.format(date));

			// create stacktrace element array
			StackTraceElement[] stackTrace = e.getStackTrace();
			int count = 0;

			for (int i = 0; i < stackTrace.length; i++) {
				if (stackTrace[i].getClassName().contains("com.main") && count == 0) {
					writer.append("\n");
					writer.append("Class name: " + stackTrace[i].getClassName());
					writer.append("\n");
					writer.append("Line number: " + stackTrace[i].getLineNumber());
					writer.append("\n");
					writer.append("Error message: " + e.getMessage());
					writer.append("\n\n");
					count++;
				}
			}
		} catch (IOException e2) {
			System.out.println("There was an error completing an operation.");
		} finally {
			writer.close();
		}
	}
}