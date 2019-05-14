// 4. Boats Booking System class
// Define a class to represent the complete Boat Seat Booking System, called BoatBookingSystem. It is the "main" program class for the system & it combines all of the above objects & threads in parallel. Note that this class is not a thread. See Figure 1.
// The BoatsBookingSystem class creates and coordinates the following objects, threads and thread groups:
// Two instance of the Boat class.
// The two Boat objects are shared by all the Kiosks, but each one is just accessed by a single Captain process.
// Creates 2 thread groups: one for the kiosks & one for the Captains.
// Three instance of the Kiosk class, i.e. three different Kiosk threads.
// Each of the 3 Kiosks & 2 Captains is passed the appropriate information via its constructor and started.
// The main program waits until all 5 threads have terminated. At which point it prints out the final status of the two boats, i.e. transaction and boat movement logs.
// In addition it must print out reports of what it is doing and when it has finished creating the threads and other objects, etc.



package concurrent;

import java.util.List;

public class BoatBookingSystem {

    public static void main(String[] args) {

        System.out.println("Boat Booking System: Start.");

        //Boats
        SharedBoat[] twoSharedBoats = new SharedBoat[2];
        twoSharedBoats[0] = new SharedBoat("Boat One", 20, Boat.Location.AT_JETTY);
        twoSharedBoats[1] = new SharedBoat("Boat Two", 20, Boat.Location.AT_JETTY);

        System.out.println("Boat Booking System: Boats created");

        //Thread Groups
        ThreadGroup kiosksThreadGroup = new ThreadGroup("Kiosk Thread Group");
        ThreadGroup captainsThreadGroup = new ThreadGroup("Captain Thread Group");
        System.out.println("Boat Booking System: 2 thread groups created");

        //Captains
        Captain[] twoCaptain = new Captain[2];
        twoCaptain[0] = new Captain(captainsThreadGroup,"Captain One", twoSharedBoats[0]);
        twoCaptain[1] = new Captain(captainsThreadGroup,"Captain One", twoSharedBoats[1]);
        System.out.println("Boat Booking System: Captains created");

        //Kiosks Names
        Kiosk[] threeKiosks = new Kiosk[3];
        threeKiosks[0] = new Kiosk(kiosksThreadGroup, "East Kiosk", twoSharedBoats);
        threeKiosks[1] = new Kiosk(kiosksThreadGroup,"North Kiosk", twoSharedBoats);
        threeKiosks[2] = new Kiosk(kiosksThreadGroup, "South Kiosk", twoSharedBoats);
        System.out.println("Boat Booking System: Kiosks created");



        try {

            threeKiosks[0].start();
            Thread.sleep((long) ((Math.random()*3000)+2000));
            threeKiosks[1].start();
            Thread.sleep((long) ((Math.random()*3000)+2000));
            threeKiosks[2].start();
            Thread.sleep((long) ((Math.random()*3000)+2000));
            System.out.println("Boat Booking System: Kiosks started.");

            twoCaptain[0].start();
            Thread.sleep((long) ((Math.random()*3000)+2000));
            twoCaptain[1].start();
            Thread.sleep((long) ((Math.random()*3000)+2000));
            System.out.println("Boat Booking System: Captains started.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {

            threeKiosks[0].join();
            threeKiosks[1].join();
            threeKiosks[2].join();
            twoCaptain[0].join();
            twoCaptain[1].join();
            System.out.println("Kiosks and Captains joined.");
            
        } catch (InterruptedException e) {
            System.out.println("Error: Threads are unable to join.");
            e.printStackTrace();
        }

        System.out.println("Boat Booking System: All Captains and Kiosks finished.");

        System.out.println("-------------All thread successfully terminated -------------------------");

        /////////////////////////////////

        List<Transaction> transactionListBoatOne = twoSharedBoats[0].getTransactionList();
        List<LogEntry> logEntryListBoatOne = twoSharedBoats[0].getLogEntryList();
        System.out.println(" Boat One Transactions: ");
        System.err.println(transactionListBoatOne.size()+"");

        for (int i=0 ;i<transactionListBoatOne.size();i++){
            System.out.println(" Transaction "+i+" : " + transactionListBoatOne.get(i).toString());
        }
        System.out.println(" Boat One Transactions Finished.");

        System.out.println(" Boat One Log Entry: ");
        for (int i=0; i<logEntryListBoatOne.size();i++){
            System.out.println(" Log Entry " + i + logEntryListBoatOne.get(i).toString());
        }

        System.out.println(" Boat One Log Entry Finished. ");

        //////////////////////////////////

        List<Transaction> transactionListBoatTwo;
        transactionListBoatTwo = twoSharedBoats[1].getTransactionList();
        List<LogEntry> logEntryListBoatTwo = twoSharedBoats[1].getLogEntryList();
        System.out.println(" Boat Two Transactions: ");
        System.err.println(transactionListBoatTwo.size()+"");

        for (int i=0 ;i<transactionListBoatTwo.size();i++){
            System.out.println(" Transaction "+ i +" : " + transactionListBoatTwo.get(i).toString());
        }

        System.out.println(" Boat Two Transactions Finished.");

        System.out.println(" Boat Two Log Entry: ");

        for (int i=0; i<logEntryListBoatTwo.size();i++){
            System.out.println(" Log Entry " + i + logEntryListBoatTwo.get(i).toString());
        }

        System.out.println(" Boat Two Log Entry Finished. ");

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Boat Booking System: Finish.");

    }


}
