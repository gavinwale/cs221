/**
 * An extension of the clock class that defines
 * the display method.
 * 
 * @author gavinwale
 */
public class WristWatch extends Clock {

	/**
	 * Constructor that creates a new WristWatch with the Clock constructor
	 * 
	 * @param ClockType - clockType
	 * @param double - d
	 */
	public WristWatch(Clock.ClockType clockType, double d) {
		super(clockType, d);
	}

	// Class variables
	String description = "wristwatch";
	ClockType clock_type = getClockType();
	
	

	@Override
	public void display() {
		System.out.println(clock_type + ", " + description + ", time [" + 
		time.formattedTime() + "], total drift = " + df.format(time.getTotalDrift()));
	
	}

}
