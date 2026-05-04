package application;

//This class to save list of year data
public class DoubleLinkedList {
	// Pointer to first double node.
	private DoubleNode first;
	// Pointer to last double node.
	private DoubleNode last;
	// size of list
	private int count;

	// Method to get the first DoubleNode in the list
	public DoubleNode getFirst() {
		return first;
	}

	// Method to get the last DoubleNode in the list
	public DoubleNode getLast() {
		return last;
	}

	// This method to add year in the first place in this list
	public DoubleNode addFirst(int year) {
		DoubleNode newNode = new DoubleNode(year);
		if (count == 0) {
			first = last = newNode;
		} else {
			newNode.setNext(first);
			first.setPre(newNode);
			first = newNode;
		}
		count++;
		return newNode;
	}

	// This method to add year in the last place in this list
	public DoubleNode addLast(int year) {
		DoubleNode newNode = new DoubleNode(year);
		if (count == 0) {
			first = last = newNode;
		} else {
			last.setNext(newNode);
			newNode.setPre(last);
			last = newNode;
		}
		count++;
		return newNode;
	}

	// this method to add the year dependent to the index
	public DoubleNode add(int year, int index) {
		if (index == 0) {
			return addFirst(year);
		} else {
			if (index >= count) {
				return addLast(year);
			} else {
				DoubleNode temp = new DoubleNode(year);
				DoubleNode current = first;
				for (int i = 0; i < index; i++) {
					current = current.getNext();
				}
				temp.setNext(current.getNext());
				temp.setPre(current);
				current.setNext(temp);
				temp.getNext().setPre(temp);
				count++;
				return temp;
			}
		}
	}

	// This method to remove first year in this list.
	public boolean removeFirst() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				last = first = null;
			} else {
				first = first.getNext();
				first.setPre(null);
			}
			count--;
			return true;
		}

	}

	// This method to remove last year in this list.
	public boolean removeLast() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				last = first = null;
			} else {
				last = last.getPre();
				last.setNext(null);

			}
			count--;
			return true;
		}
	}
	// this method to remove the year specifies the index
	public boolean remove(int index) {
		if (count == 0) {
			return false;
		} else {
			if (index == 0) {
				return removeFirst();
			} else {
				if (index == count - 1) {
					return removeLast();
				}
				if (index < 0 || index >= count) {
					return false;
				} else {
					DoubleNode current = first;
					for (int i = 0; i < index - 1; i++) {
						current = current.getNext();
					}
					current.setNext((current.getNext()).getNext());
					count--;
					return true;
				}
			}
		}
	}

	public boolean search(DoubleNode first, int index) {
		if (first == null) {
			return false;
		}

		DoubleNode current = first;
		while (current != null) {
			if (index == count) {
				return true;
			}
			current.setNext(current);
		}
		print(current);
		return false;

	}

	public void print(DoubleNode first) {
		DoubleNode cur = first;
		while (cur != null) {
			System.out.print(cur.getYear() + "--->");
			cur = cur.getNext();
		}
		System.out.println();
	}
	// This method to get search node by year
	public DoubleNode getNode(int index) {
		if (index < count) {
			DoubleNode curr = first;
			for (int i = 0; i < index; i++) {
				curr = curr.getNext();
			}
			return curr;
		} else {
			return null;
		}

	}
	// Method to get the size of the doubly linked list
	public int getCount() {
		return count;
	}

	// print the all elements in the list
	public void printList() {
		DoubleNode current = first;
		if (count == 0) {
			return;
		}
		for (int i = 0; i < count; i++) {
			System.out.println(current.toString());
			current = current.getNext();
		}
	}

	@Override //Returns a string representation of the data structure.
	public String toString() {
		String str = "";
		DoubleNode cur = first;
		while (cur != null) {
			str += " " + (cur == null ? "" : cur) + "\n";
			cur = cur.getNext();
		}
		return "{\n" + str + "}";
	}

	//Edits an Electricity record for a specific year , موجود يتم التعديل عليه
	public boolean editRecord(Electricity record) {
		int year = record.getDate().getYear();

		if (count != 0) {
			DoubleNode yearNode = first;
			while (yearNode != null) {
				if (yearNode.getYear() == year) { 
					return yearNode.getMonths().editRecord(record);
				}
				yearNode = yearNode.getNext();
			}
		}
		return false;
	}
	//Adds an Electricity record to the appropriate year , مش موجود بدي اضيفه كامل
	public boolean addRecord(Electricity record) {
		int year = record.getDate().getYear();
		if (count != 0) {
			DoubleNode yearNode = first;
			while (yearNode != null) {
				if (yearNode.getYear() == year) {
					return yearNode.getMonths().addRecord(record);
				}
				yearNode = yearNode.getNext();
			}
		}
		return false;
	}

}