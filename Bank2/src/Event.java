/**
 * This Event interface simplifies
 * how we interact with all things "event".
 * You'll find simple Getters for relevant
 * information of arrivals and departures.
 *
 * @author Sosa Edison
 * @author Michael Gustin
 *
 */
public interface Event {

    /**
     * Gets the event time
     * @return the event time
     */
    int getEventTime();

    /**
     * Returns True if a walk-in, false if a drive-in
     * @return Returns True if a walk-in, false if a drive-in
     */
    boolean getWalkIn();

    /**
     * The type of Event might be a departure.
     * @return true if an arrival, false if departure
     */
    boolean	isArrival();

    /**
     * Getter for the teller that created a departure
     * @return The teller that created the departure
     */
    Teller getTeller();

    /**
     * Getter for the transaction time of the event
     * @return the transaction time
     */
    int getTransTime();

}
