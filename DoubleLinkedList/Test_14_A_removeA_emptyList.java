/**
 * Testing for IndexedUnsortedList ADT implementation: 
 * Tests for Change Scenario 14: [A] -> remove(A) -> empty list
 * 
 * @author gavinwale
 */
public class Test_14_A_removeA_emptyList {

	// List running tests on
		IndexedUnsortedList<Character> list;
		
		//****** Constants used in tests *****************
			// Name of class
			private static String CLASS_NAME = "Test_14_A_removeA_emptyList"; 
			// Description of change scenario testing 
			private static final String testDescription = 
					"Change Scenario 14: [A] -> remove(A) -> empty list"; 
			// Number of tests run 
			private static final int NUM_TESTS = 20; 
			// Type of list using in tests
			private ListType listType; 

			// No first element in list
			private static final Character FIRST = null;
			// No last element in list 
			private static final Character LAST = null;	
			// Invalid index outside of list 
			private static final int INVALID_INDEX = -1;
			// Only valid indexes where can add a new element 
			private static final int VALID_ADD_INDEX = 0; 
			// First negative index outside valid add range
			private static final int NEG_INVALID_ADD_INDEX = -1;
			// First positive index outside valid add range
			private static final int POS_INVALID_ADD_INDEX = 1;
			// Size of the list 
			private static final int SIZE = 0; 
				
		//****** Don't change these constants *****************
			
			// An element not in list 
			private static final Character ELEMENT = IndexedUnsortedListTests.E; 
			// Another element not in list - used for negative testing 
			private static final Character INVALID_ELEMENT = IndexedUnsortedListTests.F;

		/**
		 * Sets up list for testing. 
		 */
		public void initialize()
		{
			// initialize state of list
			list = IndexedUnsortedListTests.newList(listType);
			list.add(IndexedUnsortedListTests.A);
			// apply change
			list.remove(IndexedUnsortedListTests.A);
			
		}
			
		/** 
		 * Run tests for this change scenario 
		 */
		public void run(ListType listType)
		{
			// set type of list running tests on 
			this.listType = listType; 
			
			// result of running tests 
			boolean result; 
			
			try 
			{
				// Test: removeFirst()
				initialize(); 
				result = IndexedUnsortedListTests.removeFirst(list, FIRST, Result.NoSuchElement); 
				RunIndexedUnsortedTests.printResults("removeFirst()", result);		
		
				// Test: removeLast()
				initialize(); 
				result = IndexedUnsortedListTests.removeLast(list, LAST, Result.NoSuchElement); 
				RunIndexedUnsortedTests.printResults("removeLast()", result);
				
				// Test: remove(INVALID_ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.remove(list, INVALID_ELEMENT, Result.NoSuchElement); 
				RunIndexedUnsortedTests.printResults("remove(INVALID_ELEMENT)", result);
				
				// Test: first()
				initialize(); 
				result = IndexedUnsortedListTests.first(list, FIRST, Result.NoSuchElement); 
				RunIndexedUnsortedTests.printResults("first()", result);
				
				// Test: last()
				initialize(); 
				result = IndexedUnsortedListTests.last(list, LAST, Result.NoSuchElement); 
				RunIndexedUnsortedTests.printResults("last()", result);
				
				// Test: contains(INVALID_ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.contains(list, INVALID_ELEMENT, Result.False); 
				RunIndexedUnsortedTests.printResults("contains(INVALID_ELEMENT)", result);
				
				// Test: isEmpty()
				initialize(); 
				result = IndexedUnsortedListTests.isEmpty(list, Result.True); 
				RunIndexedUnsortedTests.printResults("isEmpty()", result);
				
				// Test: size()
				initialize(); 
				result = IndexedUnsortedListTests.size(list, SIZE); 
				RunIndexedUnsortedTests.printResults("size()", result);
				
				// Test: iterator()
				initialize(); 
				result = IndexedUnsortedListTests.iterator(list); 
				RunIndexedUnsortedTests.printResults("iterator()", result);
						
				// Test: addToFront(ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.addToFront(list, ELEMENT, Result.NoException); 
				RunIndexedUnsortedTests.printResults("addToFront(ELEMENT)", result);
				
				// Test: addToRear(ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.addToRear(list, ELEMENT, Result.NoException); 
				RunIndexedUnsortedTests.printResults("addToRear(ELEMENT)", result);
				
				// Test: addAfter(ELEMENT, INVALID_ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.addAfter(list, ELEMENT, INVALID_ELEMENT, Result.NoSuchElement); 
				RunIndexedUnsortedTests.printResults("addAfter(ELEMENT, INVALID_ELEMENT)", result);
				
				// Test: add(VALID_INDEX, ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.add(list, VALID_ADD_INDEX, ELEMENT, Result.NoException); 
				RunIndexedUnsortedTests.printResults("add(VALID_INDEX, ELEMENT)", result);
				
				// add(NEG_INVALID_ADD_INDEX, ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.add(list, NEG_INVALID_ADD_INDEX, ELEMENT, Result.IndexOutOfBounds); 
				RunIndexedUnsortedTests.printResults("add(NEG_INVALID_ADD_INDEX, ELEMENT)", result);
				
				// add(POS_INVALID_ADD_INDEX, ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.add(list, POS_INVALID_ADD_INDEX, ELEMENT, Result.IndexOutOfBounds); 
				RunIndexedUnsortedTests.printResults("add(POS_INVALID_ADD_INDEX, ELEMENT)", result);
				
				// Test: remove(INVALID_INDEX)
				initialize(); 
				result = IndexedUnsortedListTests.remove(list, INVALID_INDEX, ELEMENT, Result.IndexOutOfBounds); 
				RunIndexedUnsortedTests.printResults("remove(INVALID_INDEX)", result);
				
				// Test: set(INVALID_INDEX, ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.set(list, INVALID_INDEX, ELEMENT, Result.IndexOutOfBounds); 
				RunIndexedUnsortedTests.printResults("set(INVALID_INDEX, ELEMENT)", result);
				
				// Test: add(ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.add(list, ELEMENT, Result.NoException); 
				RunIndexedUnsortedTests.printResults("add(ELEMENT)", result);
				
				// Test: get(INVALID_INDEX)
				initialize(); 
				result = IndexedUnsortedListTests.get(list, INVALID_INDEX, ELEMENT, Result.IndexOutOfBounds); 
				RunIndexedUnsortedTests.printResults("get(INVALID_INDEX)", result);
				
				// Test: indexOf(INVALID_ELEMENT)
				initialize(); 
				result = IndexedUnsortedListTests.indexOf(list, INVALID_ELEMENT, INVALID_INDEX, Result.MatchingValue); 
				RunIndexedUnsortedTests.printResults("indexOf(INVALID_ELEMENT)", result);
					
			} 
			catch (Exception e) 
			{
				System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", CLASS_NAME);
				e.printStackTrace();
			}
		}
		
		/**
		 * Returns test description 
		 * @return testDescription - description of test
		 */
		public String getDescription()
		{
			return testDescription;
		}
		
		/**
		 * Returns number of tests in this test class 
		 * @return - int representing number of tests 
		 */
		public int getNumTests()
		{
			return NUM_TESTS; 
		}
	
	
	
}
