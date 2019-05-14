package concurrent;

import java.util.List;

/** *********************************************************************
 * File:      concurrent.SeatBooking.java	[INTERFACE]
 * Author:    P. Howells
 * Contents:  7SENG007W CWK:
 *            This provides the interface for a concurrent.Kiosk booking seats on
 *            a boat.
 * Created:   5/3/19
 * Modified:  12/3/19
 * Version:   1.0
 ************************************************************************ */

public interface SeatBooking
{

    public int getCapacity( ) ;                    // returns boat's passenger capacity

    public int getNumberSeatsBooked( ) ;           // returns number of seats booked

    public int getNumberSeatsAvailable( ) ;        // returns number of seats available


    public void bookSeats( Transaction tr ) ;       // concurrent.Boat seat booking transaction


    public boolean cancelSeats( Transaction tr ) ;  // concurrent.Boat seat cancelling transaction
    // returns true if cancelled


    void printTransactionsLog( ) ;          // prints out the transactions performed

}




