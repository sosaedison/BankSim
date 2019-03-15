/**
 * This Departure class of type event
 * the opposite of our Arrival class in that
 * it's passing into a method will trigger the arrival
 * to also be removed from all lines and queues.
 *
 * @author Sosa Edison
 * @author Michael Gustin
 *
 */
public class Departure implements Event {


    /**
     * Int for the eventTime
     */
    private int eventTime;
    /**
     * Boolean for if the event was a walkIn
     */
    private boolean walkIn;
    /**
     * The teller that created it
     */
    private Teller teller;


    /**
     * The constructor that just sets up fields
     * @param eventTime the event time
     * @param walkIn was it a walkIn?
     * @param teller the teller that created it
     */
    public Departure(int eventTime, boolean walkIn, Teller teller) {
        this.eventTime = eventTime;
        this.walkIn = walkIn;
        this.teller = teller;

    }

    /**
     * Getter for the event time
     * @return the event time
     */
    @Override
    public int getEventTime() {
        return this.eventTime;
    }

    /**
     * Getter for if it was a walkIn
     * @return true if it was a walkIn, false if not
     */
    @Override
    public boolean getWalkIn() {
        return this.walkIn;
    }

    /**
     * Getter for if this is an arrival.
     * Hardcoded because it never will be
     * @return false
     */
    @Override
    public boolean isArrival() {
        return false;
    }

    /**
     * Getter for the teller that created this instance
     * @return the teller that created this departure
     */
    public Teller getTeller() {
        return teller;
    }

    /**
     * A String representation of the departure
     * @return the eventTime and the teller and if it was a walkIn
     */
    public String toString() {
        return "Departure: " + this.walkIn + " EventTime: " + this.eventTime + " Teller: " + this.teller.getID();
    }

    /**
     * The transTime of a departure is always 0
     * @return 0
     */
	@Override
	public int getTransTime() {
		// TODO Auto-generated method stub
		return 0;
	}
}
