/**
 * Simulates different clocks from a bag object and outputs information about 
 * each clock type over different preset time frames.
 * 
 * @author gavinwale
 */
public class ClockSimulation {

	static Bag<Clock> clocks;

	/**
	 * Uses a for each loop to iterate over a specific time
	 * frame in seconds
	 * 
	 * @param seconds
	 */
	private static void iterate(long seconds) {

		for (Clock clock : clocks) { // For each clock in clocks

			for (long i = 0; i < seconds; i++) {
				clock.tick(); // Increments time for each iteration of the for loop
				
			}
			clock.display(); // Display method called after all iterations of a clock are done
			clock.reset(); // Resets time to startTime
			
		}

	}

	/**
	 * Creates a bag, adds clocks to the bag, and prints the iteration of each time frame.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Creating a new bag
		clocks = new Bag<Clock>();

		// Adding clocks to the bag
		clocks.add(new Sundial(Clock.ClockType.NATURAL, 0.00));
		clocks.add(new CuckooClock(Clock.ClockType.MECHANICAL, 0.000694444));
		clocks.add(new GrandfatherClock(Clock.ClockType.MECHANICAL, 0.000347222));
		clocks.add(new AtomicClock(Clock.ClockType.QUANTUM, 0.0));
		clocks.add(new WristWatch(Clock.ClockType.DIGITAL, 0.000034722));
		
		// Long variables for the amount of time pertaining to each time frame in seconds
		long startTime = 0;
		long dayTime = 86400;
		long weekTime = 604800;
		long monthTime = 2592000;
		long yearTime = 31536000;

		// Print statements to display to the console
		System.out.println("Times before start:");
		iterate(startTime);
		System.out.println();
		
		System.out.println("After one day:");
		iterate(dayTime);
		System.out.println();

		System.out.println("After one week:");
		iterate(weekTime);
		System.out.println();

		System.out.println("After one month:");
		iterate(monthTime);
		System.out.println();

		System.out.println("After one year:");
		iterate(yearTime);
		

	}

}
