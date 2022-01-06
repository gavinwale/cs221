import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * A double linked list using nodes and 
 * implementing IndexedUnsortedList
 * 
 * @author gavinwale
 *
 * @param <T>
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

	private int count; // number of elements
	private DLLNode<T> head, tail; // first, last in list
	private int modCount; // number of modifications

	/**
	 * Default constructor, empty list
	 */
	public IUDoubleLinkedList() {
		count = 0;
		modCount = 0;
		head = null;
		tail = null;
	}

	@Override
	public void addToFront(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);

		if (count == 0) { // if there are no elements
			tail = newNode; // make newNode tail
		} else {
			head.setPrevious(newNode); // previous to front is newNode
			newNode.setNext(head); // next to newNode is front
		}

		head = newNode; // front is newNode
		count++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);

		if (count == 0) {
			head = newNode;
		} else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
		}

		tail = newNode;
		count++;
		modCount++;
	}

	@Override
	public void add(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);

		if (count == 0) {
			head = newNode;
		} else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
		}

		tail = newNode;
		count++;
		modCount++;
	}

	@Override
	public void addAfter(T element, T target) {

		if (count == 0) { // if there are no elements

			throw new NoSuchElementException("Target element does not exist"); // throw exception

		}

		boolean found = false; // found variable
		DLLNode<T> current = head; // current is head
		DLLNode<T> newNode = new DLLNode<T>(element); // new node is element parameter

		while ((current != null) & !found) { // while current is not null and not found

			if (target.equals(current.getElement())) { // if target element is the same as current

				if (current == head && current == tail) { // if there is only 1 node
					// better to say if count == 1? or head = tail?

					newNode.setPrevious(current); // current is before newNode
					current.setNext(newNode); // newNode is after current
					tail = newNode; // newNode is tail

				} else if (current == head) { // if current is head
					// THIS MAY NOT BE NEEDED AS ELSE WILL COVER IT

					newNode.setPrevious(head); // head is before newNode
					newNode.setNext(head.getNext()); // the node after head is after newNode
					newNode.getNext().setPrevious(newNode); // newNode is before the node after newNode
					head.setNext(newNode); // newNode is after head

				} else if (current == tail) { // if current is tail

					newNode.setPrevious(tail); // tail is before newNode
					tail.setNext(newNode); // newNode is after tail
					// newNode.setNext(null); // null after newNode
					tail = newNode; // newNode is tail

				} else {

					newNode.setPrevious(current); // current is before newNode
					newNode.setNext(current.getNext()); // the node after current is after newNode
					newNode.getNext().setPrevious(newNode); // newNode is before the node after newNode
					current.setNext(newNode); // newNode is after current

				}

				found = true;
				count++;
				modCount++;

			} else {

				current = current.getNext();

			}

		}

		if (!found) {

			throw new NoSuchElementException("Target element does not exist");

		}

	}

	@Override
	public void add(int index, T element) {

		if (index < 0 || index > count) {

			throw new IndexOutOfBoundsException("Can't insert, invalid index");

		}

		DLLNode<T> newNode = new DLLNode<T>(element);

		if (index == 0) { // if index is the front

			if (count == 0) { // if there are no elements

				tail = newNode; // newNode is tail

			} else {

				head.setPrevious(newNode); // newNode is before head
				newNode.setNext(head); // head is after newNode

			}

			head = newNode; // newNode is head

		} else if (index == count) { // if index is count

			newNode.setPrevious(tail); // tail is before newNode
			tail.setNext(newNode); // newNode is after tail
			tail = newNode; // newNode is tail

		} else {

			DLLNode<T> current = head; // current is head

			for (int i = 0; i < index - 1; i++) { // makes current the value right before index

				current = current.getNext();

			}

			DLLNode<T> next = current.getNext(); // next is the node after current

			newNode.setPrevious(current); // current is before newNode
			current.setNext(newNode); // newNode is after current
			next.setPrevious(newNode); // newNode is before next
			newNode.setNext(next); // next is after newNode

		}

		count++;
		modCount++;

	}

	@Override
	public T removeFirst() {

		if (count == 0) {

			throw new NoSuchElementException("No element to remove");

		}

		T retVal = head.getElement(); // retVal is the element of head

		if (count == 1) {
			
			head = head.getNext(); // head is after head (could be null)
			
		} else {
			
			head = head.getNext();
			head.setPrevious(null);
			
		}

		count--;
		modCount++;

		return retVal;
	}

	@Override
	public T removeLast() {

		if (count == 0) { // if there are no elements

			throw new NoSuchElementException("No element to remove"); // nothing to remove

		}

		T retVal = tail.getElement();

		if (count == 1) { // if list only has 1 element

			head = tail = null;

		} else {

			tail = tail.getPrevious();
			tail.getNext().setPrevious(null);
			tail.setNext(null);

		}

		count--;
		modCount++;
		
		return retVal;
	}

	@Override
	public T remove(T element) {

		boolean found = false;
		
		DLLNode<T> current = head;
		DLLNode<T> previous = null;

		while ((current != null) & !found) {

			if (element.equals(current.getElement())) {

				found = true;

			} else {

				previous = current;
				current = current.getNext();

			}
		}

		if (!found) {

			throw new NoSuchElementException("Can't remove item, not in list");

		}

		T retVal;

		if (current == head) { // if current is the first element in the list

			retVal = current.getElement();

			if (count == 1) {
				head = head.getNext(); // head is after head
			} else {
				head = head.getNext();
				head.setPrevious(null);
			}

		} else {

			if (current == tail) {

				retVal = current.getElement();

				previous.setNext(null);
				tail = previous;

			} else {

				previous.setNext(current.getNext()); // the node after current is after previous
				current.getNext().setPrevious(previous); // previous is before the node after current
				previous.setPrevious(current.getPrevious()); // the node before current is before previous
				current.getPrevious().setNext(previous); // previous is after the node before current
				retVal = current.getElement();

			}

		}

		count--;
		modCount++;
		return retVal;

	}

	@Override
	public T remove(int index) {

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("Can't set, invalid index");
		}

		DLLNode<T> current = head;
		T retVal;

		if (index == 0) {

			if (count == 1) {

				retVal = head.getElement();
				head = tail = null;

			}

			else {

				retVal = head.getElement();
				head = head.getNext();
				head.setPrevious(null);

			}

		} else {

			// Now next is the element we want to remove, and current is the element before
			// it.

			if (index == count - 1) {

				retVal = tail.getElement();
				tail = tail.getPrevious();
				tail.getNext().setPrevious(null);
				tail.setNext(null);

			} else {

				for (int i = 0; i < index; i++) {
					current = current.getNext();
				}

				retVal = current.getElement();

			}

		}

		count--;
		modCount++;

		return retVal;

	}

	@Override
	public void set(int index, T element) {

		if (index < 0 || index >= count) {
			
			throw new IndexOutOfBoundsException("Can't set, invalid index");
			
		}

		DLLNode<T> current = head;

		for (int i = 0; i < index; i++) {
			
			current = current.getNext();
			
		}

		current.setElement(element);
		
		modCount++;

	}

	@Override
	public T get(int index) {
		
		if (index < 0 || index >= count) {
			
			throw new IndexOutOfBoundsException("Can't get, invalid index");
			
		}

		DLLNode<T> current = head;

		if (index == 0) {
			
			return head.getElement();
			
		} else if (index == count - 1) {
			
			return tail.getElement();
			
		} else {
			
			for (int i = 0; i < index; i++) {
				
				current = current.getNext();
				
			}
			
		}

		return current.getElement();

	}

	@Override
	public int indexOf(T element) {

		boolean found = false;
		
		DLLNode<T> current = head;
		
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

	@Override
	public T first() {
		
		if (count == 0) {
			
			throw new NoSuchElementException("Empty list");
			
		}
		
		return head.getElement();
		
	}

	@Override
	public T last() {
		
		if (count == 0) {
			
			throw new NoSuchElementException("Empty list");
			
		}
		
		return tail.getElement();
		
	}

	@Override
	public boolean contains(T target) {

		boolean found = false;
		
		DLLNode<T> current = head;

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

	@Override
	public boolean isEmpty() {

		if (count == 0) {
			
			return true;
			
		} else {
			
			return false;
			
		}
	}

	@Override
	public int size() {
		
		return count;
		
	}

	@Override
	public Iterator<T> iterator() {
		
		return new DLLIterator();
		
	}

	@Override
	public ListIterator<T> listIterator() {
		
		return new DLLIterator();
		
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		
		return new DLLIterator(startingIndex);
		
	}

	/**
	 * Iterator for the IUDoubleLinkedList - implements Iterator interface
	 * 
	 * @author gavinwale
	 * 
	 * @param <T>
	 */

	private class DLLIterator implements Iterator<T>, ListIterator<T> {

		private DLLNode<T> next;
		private DLLNode<T> current;
		private DLLNode<T> previous;
		
		private int itrModCount;
		private int nextIndex;
		private boolean canRemove;

		/**
		 * Default constructor
		 */
		public DLLIterator() {
			
			next = head;
			current = null;
			previous = null;
			itrModCount = modCount;
			canRemove = false;
			nextIndex = 0;
			
		}

		/**
		 * Constructor with a starting index
		 * 
		 * @param startingIndex
		 */
		public DLLIterator(int startingIndex) {
			
			nextIndex = 0;
			
			for (int i = 0; i <= startingIndex; i++) {
				
				previous = current;
				current = next;
				next = next.getNext(); // might have to reassign head, might have to change the logic up
				nextIndex++;
				
			}
			
			itrModCount = modCount;
			canRemove = false;

		}

		@Override
		public boolean hasNext() {
			
			return (next != null);
			
		}

		@Override
		public T next() {
			
			if (itrModCount != modCount) {
				
				throw new ConcurrentModificationException("Can't call next, changes made");
				
			}

			if (!hasNext()) {
				
				throw new NoSuchElementException("Can't call next, end of iteration");
				
			}

			
			current = next;
			previous = current;
			next = next.getNext();
			canRemove = true;
			nextIndex++;

			return current.getElement();
			
		}

		public void remove() {

			if (!canRemove) {
				
				throw new IllegalStateException("Can't remove, haven't called next");
				
			}

			if (itrModCount != modCount) {
				
				throw new ConcurrentModificationException("Can't call remove, changes made");
				
			}
			
			if (current == head) {
				
				if (current == previous) { // next was last called
					
					head = next;
					previous = null;
					current = next; // ????????
					
				} else {
					
					head = next;
					current = null;
					previous = null;
					
				}
				
				
			} else if (current == tail) {
				
				if (current == previous) { // next was last called
					
					previous = tail.getPrevious();
					current = null;
					next = null;
					
				} else {
					
					current = previous;
					next = null;
					tail = previous;
					
				}
				
			} else {
				
				if (current == previous) { // next was last called
					
					current = current.getPrevious();
					previous = current;
					next.setPrevious(previous);
					previous.setNext(next);
					current.setNext(next);
					
				} else {
					
					current = current.getNext();
					next = current;
					next.setPrevious(previous);
					previous.setNext(next);
					current.setPrevious(previous);
					
				}
				
			}

			canRemove = false;

			count--;
			modCount++;
			itrModCount++;
		}

		@Override
		public boolean hasPrevious() {
			
			return (previous != null);
			
		}

		@Override
		public T previous() {

			if (itrModCount != modCount) {
				
				throw new ConcurrentModificationException("Can't call previouis, changes made");
				
			}

			if (!hasPrevious()) {
				
				throw new NoSuchElementException("Can't call previous, end of iteration");
				
			}

			next = previous;
			current = previous;
			previous = previous.getPrevious();
			canRemove = true;
			nextIndex--;
			
			return current.getElement();

		}

		@Override
		public int nextIndex() {

			return nextIndex;
			
		}

		@Override
		public int previousIndex() {

			return nextIndex - 1;

		}

		@Override
		public void set(T e) {

			if (!canRemove) {
				
				throw new IllegalStateException("Next or previous need to be called");
				
			}

			IUDoubleLinkedList.this.set(indexOf(current.getElement()), e);
			
			itrModCount++;

		}

		@Override
		public void add(T e) {

			if (previous == null) {
				
				IUDoubleLinkedList.this.addToFront(e);
				
				previous = head;

			} else {
				
				int i;
				
				if (next == null) {
					
					i = count;
					
				} else {
					
					i = indexOf(next.getElement());
					
				}

				IUDoubleLinkedList.this.add(i, e);
				
				previous = previous.getNext();

			}

			canRemove = false;
			nextIndex++;
			itrModCount++;

		}

	}

}
