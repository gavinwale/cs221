import java.text.DecimalFormat;

/**
 * This abstract clock class represents all the core features of
 * a clock and will be extended by all Clock classes.
 * 
 * @author gavinwale
 */
public abstract class Clock implements TimePiece {

	/**
	 * The types of clocks that will be analyzed
	 */
	public enum ClockType {NATURAL, MECHANICAL, DIGITAL, QUANTUM}
	
	protected DecimalFormat df = new DecimalFormat("0.00");
	
	// Class variables
	private ClockType clockType;
	protected Time time;
	
	/**
	 * Instantiates a new clock given the type of clock and the drift per second as a double
	 * 
	 * @param clockType
	 * @param d
	 */
	public Clock(ClockType clockType, double d) {
		this.clockType = clockType;
		time = new Time(d);
	}
	
	/**
	 * Sets the type of clock
	 * 
	 * @param clockType
	 */
	public void setClockType(ClockType clockType) {
		this.clockType = clockType;
	}
	
	/**
	 * Returns the type of clock
	 * 
	 * @return clockType
	 */
	public ClockType getClockType() {
		return this.clockType;
	}
	

	@Override
	public void reset() {
		time.resetToStartTime();
	}

	@Override
	public void tick() {
		time.incrementTime();
	}

	@Override
	abstract public void display(); // To be defined in child classes
	
	

}
