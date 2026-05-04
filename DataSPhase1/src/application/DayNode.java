package application;

//This class for save data of record and the next node
public class DayNode {

	private Electricity record;
	private int day;
	private DayNode next;

	// Constructor to make objects of node with record data
	public DayNode(Electricity record) {
		this.record = record;
		this.day = record.getDate().getDayOfMonth();
	}
	// Constructor to make objects of node with day data
	public DayNode(int day) {
		this.day = day;
	}

	public Electricity getRecord() {
		return record;
	}

	public void setRecord(Electricity record) {
		this.record = record;
		day = record != null ? record.getDate().getDayOfMonth() : day;
	}

	public int getDay() {
		return day;
	}

	public DayNode getNext() {
		return next;
	}

	public void setNext(DayNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return getDay() + " : " + (record == null ? "{}" : record.toString());
	}

}