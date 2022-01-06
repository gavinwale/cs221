/**
 * Runs a set of black box tests for lists that 
 * implement IndexedUnsortedList ADT.
 * This is a set of black box tests that should 
 * work for any implementation.
 * 
 * @author CS221
 */
public class RunIndexedUnsortedTests
{
	// TODO: make sure using correct list when running tests 
	
	// Which list to use in tests:
	// { goodList, arrayList, singleLinkedList, doubleLinkedList, wrappedDLL }
	
	// Run tests on the GoodList class first, then on your IUArrayList class
	private final static ListType LIST_TYPE = ListType.doubleLinkedList;
	//private final static ListType LIST_TYPE = ListType.arrayList; 

	// total test count
	private static int totalTests = 0; 

	// instance variables for tracking test results
	private static int passes = 0;
	private static int failures = 0;
	private static int total = 0;

	/** 
	 * Run tests
	 * @param args - not used in this test suite
	 */
	public static void main(String[] args) 
	{
		runTests();
		
		// report final verdict
		printFinalSummary();
	}
	
	/** 
	 * Run tests to confirm required functionality of implementation
	*/
	private static void runTests()
	{		
		// Tests for given change scenarios
		Test_1_constructor_emptyList constructor_emptyList = new Test_1_constructor_emptyList();
		printDescription(constructor_emptyList.getDescription());
		constructor_emptyList.run(LIST_TYPE); 
		totalTests += constructor_emptyList.getNumTests(); 
		printLine();
		
		Test_2_emptyList_addToFrontA_A emptyList_addToFrontA_A = new Test_2_emptyList_addToFrontA_A();
		printDescription(emptyList_addToFrontA_A.getDescription());
		emptyList_addToFrontA_A.run(LIST_TYPE);
		totalTests += emptyList_addToFrontA_A.getNumTests();
		printLine();
		
		Test_3_emptyList_addToRearA_A emptyList_addToRearA_A = new Test_3_emptyList_addToRearA_A();
		printDescription(emptyList_addToRearA_A.getDescription());
		emptyList_addToRearA_A.run(LIST_TYPE);
		totalTests += emptyList_addToRearA_A.getNumTests();
		printLine();
		
		Test_4_emptyList_addA_A emptyList_addA_A = new Test_4_emptyList_addA_A();
		printDescription(emptyList_addA_A.getDescription());
		emptyList_addA_A.run(LIST_TYPE);
		totalTests += emptyList_addA_A.getNumTests();
		printLine();
		
		Test_5_emptyList_add0A_A emptyList_add0A_A = new Test_5_emptyList_add0A_A();
		printDescription(emptyList_add0A_A.getDescription());
		emptyList_add0A_A.run(LIST_TYPE);
		totalTests += emptyList_add0A_A.getNumTests();
		printLine();
			
		Test_6_A_addToFrontB_BA A_addToFrontB_BA = new Test_6_A_addToFrontB_BA();
		printDescription(A_addToFrontB_BA.getDescription());
		A_addToFrontB_BA.run(LIST_TYPE); 
		totalTests += A_addToFrontB_BA.getNumTests(); 
		printLine(); 
		
		Test_7_A_addToRearB_AB A_addToRearB_AB = new Test_7_A_addToRearB_AB();
		printDescription(A_addToRearB_AB.getDescription());
		A_addToRearB_AB.run(LIST_TYPE); 
		totalTests += A_addToRearB_AB.getNumTests(); 
		printLine(); 
		
		Test_8_A_addAfterBA_AB A_addAfterBA_AB = new Test_8_A_addAfterBA_AB();
		printDescription(A_addAfterBA_AB.getDescription());
		A_addAfterBA_AB.run(LIST_TYPE); 
		totalTests += A_addAfterBA_AB.getNumTests(); 
		printLine(); 
		
		Test_9_A_addB_AB A_addB_AB = new Test_9_A_addB_AB();
		printDescription(A_addB_AB.getDescription());
		A_addB_AB.run(LIST_TYPE); 
		totalTests += A_addB_AB.getNumTests(); 
		printLine(); 
		
		Test_10_A_add0B_BA A_add0B_BA = new Test_10_A_add0B_BA();
		printDescription(A_add0B_BA.getDescription());
		A_add0B_BA.run(LIST_TYPE); 
		totalTests += A_add0B_BA.getNumTests(); 
		printLine(); 
		
		Test_11_A_add1B_AB A_add1B_AB = new Test_11_A_add1B_AB();
		printDescription(A_add1B_AB.getDescription());
		A_add1B_AB.run(LIST_TYPE); 
		totalTests += A_add1B_AB.getNumTests(); 
		printLine(); 
		
		Test_12_A_removeFirst_emptyList A_removeFirst_emptyList = new Test_12_A_removeFirst_emptyList();
		printDescription(A_removeFirst_emptyList.getDescription());
		A_removeFirst_emptyList.run(LIST_TYPE); 
		totalTests += A_removeFirst_emptyList.getNumTests(); 
		printLine(); 
		
		Test_13_A_removeLast_emptyList A_removeLast_emptyList = new Test_13_A_removeLast_emptyList();
		printDescription(A_removeLast_emptyList.getDescription());
		A_removeLast_emptyList.run(LIST_TYPE); 
		totalTests += A_removeLast_emptyList.getNumTests(); 
		printLine(); 
		
		Test_14_A_removeA_emptyList A_removeA_emptyList = new Test_14_A_removeA_emptyList();
		printDescription(A_removeA_emptyList.getDescription());
		A_removeA_emptyList.run(LIST_TYPE); 
		totalTests += A_removeA_emptyList.getNumTests(); 
		printLine(); 
		
		Test_15_A_remove0_emptyList A_remove0_emptyList = new Test_15_A_remove0_emptyList();
		printDescription(A_remove0_emptyList.getDescription());
		A_remove0_emptyList.run(LIST_TYPE); 
		totalTests += A_remove0_emptyList.getNumTests(); 
		printLine();
		
		Test_16_A_set0B_B A_set0B_B = new Test_16_A_set0B_B();
		printDescription(A_set0B_B.getDescription());
		A_set0B_B.run(LIST_TYPE);
		totalTests += A_set0B_B.getNumTests();
		printLine();
		
		Test_17_AB_addToFrontC_CAB AB_addToFrontC_CAB = new Test_17_AB_addToFrontC_CAB();
		printDescription(AB_addToFrontC_CAB.getDescription());
		AB_addToFrontC_CAB.run(LIST_TYPE);
		totalTests += AB_addToFrontC_CAB.getNumTests();
		printLine();
		
		Test_25_AB_removeFirst_B AB_removeFirst_B = new Test_25_AB_removeFirst_B();
		printDescription(AB_removeFirst_B.getDescription());
		AB_removeFirst_B.run(LIST_TYPE);
		totalTests += AB_removeFirst_B.getNumTests();
		printLine();
		
		Test_26_AB_removeLast_A AB_removeLast_A = new Test_26_AB_removeLast_A();
		printDescription(AB_removeLast_A.getDescription());
		AB_removeLast_A.run(LIST_TYPE);
		totalTests += AB_removeLast_A.getNumTests();
		printLine();
		
		Test_27_AB_removeA_B AB_removeA_B = new Test_27_AB_removeA_B();
		printDescription(AB_removeA_B.getDescription());
		AB_removeA_B.run(LIST_TYPE);
		totalTests += AB_removeA_B.getNumTests();
		printLine();
		
		Test_28_AB_removeB_A AB_removeB_A = new Test_28_AB_removeB_A();
		printDescription(AB_removeB_A.getDescription());
		AB_removeB_A.run(LIST_TYPE);
		totalTests += AB_removeB_A.getNumTests();
		printLine();
		
		Test_29_AB_remove0_B AB_remove0_B = new Test_29_AB_remove0_B();
		printDescription(AB_remove0_B.getDescription());
		AB_remove0_B.run(LIST_TYPE);
		totalTests += AB_remove0_B.getNumTests();
		printLine();
		
		Test_30_AB_remove1_A AB_remove1_A = new Test_30_AB_remove1_A();
		printDescription(AB_remove1_A.getDescription());
		AB_remove1_A.run(LIST_TYPE);
		totalTests += AB_remove1_A.getNumTests();
		printLine();
		
		Test_39_ABC_remove1_AC ABC_remove1_AC = new Test_39_ABC_remove1_AC();
		printDescription(ABC_remove1_AC.getDescription());
		ABC_remove1_AC.run(LIST_TYPE); 
		totalTests += ABC_remove1_AC.getNumTests(); 
		printLine(); 
		
		Test_41_ABC_set0D_DBC ABC_set0D_DBC = new Test_41_ABC_set0D_DBC();
		printDescription(ABC_set0D_DBC.getDescription());
		ABC_set0D_DBC.run(LIST_TYPE);
		totalTests += ABC_set0D_DBC.getNumTests();
		printLine();

	}
	
	/**
	 * Print test results in a consistent format
	 * 
	 * @param testDesc - description of the test
	 * @param result - indicates whether the test passed or failed
	 */	
	public static void printResults(String testDesc, boolean result)
	{
		total++;
		if (result) 
		{
			passes++;
		} 
		else 
		{
			failures++;
		}
		System.out.printf("%-46s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
	}

	/** 
	 * Print a final summary 
	 */
	private static void printFinalSummary()
	{
		System.out.printf("\nTotal Tests Run: %d of %d,  Passed: %d,  "
				           + "Failed: %d\n", total, totalTests, passes, failures);
	}
	
	/**
	 * Prints description of tests 
	 * @param description - description of tests 
	 */
	private static void printDescription(String description)
	{
		System.out.println(description);		
	}

	/** 
	 * Prints line between tests
	 */
	private static void printLine()
	{
		System.out.println("=======================================================");
		
	}

}
