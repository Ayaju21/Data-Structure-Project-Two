package application;

//This class to save list of day data
public class DayLinkedList {
	// Pointer to first node.
	private DayNode first;
	// Pointer to last node.
	private DayNode last;
	// size of list
	int count;

	// Method to get the first Node in the list
	public Electricity getFirstRecord() {
		if (count == 0)
			return null;
		else
			return first.getRecord();
	}

	// Method to get the last Node in the list
	public Electricity getLastRecord() {
		if (count == 0)
			return null;
		else
			return last.getRecord();
	}
	// Method to get the first Node in the list
	public DayNode getFirst() {
		return first;
	}
	// Method to get the last Node in the list
	public DayNode getLast() {
		return last;
	}

	// This method to add Electricity in the first place in this list.
	public void addFirst(Electricity record) {
		if (count == 0) {
			first = last = new DayNode(record);

		} else {
			DayNode tmp = new DayNode(record);
			tmp.setNext(first);
			first = tmp;
		}
		count++;
	}

	// This method to add day in the first place in this list.
	public void addFirst(int day) {
		if (count == 0) {
			first = last = new DayNode(day);

		} else {
			DayNode tmp = new DayNode(day);
			tmp.setNext(first);
			first = tmp;
		}
		count++;
	}

	// This method to add Electricity in the last place in this list.
	public void addLast(Electricity record) {
		if (count == 0)
			first = last = new DayNode(record);
		else {
			DayNode temp = new DayNode(record);
			last.setNext(temp);
			last = temp;
		}
		count++;
	}

	// This method to add day in the last place in this list.
	public void addLast(int day) {
		if (count == 0)
			first = last = new DayNode(day);
		else {
			DayNode temp = new DayNode(day);
			last.setNext(temp);
			last = temp;
		}
		count++;
	}

	// This method to add Electricity in the index place in this list.
	public void add(Electricity record, int index) {
		if (index == 0)
			addFirst(record);
		else {
			if (index >= count)
				addLast(record);
			else {
				DayNode temp = new DayNode(record);
				DayNode current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.getNext();
				}
				temp.setNext(current.getNext());
				current.setNext(temp);
				count++;
			}
		}
	}

	// This method to add Electricity in the place
	public void add(Electricity record) {
		DayNode curr = first;
		int i = 0;
		while (curr != null && (curr.getRecord().compareTo(record) < 0)) {
			curr = curr.getNext();
			i++;
		}
		add(record, i);
	}

	// This method to remove first Electricity day in this list.
	public boolean removeFirst() {

		if (count == 0) {
			return false;

		} else {
			if (count == 1) {
				last = first = null;
			}

			else {
				first = first.getNext();
			}
			count--;
			return true;
		}

	}

	// This method to remove last Electricity in this list.
	public boolean removeLast() {

		if (count == 0) {
			return false;

		} else {
			if (count == 1) {
				last = first = null;
			}

			else {
				DayNode current = first;
				for (int i = 0; i < count - 2; i++)
					current = current.getNext();

				last = current;
				last.setNext(null);

			}
			count--;

			return true;
		}

	}

	// This method to remove index place Electricity in this list.
	public boolean remove(int index) {
		if (count == 0)
			return false;
		else {
			if (index == 0)
				return removeFirst();
			else {
				if (index == count - 1)
					return removeLast();
				if (index < 0 || index >= count)
					return false;
				else {
					DayNode current = first;
					for (int i = 0; i < index - 1; i++)
						current = current.getNext();
					current.setNext((current.getNext()).getNext());
					count--;
					return true;
				}
			}
		}
	}

	// This method to remove input Electricity in this list.
	public boolean remove(Object day) {
		DayNode current = null;
		if (count != 0) {
			if (day.equals(first.getDay())) {
				return removeFirst();
			} else if (day.equals(last.getDay())) {
				return removeLast();
			} else {
				current = first;
				for (int i = 0; i < count - 1; i++) {
					if (day.equals(current.getDay())) {
						return remove(i);
					}
					current = current.getNext();
				}
			}
		}
		return false;
	}

	// This method to search Electricity in this list.
	public boolean search(Object o) {

		DayNode current = first;

		for (int i = 0; i < count; i++) {
			if (current.getRecord().equals(o)) {
				return true;
			}
			current = first.getNext();
		}
		return false;

	}
	//This method return the searched node by day
	public DayNode getNode(int index) {
		DayNode curr = first;
		for (int i = 0; i < index && curr != null; i++) {
			curr = curr.getNext();
		}
		return curr;
	}

	@Override
	public String toString() {
		String str = "";
		DayNode cur = first;
		while (cur != null) {
			str += "   " + (cur == null ? "" : cur) + "\n";
			cur = cur.getNext();
		}
		return "{\n" + str + "   }";
	}
	// print the all elements in the list
	public void printList() {
		DayNode current = first;
		if (count == 0)
			return;
		for (int i = 0; i < count; i++) {
			System.out.println(current.toString());
			current = current.getNext();
		}
	}
	//Adds an Electricity record to the appropriate day
	public boolean addRecord(Electricity record) {
		int day = record.getDate().getDayOfMonth();

		if (count != 0) {
			DayNode dayNode = first;
			while (dayNode != null) {
				if (dayNode.getDay() == day) {
					if (dayNode.getRecord() == null) { // not fount add the record 
						dayNode.setRecord(record);
						return true;
					} else {
						return false;
					}
				}
				dayNode = dayNode.getNext();
			}
		}
		return false;
	}
	//Edits an Electricity record for a specific day
	public boolean editRecord(Electricity record) {
		int day = record.getDate().getDayOfMonth();

		if (count != 0) {
			DayNode dayNode = first;
			while (dayNode != null) {
				if (dayNode.getDay() == day) {
					dayNode.setRecord(record);
					return true;
				}
				dayNode = dayNode.getNext();
			}
		}
		return false;
	}

}