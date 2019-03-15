/**
 * The arrival class is simply a skeleton for
 * and event of time arrival. It just holds
 * information about the event, or in the
 * eyes of the bank, it holds information
 * about an incoming customer.
 *
 * @author Sosa Edison
 * @author Michael Gustin
 */
public class Arrival implements Event {


    /**
    An int to hold the eventTime for the bank's global clock
     */
    private int eventTime;
    /**
    A boolean that indicates if this arrival was a walk-in or a drive-in
     */
    private boolean walkIn;
    /**
     A boolean that indicates if this event was an arrival or departure
     */
    private boolean type;
    /**
     * An int that holds the transaction time of this instance of an arrival
     */
    private int transTime;

    /**
     *  The constructor for this arrival class takes in and sets all
     *  of the variables described above.
     * @param eventTime The event time
     * @param walkIn Was it a walk-in?
     * @param type Was this an arrival? Always created as true
     * @param transTime How long will this customer take to complete their transactions
     */
    public Arrival(int eventTime,boolean walkIn, boolean type, int transTime){

        this.eventTime = eventTime;
        this.walkIn = walkIn;
        this.transTime = transTime;
        this.type = type;
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
     * Getter for whether this was a walk-in
     * @return true if this was a walk-in arrival
     */
    @Override
    public boolean getWalkIn() {
        return this.walkIn;
    }

    /**
     * Getter for if this was an arrival. Hardcoded
     * because this event type will always be an arrival
     * @return True; this is an arrival
     */
    @Override
    public boolean isArrival() {
        return true;
    }

    /**
     * Getter for the transaction time of this arrival event
     * @return the transaction time
     */
    public int getTransTime() {
        return this.transTime;
    }

    /**
     * Getter for the teller that created this event,
     * but Tellers do not create arrivals so we return null.
     * @return null
     */
    @Override
    public Teller getTeller() {
        return null;
    }

    /**
     * String representation of this arrival event; Mainly used for testing purposes.
     * @return A string of the event time and transaction time.
     */
    public String toString() {
        return "Arrival: " + this.type + " EventTime: " + this.eventTime + " TransTime: " + this.transTime ;
    }
}
