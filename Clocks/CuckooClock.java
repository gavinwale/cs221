/**
 * An extension of the clock class that defines
 * the display method.
 * 
 * @author gavinwale
 */
public class CuckooClock extends Clock {

	/**
	 * Constructor that creates a new CuckooClock with the Clock constructor
	 * 
	 * @param ClockType - clockType
	 * @param double - d
	 */
	public CuckooClock(Clock.ClockType clockType, double d) {
		super(clockType, d);
	}

	// Class variables
	String description = "cuckoo clock";
	ClockType clock_type = getClockType();
	
	

	@Override
	public void display() {
		System.out.println(clock_type + ", " + description + ", time [" + 
		time.formattedTime() + "], total drift = " + df.format(time.getTotalDrift()));
	}

}
