import java.util.ArrayList;

/**
 * This teller class works with a number of things:
 * Arrivals, Departures, eventTimes, transTimes, idleTime
 * and the waitTime of the customers. All of this is needed
 * in order to get the results we need for the simulation.
 *
 * @author Sosa Edison
 * @author Michael Gustin
 */
public class Teller {
    /**
     * int for the idleTime of this teller
     */
    private int idleTime = 0;
    /**
     * Hold int used to calculate the idleTime
     */
    private int stopTime = 0;
    /**
     * The waitTime of the customers in this teller's line
     */
    private int sumWaitTime = 0;
    /**
     * ArrayList used as the line of customers
     */
    private ArrayList<Event> line = new ArrayList<>();
    /**
     * Int that gives us a way to identify the teller
     */
    private int ID;
    /**
     * Boolean that hold whether this tell is currently helping a customer
     */
    private boolean available = true;
    /**
     * An instance of bank for the teller to work with
     */
    private Bank bank;
    /**
     * An int for number of customers helped.
     */
    private int numHelped = 0;

    /**
     * 2 Parameter constructor for teller.
     * @param ID An Id for this teller
     * @param bank The instance bank they'll be working with
     */
    public Teller(int ID, Bank bank) {
        this.ID = ID;
        this.bank = bank;
    }

    /**
     * Method that adds an arrival to the tellers' line of customers
     * @param event the event we're adding to the line
     */
    public void addArrival(Event event)
    {
    	if(line.isEmpty() && available) {
    		idleTime += (bank.giveClock() - stopTime); // calculating any idleTime
    		line.add(event);
    		bank.addCust(createDep()); // Creates departure if not helping someone
    	} else {
    		line.add(event); // Just add it and wait
    	}
    	available = false;
    }

    /**
     * Method that creates a departure event and
     * removes the first event from the teller line
     * @return the new departure
     */
    public Event createDep() {
    	Event cur = line.remove(0);
    	sumWaitTime = sumWaitTime + (this.bank.giveClock() - cur.getEventTime()); // calc any waitTime
    	Event dep = new Departure((cur.getTransTime()+this.bank.giveClock()),false, this);
    	numHelped++; // increase the total customers helped
    	return dep;
    }

    /**
     * Creating a drive-thru departure event for the bank event Queue
     * @param event drive in arrival
     * @return the new departure
     */
    private Event addDriveIn(Event event) {
    	sumWaitTime = sumWaitTime + (this.bank.giveClock() - event.getEventTime());
    	Event dep = new Departure((event.getTransTime()+this.bank.giveClock()),false, this);
    	numHelped++;// increase the total customers helped
    	return dep;
    }

    /**
     * Method that handles the creation and addition of a
     * drive-in customer to the bank event Queue. It's important that
     * drive-in's happen first, so this called after every event.
     */
    public void getNextEvent() {
        Event temp = this.bank.checkDriveIn(); // Was it a drive-in event?
        if(temp != null) {
            this.bank.addCust(addDriveIn(temp)); // add a drive in departure
            this.available = false;
        }else if(!line.isEmpty()) {
            this.bank.addCust(createDep());
            this.available = false;
        }else {
            stopTime = bank.giveClock();
            this.available = true;
        }
    }

    /**
     * Getter for the size of teller line
     * @return teller line length
     */
    public int getLineLength() {
    	return line.size();
    }

    /**
     * Getter for the ID of this teller
     * @return the ID for this teller
     */
    public int getID() {
    	return this.ID;
    }

    /**
     * Getter for the Number of Customers helped
     * @return the number of customers helped
     */
    public int getNumHelped() {
    	return this.numHelped;
    }

    /**
     * Getter for the idleTime of this teller
     * @return the idleTime for this teller
     */
    public int getIdleTime()
    {
    	return idleTime;
    }

    /**
     * Getter for whether this teller is avaible to help a drive-in arrival
     * @return whether this teller is with a customer
     */
    public boolean isAvailable() {
    	return this.available;
    }

    /**
     * Getter for the total waitTime of all the customers
     * @return the total waitTime of all the customers
     */
    public int getSumWaitTime()
    {
    	return sumWaitTime;
    }
    

}
