package application;

//This class for save data of year with linked list of month and the next node
public class DoubleNode {

	// Attributes of double node
	private int year;
	private LinkedList months;
	private DoubleNode pre;
	private DoubleNode next;
     
	//non argument constructor
	public DoubleNode() {
		super();
	}
	// Constructor to make objects of double node with year data and initialize the
	// linked list
	public DoubleNode(int year) {
		super();
		this.year = year;
		months = new LinkedList();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public LinkedList getMonths() {
		return months;
	}

	public void setMonths(LinkedList months) {
		this.months = months;
	}

	public DoubleNode getPre() {
		return pre;
	}

	public void setPre(DoubleNode pre) {
		this.pre = pre;
	}

	public DoubleNode getNext() {
		return next;
	}

	public void setNext(DoubleNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return getYear() + " : " + months == null ? "{}" : months.toString();
	}

}