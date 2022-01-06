import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Merge Sort algorithm.
 *
 * @author CS221, gavinwale
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. 
	 * Must be changed if using your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	public static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); 
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{ // this one uses compareTo method, Integer objects have implemented the Comparable interface. 
	  // 
		mergesort(list);
	}
 	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Merge Sort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		
		if (list.size() <= 1) {
			return;
		}
		
		// sub-lists about half the size of list 
		IndexedUnsortedList<T> lowerList = newList(); 
		IndexedUnsortedList<T> upperList = newList(); 
		
		int start = 0;
		int end = list.size();
		
		if (list.size() > 1) {
			
			int mid = (start + end) / 2;
			
			for (int i = 0; i < end; i++) {
				
				if (i < mid) {
					
					lowerList.add(list.removeFirst());
					
				} else {
					
					upperList.add(list.removeLast());
					
				}
				
			}
			
		}
		
		mergesort(lowerList);
		mergesort(upperList);
		
		merge(list, lowerList, upperList); 
	}
	
	/**
	 * Implements merge method for Merge Sort algorithm.  
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 *
	 * @param list - reference to original list that's being sorted, now empty 
	 * @param lowerList - lower half of original list 
	 * @param upperList - upper half of original list 
	 */
	private static <T extends Comparable<T>> void merge(IndexedUnsortedList<T> list, IndexedUnsortedList<T> lowerList, IndexedUnsortedList<T> upperList)
	{
		int end = lowerList.size() + upperList.size();
		
		for (int i = 0; i < end; i++) {
			
			if (lowerList.isEmpty()) {
				
				T toAdd1 = upperList.removeFirst();
				list.addToRear(toAdd1);
				
			} else if (upperList.isEmpty()) {
				
				T toAdd2 = lowerList.removeFirst();
				list.addToRear(toAdd2);
				
			} else if (upperList.first().compareTo(lowerList.first()) > 0) {
				
				T toAdd3 = lowerList.removeFirst();
				list.addToRear(toAdd3);
				
			} else {
				
				T toAdd4 = upperList.removeFirst();
				list.addToRear(toAdd4);
				
			}
			
		}
		
	}
		
	/**
	 * Merge Sort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{		
		IndexedUnsortedList<T> lowerList = newList(); 
		IndexedUnsortedList<T> upperList = newList(); 
		
		if (list.size() <= 1) {
			return;
		}
		
		int start = 0;
		int end = list.size();
		
		if (list.size() > 1) {
			
			int mid = (start + end) / 2;
			
			for (int i = 0; i < end; i++) {
				
				if (i < mid) {
					
					lowerList.add(list.removeFirst());
					
				} else {
					
					upperList.add(list.removeLast());
					
				}
				
			}
			
		}
		
		mergesort(lowerList, c);
		mergesort(upperList, c);
		
		merge(list, lowerList, upperList, c); 

	}
	
	/**
	 * Implements merge method for Merge Sort algorithm 
	 * @param list - reference to original list that's being sorted, now empty 
	 * @param lowerList - lower half of original list 
	 * @param upperList - upper half of original list 
	 * @param c - Comparator for comparing elements in list
	 */
	private static <T> void merge(IndexedUnsortedList<T> list, IndexedUnsortedList<T> lowerList, IndexedUnsortedList<T> upperList, Comparator<T> c)
	{

		int end = lowerList.size() + upperList.size();
		
		for (int i = 0; i < end; i++) {
			
			if (lowerList.isEmpty()) {
				
				T toAdd1 = upperList.removeFirst();
				list.addToRear(toAdd1);
				
			} else if (upperList.isEmpty()) {
				
				T toAdd2 = lowerList.removeFirst();
				list.addToRear(toAdd2);
				
			} else if (c.compare(upperList.first(), lowerList.first()) > 0) {
				
				T toAdd3 = lowerList.removeFirst();
				list.addToRear(toAdd3);
				
			} else {
				
				T toAdd4 = upperList.removeFirst();
				list.addToRear(toAdd4);
				
			}
			
		}
		
	}
	
}
