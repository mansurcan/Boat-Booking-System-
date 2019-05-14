package concurrent;

/** *********************************************************************
 * File:      Transaction.java
 * Author:    P. Howells
 * Contents:  7SENG007W CWK:
 *            Provides the basic data structure for a ferry seat
 *            transaction. That is the kiosk name, number of seats
 *            to either "BOOK" or "CANCEL".
 * Created:   5/3/19
 * Modified:  12/3/19
 * Version:   1.1
 ************************************************************************ */

public class Transaction
{
    private final String kioskName ;
    private final int    numberOfSeats ;
    private final String type ;            // "BOOK" or "CANCEL"

    public Transaction(  String kiosk, int numSeats, String type )
    {
        this.kioskName     = kiosk ;
        this.numberOfSeats = numSeats ;
        this.type          = type ;
    }


    public String getKioskName( )     { return this.kioskName ; }

    public int    getNumberOfSeats( ) { return this.numberOfSeats ; }

    public String getType( )          { return this.type ; }


    public String toString( )
    {
        String transaction
                = new String( "[ " +
                "Kiosk: " + this.kioskName     + ", " +
                "Seats: " + this.numberOfSeats + ", " +
                "Type: "  + this.type                 +
                "]"  ) ;

        return  transaction ;
    }

} // Transaction



