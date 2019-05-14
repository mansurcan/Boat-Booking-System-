package concurrent;

/** *********************************************************************
 * File:      LogEntry.java
 * Author:    P. Howells
 * Contents:  7SENG007W CWK:
 *            Provides the data structure for a boat's log entry.
 * Created:   8/3/19
 * Modified:  12/3/19
 * Version:   1.0
 ************************************************************************ */

public class LogEntry
{

    public enum Action { DockAtJetty, CastOffFromJetty } ;

    // To use these enumerated type values outside of this class:
    //   Action action = LogEntry.Action.DockAtJetty


    private final String Name ;
    private final int    numPassengers ;
    private final Action action ;

    public LogEntry( String name, int numPass, Action action )
    {
        this.Name          = name ;
        this.numPassengers = numPass ;
        this.action        = action ;
    }

    public String getBoatName( )      { return Name ; }

    public int    getNumPassengers( ) { return numPassengers ; }

    public Action getAction( )        { return action ; }


    public String toString( )
    {
        return  new String( "[ " +
                "Boat: "            + Name           + ", " +
                "No. Passengers: "  + numPassengers  + ", " +
                "Action: "          + action                +
                "]"  ) ;
    }

} // LogEntry



