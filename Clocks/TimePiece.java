/**
 * This interface contains methods to be used
 * through inheritance.
 * 
 * @author gavinwale
 */
public interface TimePiece {

	/**
	 * Resets TimePiece to starting time
	 * (00:00:00 - midnight by default)
	 */
	public void reset();
	
	/**
	 * Simulates one second of time passing
	 */
	public void tick();
	
	/**
	 * Displays the current time
	 */
	public void display();
	
}
