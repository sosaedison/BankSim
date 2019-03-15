/**
 * This class handles the creation of all
 * the event that are read in from the driver.
 * As they are passed in as a string, methods
 * in this class parse the string into the appropriate pieces for
 * the creation of the arrival event.
 *
 * @author Sosa Edison
 * @author Michael Gustin
 *
 */
public class EventParser {


    /**
     * The line from the file.
     */
    private String cust;

    /**
     * 1 argument constructor for the EventParser
     * @param cust a line from the file
     */
    public EventParser (String cust) {

        this.cust = cust;
    }

    /**
     * This method parses through the string, breaking it up
     * into the necessary pieces for the creation of an arrival.
     * @return the new arrival event
     */
    public Event createEvent() {
        String tab = "\t";
        String[] parts = cust.split(tab); // Splitting based on how the file is formatted
        int eventTime = Integer.parseInt(parts[0]); // the eventTime
        int transTime = Integer.parseInt(parts[1]); // the transactionTime
        boolean walkIn = Boolean.parseBoolean(parts[2]); // Was this a walkIn?

        Arrival temp = new Arrival(eventTime,walkIn, true, transTime);
        return temp; // New event
    }
}
