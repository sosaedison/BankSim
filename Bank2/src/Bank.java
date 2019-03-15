
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * The bank class dishes out event to the tellers that
 * were added to the PriorityQueue from the EventParser.
 * It then pulls a number of calculations from the tellers
 * to give us stats on how this day at the bank went. In
 * other words, the bank has a toString that provides a
 * readable sentence about the necessary stats on waitTime total,
 * waitTime per customer and total elapsed time as well.
 *
 * @author Sosa Edison
 * @author Michael Gustin
 */
public class Bank {

    /**
     * Random instance for adding events to the tellers
     */
	private Random rand = new Random();
    /**
     * Comparator for ordering the PriorityQueue
     */
    private EventComparator comparator = new EventComparator();
    /**
     * PriorityQueue for the customers; Drive-in arrivals then departures,
     * then walk-in arrivals and departures.
     */
    private PriorityQueue<Event> eventLine = new PriorityQueue<>(comparator);
    /**
     * An ArrayList of employees of the bank since the size can vary
     */
    private ArrayList<Teller> employees = new ArrayList<>();
    /**
     * ArrayList of Drive in events that we remove from at the appropriate time
     */
    private ArrayList<Event> driveInQ = new ArrayList<>();
    /**
     * Global clock int
     */
    private int clock = 0;
    /**
     * Boolean for if the driveThru is open
     */
    private boolean driveInOpen;
    /**
     * int for the total number of customers
     */
    private int numCust = 0;


    /**
     * 2 Parameter constructor for Bank, based on the number of Tellers
     * and whether the drive-thru is open.
     * @param amTellers the set number of tellers
     * @param driveThru if the drive-thru is open
     */
    public Bank(int amTellers, boolean driveThru) {
    	this.driveInOpen = driveThru;
        startWeek(amTellers);
    }

    /**
     * Sets up the bank Tellers
     * @param amtTellers the number of tellers wanted
     */
    private void startWeek(int amtTellers) {
        for (int i  =0; i< amtTellers; i++) {
            employees.add(new Teller(i+1, this));
        }
    }

    /**
     * Adds events to the PriorityQueue
     * @param temp the event we're adding
     */
    public void addCust(Event temp){
        eventLine.add(temp);
    }


    /**
     * This run method is how the bank runs.
     * After polling and event from the PriorityQueue,
     * we check if it's arrival and then if it's a drive-in
     * that can be handled at the current moment. We then add
     * all walk-ins to the teller with the shortest line.
     */
    public void run(){
        Event temp;
        while (!eventLine.isEmpty()) {
            temp = eventLine.poll();
            clock = temp.getEventTime();
            if (temp.isArrival()) { // Arrival Event?
            	numCust++; // add to total customers helped
                if (!temp.getWalkIn() && driveInOpen) { // is it a drive-in and is the driveThru open?
                	Teller tellerTemp = null;
            		for(Teller t: employees) {
            			if(t.isAvailable()) {
            				tellerTemp = t;
            			}
            		}
                	if(tellerTemp!= null) {
                		tellerTemp.addArrival(temp);
                	}else {
                		driveInQ.add(temp); // add the event to the drive-in line
                	}
                    
                }else { // find the teller with the shortest line and add the event
                    getShortestLine().addArrival(temp);
                }
            } else {
            	temp.getTeller().getNextEvent(); // This was departure that we polled from the Queue
            }
        }
    }

    /**
     * Method that gives a drive-in arrival if there is one
     * @return the drive-in event
     */
    public Event checkDriveIn() {
    	if(!this.driveInQ.isEmpty()) {
    		return this.driveInQ.remove(0);
    	}
    	return null;
    }

    /**
     * Method that helps us find the teller with the shortest line
     * @return the teller with the shortest line
     */
    private Teller getShortestLine() {
        Teller temp = employees.get(rand.nextInt(employees.size()));
        for (Teller t: employees) {
            if (t.getLineLength() < temp.getLineLength()) {
                temp = t;
            }
        }
        return temp;
    }
    
    /**
     * How we order the customers based on ArrivalTime
     */
    private class EventComparator implements Comparator<Event> {
        @Override
        public int compare(Event a, Event b) {

            if(a.getEventTime() > b.getEventTime()) {
                return 1;
            } 
            else if(a.getEventTime() < b.getEventTime()) 
            {
                return -1;
            }
            else if(!a.isArrival())
            {
            	return -1;
            }
            else if(!b.isArrival()) 
            {
            	return 1;
            }
            else if(!a.getWalkIn()) 
            {
            	return -1;
            }
            else if(!b.getWalkIn()) 
            {
            	return 1;
            }
            return 0;
        }
    }

    /**
     * Getter for the global clock of the bank
     * @return the current time
     */
    public int giveClock()
    {
        return clock;
    }


    /**
     * ToString of the bank that puts together all the important stats that we need in order to be able to
     * evaluate the changes we might need to make to the amount of tellers or the driveThru in order
     * to make it run smoother.
     * @return the important stats
     */
    public String toString() {
    	String ret;
    	int sumWait = 0;
    	for(Teller t : employees)
    	{
    		sumWait += t.getSumWaitTime();
    	}
    	
    	ret = "Total elapsed time: " + clock;
    	ret = ret + "\nTotal customers helped: "+ numCust;
    	ret = ret + "\nAvg. wait time: "+String.format("%.3f",(float)sumWait/numCust);
    	for(Teller t : employees)
    	{
    		ret = ret + "\nTeller "+t.getID()+":        % time idle: "+String.format("%.3f",(float)(100*t.getIdleTime())/clock)+"     Number of customers helped: "+t.getNumHelped();
    	}
    	return ret;
    }
}
