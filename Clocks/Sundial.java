/**
 * An extension of the clock class that defines
 * the display method.
 * 
 * @author gavinwale
 */
public class Sundial extends Clock {

	/**
	 * Constructor that creates a new Sundial with the Clock constructor
	 * 
	 * @param ClockType - clockType
	 * @param double - d
	 */
	public Sundial(Clock.ClockType clockType, double d) {
		super(clockType, d);
		
	}
	
	// Class variables
	String description = "sun dial";
	ClockType clock_type = getClockType();
	
	

	@Override
	public void display() {
		System.out.println(clock_type + ", " + description + ", time [" + 
		time.formattedTime() + "], total drift = " + df.format(time.getTotalDrift()));
	}

}
