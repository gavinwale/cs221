import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {

	/**
	 * @TODO try every time newNode is tail making it's next null
	 */
	private int count; // Number of elements
	private SLLNode<T> head, tail; // first, last in list
	private int modCount; // Number of modifications

	/**
	 * Default constructor, empty list
	 */
	public IUSingleLinkedList() {
		count = 0;
		modCount = 0;
		head = null;
		tail = null;
	}

	// Not done
	@Override
	public void addToFront(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);

		if (count == 0) { // If there is nothing, set head to newNode
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
		count++;
		modCount++;

	}

	// Done
	@Override
	public void addToRear(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);

		if (count == 0) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}

		tail = newNode;
		count++;
		modCount++;

	}

	// Done
	@Override
	public void add(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);

		if (count == 0) {
			head = newNode; // If empty, head is the first element
		} else {
			tail.setNext(newNode); // Sets newNode to the next after the last
		}

		tail = newNode; // Sets tail to the newNode
		count++; // Increases number of elements
		modCount++; // Increases number of mods
	}

	// Not done: use contains, index of, and add.
	@Override
	public void addAfter(T element, T target) {
		// Check if element is in list
		// Go to element in list
		// Insert
		if (count == 0) {
			throw new NoSuchElementException("Target element does not exist");
		}

		boolean found = false;
		SLLNode<T> current = head;
		SLLNode<T> newNode = new SLLNode<T>(element);
		while ((current != null) & !found) {
			if (target.equals(current.getElement())) {
				// SLLNode<T> insertNode = current; // insertNode = current = target
				
				if (current == head && current == tail) {
					
					current.setNext(newNode);
					tail = newNode;
					newNode.setNext(null);
					
				} else if (current == head) {
					
					newNode.setNext(head.getNext());
					head.setNext(newNode);
					
				} else if (current == tail) {
					
					tail.setNext(newNode);
					tail = newNode;
					newNode.setNext(null);
					
				} else {
					
					newNode.setNext(current.getNext());
					current.setNext(newNode);
					
				}

				found = true; // found = true if the element exists in the list
				count++;
				modCount++;
				
			} else {current = current.getNext();}

		}
		
		if (!found) {
			
			throw new NoSuchElementException("Target element does not exist");
			
		}
		
	}

	// Done
	@Override
	public void add(int index, T element) {
		// check for valid index
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("Can't insert, invalid index");
		}

		SLLNode<T> newNode = new SLLNode<T>(element);

		if (index == 0) {
			newNode.setNext(head); // this could be an issue. 
			head = newNode;
			if (count == 0) { // this logic is off? not sure
				tail = newNode;
			}
		} else {
			SLLNode<T> current = head;
			// traverse to node prior at location index
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}

			SLLNode<T> next = current.getNext();
			current.setNext(newNode);
			newNode.setNext(next);
			if (index == count) { // this could be a major issue
				tail = newNode;
			}
		}

		count++;
		modCount++;

	}

	// Done
	@Override
	public T removeFirst() {

		if (count == 0) {
			throw new NoSuchElementException("No element to remove");
		}

		T retVal = head.getElement();

		head = head.getNext();

		count--;
		modCount++;
		return retVal;

	}

	// Done
	@Override
	public T removeLast() {
		
		if (count == 0) { // If the list is empty
			throw new NoSuchElementException("No element to remove"); // Nothing to remove
		}
		
		return remove(tail.getElement());
		

//		
//		SLLNode<T> previous = null;
//		SLLNode<T> theNode = head;
//
//		while ((theNode != tail)) { // I was right all along. Stupid null pointer exception I knew it
//			
//			previous = theNode;
//			theNode = theNode.getNext();
//			//previous = previous.getNext();
//			//theNode.setNext(previous);
//		}
//
//		T retVal = theNode.getElement();
//
//		tail = previous;
//		tail.setNext(null); //leaving this out for now
//
//		count--;
//		modCount++;
//		return retVal;
		
//		if (count == 0) { // If the list is empty
//			throw new NoSuchElementException("No element to remove"); // Nothing to remove
//		}
//		
//		SLLNode<T> previous = null;
//		SLLNode<T> current = head;
//		
//		T retVal = current.getElement();
//		
//		if (head.getNext() == null) {
//			
//			head = null;
//			retVal = head.getElement();
//		}
//		
//		while (previous.getNext().getNext() != null) {
//			previous = previous.getNext();
//		}
//		
//		previous.getNext().setNext(null);
//		tail = previous.getNext();
//
//		return retVal;

//		
//		
//		if (count == 0) { // If the list is empty
//		throw new NoSuchElementException("No element to remove"); // Nothing to remove
//	}
//		
//		
//		SLLNode<T> last = head;
//		SLLNode<T> secondToLast = null;
//		
//		if (count == 1) {
//			
//			last.setElement(null);
//			T retVal = last.getElement(); // HEAD ISNT BEING RESET
//			
//		} else {
//		
//		while(last.getNext() != null) {
//			secondToLast = last;
//			last = last.getNext();
//		}
//		
//
//		
//		SLLNode<T> temp = secondToLast.getNext();
//		temp = null;
//		T retVal = last.getElement();
//		return retVal;
//		}
//		return null;
		
		
		
	}

	// Done
	@Override
	public T remove(T element) {

		boolean found = false;
		SLLNode<T> current = head; // head is where it starts
		SLLNode<T> previous = null; // previous is before head, null

		while ((current != null) & !found) { // while current isn't null and it isn't found
			if (element.equals(current.getElement())) { // if given element equals current
				found = true; // we have found the element
			} else { // if not
				previous = current; // previous is equal to current
				current = current.getNext(); // current moves on
			}
		}

		if (!found) { // If item was never found, throw exception
			throw new NoSuchElementException("Can't remove item, not in list");
		}

		SLLNode<T> next = current.getNext();

		if (current == head) { // if head is current
			head = next; // moves head up one spot
		} else {
			previous.setNext(next);
		}

		if (current == tail) {
			tail = previous; // moves tail down one
		}
		current.setNext(null); // sets what was current to null

		count--;
		modCount++;

		return current.getElement();

	}

	// Done
	@Override
	public T remove(int index) {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("Can't remove, invalid index");
		}

		T element = null;
		if (index == 0) { // If removing the first element in the list
			SLLNode<T> next = head.getNext(); // Next equals the node after head
			head.setNext(null); // Sets next to null
			element = head.getElement(); // Sets element to head
			head = next; // Head is equal to next (what was after head originally)
			if (count == 1) {
				tail = null;
			}

		} else {
			SLLNode<T> current = head; // Current = head
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext(); // Iterate until index is met
			}
			SLLNode<T> next = current.getNext(); // Set next to the next node
			current.setNext(next.getNext()); // Sets the next of current to next
			next.setNext(null); // Removes the element after next
			element = next.getElement(); // Sets element to next
			if (index == count - 1) { // If removing the last element
				tail = current; // Move tail properly
			}
		}

		count--; // Decrement count
		modCount++; // Increment modCount

		return element;
	}

	// Done
	@Override
	public void set(int index, T element) {

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("Can't set, invalid index");
		}

		SLLNode<T> current = head;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		current.setElement(element);
		modCount++;

	}

	// Done
	@Override
	public T get(int index) {

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("Can't get, invalid index");
		}

		SLLNode<T> current = head;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();

	}

	// Done
	@Override
	public int indexOf(T element) {

		boolean found = false;
		SLLNode<T> current = head;
		int index = 0;

		while ((current != null) & !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				current = current.getNext();
				index++;
			}
		}

		if (!found) {
			index = -1;
		}
		return index;
	}

	// Done
	@Override
	public T first() {

		if (count == 0) {
			throw new NoSuchElementException("Empty list");
		}

		return head.getElement();
	}

	// Done
	@Override
	public T last() {

		if (count == 0) {
			throw new NoSuchElementException("Empty list");
		}

		return tail.getElement();
	}

	// Done
	@Override
	public boolean contains(T target) {
		boolean found = false;
		SLLNode<T> current = head;

		while ((current != null) & !found) {
			if (target.equals(current.getElement())) {
				found = true;
			} else {
				current = current.getNext();

			}
		}

		if (!found) {
			return false;
		}
		return found;
	}

	// Done
	@Override
	public boolean isEmpty() {

		if (count == 0) {
			return true;
		} else {
			return false;
		}

	}

	// Done
	@Override
	public int size() {
		return count;
	}

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Iterator for the IUSingleLinkedList - implements Iterator interface
	 * 
	 * @author gavinwale
	 *
	 */
	private class SLLIterator implements Iterator<T> {

		private SLLNode<T> next;
		private SLLNode<T> current;
		private SLLNode<T> previous;
		private int itrModCount;
		private boolean canRemove;

		public SLLIterator() {
			next = head;
			current = null;
			previous = null;
			itrModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			
//			if (itrModCount != modCount) {
//				throw new ConcurrentModificationException("can't call next, changes made");
//			}
			
			return (next != null); // Returns true or false whether or not
		} // next is equal to null

		@Override
		public T next() {

			if (itrModCount != modCount) {
				throw new ConcurrentModificationException("Can't call next, changes made");
			}

			if (!hasNext()) {
				throw new NoSuchElementException("Can't call next, end of iteration");
			}

			previous = current;
			current = next;
			next = next.getNext();
			canRemove = true;

			return current.getElement();
		}

		public void remove() {

			if (!canRemove) {
				throw new IllegalStateException("Can't remove, haven't called next");
			}

			if (itrModCount != modCount) {
				throw new ConcurrentModificationException("Can't call remove, changes made");
			}

			current.setNext(null);
			if (current == head) {
				head = next;
				current = null;
			} else {
				previous.setNext(next);
				current = previous;
			}

			if (current == tail) {
				tail = previous;
			}

			canRemove = false;

			count--;

			modCount++;
			itrModCount++;
		}

	}

}
