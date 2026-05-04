package application;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// The FileOperations class provides functionality for loading and writing electricity data to and from a file 
public class FileOperations {

	// Replace with the actual file path
	public final static String DATA_FILE_PATH = "C:\\Users\\EasyLife\\Downloads\\Electricity.csv";

	public static void loadData() {
		loadData(new File(DATA_FILE_PATH));
	}

	public static void loadData(File file) {
		List<Electricity> electricities = readData(file != null ? file : new File(DATA_FILE_PATH));
		Data.appendRecords(electricities);
	}

	//Reads electricity data from the specified file
	private static List<Electricity> readData(File file) {
		List<Electricity> electricities = new ArrayList<>();
		try (Scanner in = new Scanner(file)) {
			in.nextLine();
			while (in.hasNext()) {
				String[] tokens = in.nextLine().split(",");
				Electricity record = new Electricity(
						LocalDate.parse(tokens[0]),
						Float.parseFloat(tokens[1]),
						Float.parseFloat(tokens[2]),
						Float.parseFloat(tokens[3]),
						Float.parseFloat(tokens[4]),
						Float.parseFloat(tokens[5]),
						Float.parseFloat(tokens[6]),
						Float.parseFloat(tokens[7]));
				electricities.add(record);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return electricities;
	}

	//The file to which the data will be written.
	public static void writeData(File file) {
		try (PrintStream writer = new PrintStream(file)) {
			writer.println(
					"Date,Israeli_Lines_MWs,Gaza_Power_Plant_MWs,Egyptian_Lines_MWs,Total_daily_Supply_available_in_MWs,Overall_demand_in_MWs,Power_Cuts_hours_day_400mg,Temp");
			//write each record to the file
			DoubleLinkedList years = Data.getDataLinkedList();
			DoubleNode year = years.getFirst();

			while (year != null) {
				LinkedList months = year.getMonths();
				Node month = months.getFirst();

				while (month != null) {
					DayLinkedList days = month.getDays();
					DayNode day = days.getFirst();

					while (day != null) {
						String recordLine = day.getRecord().toFileString();
						writer.println(recordLine);
						day = day.getNext();
					}
					month = month.getNext();
				}
				year = year.getNext();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void writeData() {
		writeData(new File(DATA_FILE_PATH));
	}
}