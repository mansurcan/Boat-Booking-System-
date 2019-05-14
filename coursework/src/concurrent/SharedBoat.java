
//
//1. A Shared Boat class
//Define a class to model a shared Boat.
//
//        Note that this class should be a "monitor", also it should NOT be a thread.
//        The Boat class must: Implements the SeatBooking and Boat interfaces.
//        These interfaces define the complete interface to the Boat for both kiosks and Captains.
//         Accepts instances of the Transaction class represent a seat "booking" or “cancelling” request that the three kiosks make to the two boats.
//         Uses instances of the LogEntry class to record the captain's actions with the boat.
//         Have private data members that hold the information associated with the boat, i.e. the Boat's name, its seat capacity, the number of seats available, its location. Plus it keeps:
//         a seats booking transaction log, i.e. a list of instances of the Transaction class;
// a boat action log, i.e. a list of instances of the LogEntry class.
//         A single constructor that initializes the Boat.
//         A toString() method that returns a String representation of the current state of the Boat. For example:
//        [ PleasureBoat: McBoatFace, Capacity: 20, Seats Available: 20, Seats Booked: 0]
//        Boat Behaviour
//        An instance of the Boat class should:
//         Allow kiosks to book seats using the bookSeats( transaction ) method provided it has sufficient seats available otherwise the booking request must wait until seats are available.
//         Allow kiosks to cancel seats using the cancelSeats( transaction ) method provided at least the number of seats to be cancelled have been booked, otherwise the cancellation request fails .
//         In addition, seats can only be booked or cancelled if the boat is at the jetty. If it is touring the lake then booking seats has to wait until the boat returns to the jetty; but cancelling seats does not wait but fails.
//         The boat keeps a record of the booking/cancelling transactions carried out.
//         The captain uses the castOff_TourLake() method to move the boat - “cast off and tour the lake”, provided the boat is at the jetty, the boat is at least half full of passengers, i.e. seats booked.
//         The captain uses the tieUp_AtJetty() method to move the boat - “tie up at the jetty after touring the lake”, provided the boat is on the lake. The result of this is that it resets the seats available to the capacity.
//         The boat keeps a record of the its movements carried out by its captain, i.e. log entries.
//         The boat status must be a correct record of its seat bookings, transaction and movement history & must not suffer from "data corruption & interference".
//         Each user (kiosk or captain) must have "mutual exclusive" access to the boat while it operates on it.
//         Allow any user of the boat, including itself, to call the toString( ) method, to get a String representation of its status.
//


package concurrent;
//monitor

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SharedBoat implements SeatBooking, Boat{

    private String boatName;
    private int capacitySeats;
    private Location locationBoat;
    private int seatsAvailable;
    private Transaction transaction;
    private List<Transaction> transactionList = new ArrayList<>();
    private List<LogEntry> logEntryList = new ArrayList<>();

    //Constructor
    public SharedBoat(String boatName, int capacitySeat, Location locationB){

        this.capacitySeats = capacitySeat;
        this.boatName = boatName;
        this.locationBoat = locationB;
        this.seatsAvailable = capacitySeat;

    }

    @Override
    public synchronized void bookSeats(Transaction transaction) {

        this.transaction = transaction;
        this.transactionList.add(this.transaction);

        while(this.transaction.getNumberOfSeats() > this.getNumberSeatsAvailable() || locationBoat.equals(Boat.Location.ON_LAKE)) {
            try{
                wait(500);
            }
            catch (Exception e){
                Logger.getLogger(SharedBoat.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Error: Unable to book a seat.");
                e.printStackTrace();
            }
        }

        this.seatsAvailable = this.getNumberSeatsAvailable() - this.transaction.getNumberOfSeats();
        notifyAll();

    }

    @Override
    public synchronized boolean cancelSeats(Transaction transaction) {

        this.transaction = transaction;
        this.transactionList.add(this.transaction);

        if (this.getNumberSeatsBooked() >= this.transaction.getNumberOfSeats() && locationBoat.equals(Location.AT_JETTY)){
            seatsAvailable = this.getNumberSeatsAvailable() + this.transaction.getNumberOfSeats();

            notifyAll();
            return true;
        }
        return false;
    }

    @Override
    public synchronized void printTransactionsLog() {throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public synchronized void castOff_TourLake() {
        if ( getNumberSeatsBooked() >= getCapacity()/2) {

            this.locationBoat = Boat.Location.AT_JETTY;

            notifyAll();

        }
    }

    @Override
    public synchronized void tieUp_AtJetty() {

        this.locationBoat = Boat.Location.AT_JETTY;
        seatsAvailable = getCapacity();

        notifyAll();

    }

    public synchronized String toString(){

        return " [ Pleasure Boat : " +
                this.boatName + ", Capacity : " + this.capacitySeats +
                ", Seats Available : " +
                this.seatsAvailable +
                ", Seats Booked : " +
                this.getNumberSeatsBooked() +
                " ] ";
    }


    @Override
    public synchronized void printBoatsLog() {
        for (LogEntry entryLog : logEntryList){
            logEntryList.add(entryLog);
            System.out.println(entryLog.toString());
        }
        notifyAll();
    }

    @Override
    public synchronized String getName() { return boatName; }

    @Override
    public synchronized Location getLocation() { return locationBoat; }

    @Override
    public synchronized int getCapacity() { return capacitySeats; }

    @Override
    public synchronized int getNumberSeatsBooked() { return  getCapacity() - getNumberSeatsAvailable(); }

    @Override
    public synchronized int getNumberSeatsAvailable() { return seatsAvailable; }


    List<Transaction> getTransactionList(){ return transactionList;}

    List<LogEntry> getLogEntryList() { return logEntryList; }

}
