package concurrent;

/** *********************************************************************
 * File:      concurrent.Boat.java	 [INTERFACE]
 * Author:    P. Howells
 * Contents:  7SENG007W CWK:
 *            This provides the interface for a boat, used by a concurrent.Captain.
 * Created:   8/3/19
 * Modified:  12/3/19
 * Version:   1.1
 ************************************************************************ */

public interface Boat
{

    public enum Location { AT_JETTY, ON_LAKE }


    public final int      DEFAULT_CAPACITY  = 10 ;
    public final Location INITIAL_LOCATION = Location.AT_JETTY ;


    public String getName( ) ;          // returns boat's name

    public Location getLocation( ) ;    // returns boat's location

    // concurrent.Captain's actions on the boat
    public void tieUp_AtJetty() ;       // boat arrives at jetty

    public void castOff_TourLake() ;    // boat leaves jetty tours lake


    public void printBoatsLog( ) ;      // print boat's log

}