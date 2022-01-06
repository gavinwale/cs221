import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple data structure that holds items of type T 
 * using an array implementation. 
 * @author Matt T 
 */
public class Bag<T> implements Iterable<T>
{
	private final int INITIAL_CAPACITY = 50;   // default capacity of bag
	private int capacity;  // number of T objects the bag can hold 
	private T[] bag;  // array to hold objects of type T in the Bag
	private int count; // number of integers stored in the array
	private int modCount; // number of changes made to bag 

	/**
	 * Default constructor creates an empty Bag
	 */
	@SuppressWarnings("unchecked")
	public Bag() 
	{
		// create array of T elements
		setCapacity(INITIAL_CAPACITY);
		bag = (T[])new Object[capacity];
		count = 0;
		modCount = 0; 
	}

	/**
	 * Mutator for capacity of the bag. 
	 * @param (int) newCapacity - integer representing new size of bag
	 */
	public void setCapacity(int newCapacity)
	{
		capacity = newCapacity;
	}

	/**
	 * Accessor method for number of elements in the bag. 
	 * @return (int) current number of elements in the bag 
	 */
	public int size()
	{
		return count;
	}
	
	/**
	 * Adds a new element to the bag. 
	 *@param element (T) object to be added to the bag
	 */
	public void add(T element) 
	{
		// if current number of elements is at capacity
		if (count >= capacity) 
		{
			resize();		// expand size of bag 
		} 
		// add item at end of the array
		bag[count] = element;
		count++;
		modCount++; 
	}

	/**
	 * Doubles size of the bag.  
	 */
	private void resize()
	{
		capacity *= 2; 
		bag = Arrays.copyOf(bag, capacity);
	}
	
	/**
	 * Print elements of the bag.
	 */
	public void print() 
	{
		// print out number of elements
		System.out.println("The bag has " + count + " elements:");
		// if not empty
		if (!isEmpty()) 
		{
			// uses toString method of objects to print them
			// separated by commas
			System.out.print(bag[0].toString());
			for (int i = 1; i < count; i++) 
			{
				System.out.print(", " + bag[i]);
			}
			System.out.println();
		}
	}

	/**
	 * Removes particular element from the bag,
	 * throws exception if not found. 
	 * @param element (T) item looking for 
	 * @return (T) reference to element if found 
	 * @throws NoSuchElementException
	 */
	public T remove(T element)
	{
		T target = null;
		Boolean found = false;  // still looking for  element 
		int i = 0; 	// loop variable
		
		// iterate through array looking for element
		while(i < count && !found)
		{
			if (bag[i] == element) 
			{
				// get return value
				target = bag[i];
				//move last element into space left by this element
				bag[i] = bag[count-1];
				// set old last position to null
				bag[count - 1] = null;
				// update count
				count--;
				// update modCount
				modCount++; 
				// done, so set to true 
				found = true;
			}
			i++;
		}
		// if didn't find element 
		if(!found)
		{
			throw new NoSuchElementException("Bag - remove(element)");
		}
		return target;
	}

	/**
	 *  Whether bag contains a particular element or not
	 * @param target (T) element looking for
	 * @return (boolean) whether element is found 
	 */
	public boolean contains(T target)
	{
		Boolean found = false;
		int i = 0; 	// loop variable
		
		// iterate through array looking for element
		while(i < count && !found)
		{
			// if found, set found to true 
			if (bag[i] == target) 
			{
				found = true;
			}
			i++;
		}
		return found;
	}

	/**
	 * Whether bag is empty or not. 
	 * @return (boolean) representing whether empty or not 
	 */
	public boolean isEmpty()
	{
		// empty if count equals 0, not otherwise 
		return (count == 0);
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return new BagIterator();
	}
	
	/**
	 * Inner class for Iterator for Bag class.
	 * Doesn't implement optional remove method. 
	 * 
	 * @author Matt T
	 *
	 */
	private class BagIterator implements Iterator<T>
	{
		private int next; // position of next element to be returned 
		private int itrModCount; // number of modifications done when iterator instantiated
		
		/**
		 * Default constructor, starts at position 0 
		 */
		public BagIterator()
		{
			next = 0; 
			itrModCount = modCount; 
		}
		
		@Override
		public boolean hasNext()
		{
			// check for modifications to bag 
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("BagIterator - hasNext"); 
			}
			// true if next not at end of array 
			return (next < count);
		}

		@Override
		public T next()
		{
			// check precondition for next 
			if(!hasNext())
			{
				throw new NoSuchElementException("BagIterator - next"); 
			}
			// get reference to next object 
			T nextObject = bag[next]; 
			// update next 
			next++; 
			
			return nextObject;
		}
		
	}

}
