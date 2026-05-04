package application;
// This class to save list of month data
public class LinkedList {
    // Pointer to first node.
    private Node first;
    // Pointer to last node.
    private Node last;
    // size of list
    int count;

    // Method to get the first Node in the list
    public Node getFirst() {
        return first;
    }

    // Method to get the last Node in the list
    public Node getLast() {
        return last;
    }
    // This method to add month in the first place in this list.
    public Node addFirst(int month) {
        Node newNode = new Node(month);
        if (count == 0) {
            first = last = newNode;
        } else {
            Node temp = newNode;
            temp.setNext(first);
            first = temp;
        }
        count++;
        return newNode;
    }
    // This method to add month in the last place in this list.
    public Node addLast(int month) {
        Node newNode = new Node(month);
        if (count == 0) {
            first = last = newNode;
        } else {
            Node temp = newNode;
            last.setNext(temp);
            last = temp;
        }
        count++;
        return newNode;
    }
    // This method to add month in the index place in this list.
    public Node add(int month, int index) {
        if (index == 0) {
            return addFirst(month);
        } else {
            if (index >= count) {
                return addLast(month);
            } else {
                Node temp = new Node(month);
                Node current = first;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                temp.setNext(current.getNext());
                current.setNext(temp);
                count++;
                return temp;
            }
        }
    }
    // This method to remove first month in this list.
    public boolean removeFirst() {
        if (count == 0) {
            return false;
        } else {
            if (count == 1) {
                first = last = null;
            } else {
                first = first.getNext();
            }
            count--;
            return true;
        }
    }
    // This method to remove last month in this list.
    public boolean removeLast() {
        if (count == 0) {
            return false;
        } else {
            if (count == 1) {
                last = first = null;
            } else {
                Node current = first;
                for (int i = 0; i < count - 2; i++) {
                    current = current.getNext();
                }
                last = current;
                current.setNext(null);
                ;
            }
            count--;
            return true;
        }
    }

    // This method to remove index place month in this list.
    public boolean remove(int index) {
        if (index < 0 || index > count) {
            return false;

        } else if (index == 0) {
            return removeFirst();

        } else if (index == count) {
            return removeLast();

        } else {
            Node current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }

            current.setNext(current.getNext().getNext());
            count--;
            return true;
        }

    }
     //This method return the searched node by month
    public Node getNode(int index) {
        Node curr = first;
        for (int i = 0; i < index && curr != null; i++) {
            curr = curr.getNext();
        }
        return curr;
    }
 // print the all elements in the list
    public void printList() {
        Node current = first;
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(current.toString());
            current = current.getNext();
        }
    }

    @Override
    public String toString() {
        String str = "";
        Node cur = first;
        while (cur != null) {
            str += "  " + (cur == null ? "" : cur) + "\n";
            cur = cur.getNext();
        }
        return "{\n" + str + "  }";
    }
//Adds an Electricity record to the appropriate month
    public boolean addRecord(Electricity record) {
        int month = record.getDate().getMonthValue();

        if (count != 0) {
            Node monthNode = first;
            while (monthNode != null) {
                if (monthNode.getMonth() == month) {
                    return monthNode.getDays().addRecord(record);
                }
                monthNode = monthNode.getNext();
            }
        }
        return false;
    }
//Edits an Electricity record for a specific month
    public boolean editRecord(Electricity record) {
        int month = record.getDate().getMonthValue();
        if (count != 0) {
            Node monthNode = first;
            while (monthNode != null) {
                if (monthNode.getMonth() == month) {
                    return monthNode.getDays().editRecord(record);
                }
                monthNode = monthNode.getNext();
            }
        }
        return false;
    }

}