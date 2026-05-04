package application;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//It includes methods for searching, appending, editing, initializing, and deleting electricity records.

public class Data {
	public final static int FIRST_YEAR = 2017;
	public final static int LAST_YEAR = 2023;

	private static final DoubleLinkedList data = new DoubleLinkedList();

	public static DoubleLinkedList getDataLinkedList() {
		return data;
	}
	//Checks if a given LocalDate matches the specified search criteria based on a formatted search field.
	private static boolean isDateMatchs(LocalDate date, String searchField) {
		searchField = searchField.replaceAll(" ", "").replaceAll("-", "/") + "/ / /";//The search field containing the date criteria
		//Extract year, month, and day components from the formatted search field
		String year = searchField.split("/")[0].trim();
		String month = searchField.split("/")[1].trim();
		String day = searchField.split("/")[2].trim();
		// Check if each component of the date matches the corresponding component in the search criteria
		boolean yearMatches = year == "" || date.getYear() == Integer.parseInt(year);
		boolean monthMatches = month == "" || date.getMonthValue() == Integer.parseInt(month);
		boolean dayMatches = day == "" || date.getDayOfMonth() == Integer.parseInt(day);
		// Return true if all components match
		return yearMatches && monthMatches && dayMatches;
	}

	// Searches for electricity records within a specified range and based on a search field.
	public static List<Electricity> searchInData(int fromIndex, int toIndex, String searchField) {
		int indexCounter = 0;
		List<Electricity> data = new ArrayList<>();

		DoubleNode yearNode = Data.data.getFirst();
		while (yearNode != null) {

			Node monthNode = yearNode.getMonths().getFirst();
			while (monthNode != null) {

				DayNode dayNode = monthNode.getDays().getFirst();
				while (dayNode != null) {

					Electricity record = dayNode.getRecord();
					if (record != null) {
						// Check if the record matches the search criteria based on date
						if (searchField == null || searchField.isEmpty() ||  
								isDateMatchs(record.getDate(), searchField)) {
							// Check if the record falls within the specified index range
							if (indexCounter < fromIndex) {
								indexCounter++;
							} else if (indexCounter <= toIndex || toIndex < 0) { 
								indexCounter++;

								data.add(record); 
							} else {
								return data;  //return A list of Electricity records that match the search criteria and fall within the specified index range.

							}
						}
					}
					dayNode = dayNode.getNext();
				}
				monthNode = monthNode.getNext();
			}
			yearNode = yearNode.getNext();
		}
		return data;
	}

	public static void appendRecords(List<Electricity> records) {
		for (Electricity record : records) {
			appendRecord(record);
		}
	}

	public static void editRecords(List<Electricity> records) {
		for (Electricity record : records) {
			editRecord(record);
		}
	}

	public static boolean appendRecord(Electricity record) {
		return data.addRecord(record);
	}

	public static boolean editRecord(Electricity record) {
		return data.editRecord(record);
	}
	//Initialize the data structure with a default organ of years, months, and days.
	public static void initTheDataList() {
		for (int year = FIRST_YEAR; year <= LAST_YEAR; year++) {

			LinkedList monthList = new LinkedList();
			for (int month = 1; month <= 12; month++) {

				DayLinkedList dayList = new DayLinkedList();
				for (int day = 1; day <= 31; day++) {
					dayList.addLast(day);   // add day
				}
				Node monthNode = monthList.addLast(month);
				monthNode.setDay(dayList); //add day on month
			}
			DoubleNode yearNode = data.addLast(year);
			yearNode.setMonths(monthList);   //add month on year
		}
	}
	//Deletes the specified electricity record,find the record matching the provided one and removes it from the data structure
	public static boolean deleteRecord(Electricity record) {   // first it searching for the record
		DoubleNode yearNode = Data.data.getFirst();
		while (yearNode != null) {
			Node monthNode = yearNode.getMonths().getFirst();
			while (monthNode != null) {
				DayNode dayNode = monthNode.getDays().getFirst();
				while (dayNode != null) {
					Electricity currentRecord = dayNode.getRecord();
					if (currentRecord != null) {

						if (currentRecord.compareTo(record) == 0) { //Compare the current record with the specified record
							dayNode.setRecord(null);  //If a match is found, delete the record by setting it to null
							return true;
						}
					}
					dayNode = dayNode.getNext();
				}
				monthNode = monthNode.getNext();
			}
			yearNode = yearNode.getNext();
		}
		return false; // If the record was not found 
	}
}