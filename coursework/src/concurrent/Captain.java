//3. Captain class
//Define a class to represent a boat captain that repeated moves the boat from the jetty and tours the lake if it is at least half full of passengers, then returns to the jetty. Note that this class is a thread.
//        The Captain class must:
//         Have private data members that hold the information associated with him/her, i.e. the
//        thread group he/she is in; his/her Boat; his/her name.
//         A single constructor that initializes its information, including placing the captain in the "captain" thread group.
//        Captain's behaviour is to:
//         It takes the boat on 4 tours, i.e. leave jetty and tour lake then return to the jetty.
//         He/she should "sleep" for a random amount of time between each attempt to refill the
//        printer's paper tray.
//         When he/she has made three attempt to refill the printer's paper tray, print out a message.


package concurrent;

public class Captain extends Thread{

    private String name;
    private SharedBoat sharedBoat;

    Captain(ThreadGroup threadGroup, String name, SharedBoat twoSharedBoats) {
        super(threadGroup, name);
        this.name = getName();
        this.sharedBoat = twoSharedBoats;

        System.out.println("Starting " + name + " thread.");

    }

    @Override
    public void run() {

        try {
            System.out.println("Captains start tours.");

            Boat.Location boatLocation = this.sharedBoat.getLocation();
            int capacityBoat = this.sharedBoat.getCapacity();
            double seatsBooked = (double) this.sharedBoat.getNumberSeatsBooked();

            if (boatLocation == Boat.Location.AT_JETTY && seatsBooked >= capacityBoat/2) {

                this.sharedBoat.castOff_TourLake();
                System.out.println("Captain: " + name + " casts off with " + sharedBoat.getName());
                Thread.sleep((long) (Math.random()*500)+500);

                this.sharedBoat.tieUp_AtJetty();
                System.out.println("Captain: " + name + " ties up " + sharedBoat.getName());
                Thread.sleep((long) (Math.random()*500)+500);

                this.sharedBoat.castOff_TourLake();
                System.out.println("Captain: " + name + " casts off with " + sharedBoat.getName());
                Thread.sleep((long) (Math.random()*500)+500);

                this.sharedBoat.tieUp_AtJetty();
                System.out.println("Captain: " + name + " ties up " + sharedBoat.getName());
                Thread.sleep((long) (Math.random()*500)+500);

            }else{

                System.out.println("Captains can't tour.");
            }
        } catch (Exception e) {
            System.out.println("Error: Unable to tour");
            e.printStackTrace();
        }
    }
}


