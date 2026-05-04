package application;


//This class for save data of month with linked list of day and the next node
public class Node {

	private int month;
	private DayLinkedList day;
	private Node next;

	//non argument constructor
	public Node() {
		super();
	}
	// Constructor to make objects of node with month data
	public Node(int month) {
		super();
		this.month = month;
		day = new DayLinkedList();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public DayLinkedList getDays() {
		return day;
	}

	public void setDay(DayLinkedList day) {
		this.day = day;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return getMonth() + " : " +day == null ? "{}" : day.toString();
	}

}