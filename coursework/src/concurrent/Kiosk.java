//2. Kiosk class
//Define a class to represent a seat booking kiosk, that can book or cancel seats on both of the two boats. Note that this class should be a thread.
//        The kiosk class must:
//         Use the instances of the Transaction class.
//         Instances of the Transaction class are used to represent a seat "booking" or
//        “cancelling” request that the kiosk makes to the two boats.
//         Create seat transactions for the two boats.
//        For example, the following shows how to create booking and cancelling seat transactions and attempt to perform them on the two boats:
//        Transaction bookfamily
//        = new Transaction( getName(), 4, "BOOK" ) ;
//        boat[0].bookSeats( bookfamily ) ;
//        Transaction cancel1seat
//        = new Transaction( getName(), 1, "CANCEL" ) ;
//        boat[1].cancelSeats( cancel1seat ) ;
//         Have private data members that hold the information associated with it - the two boats; its name.
//         A single constructor that initializes its information: the "kiosk" thread group, the two boats; its name.
//        Kiosk's behaviour is to:
//         Create at least 3 transactions for each of the two boats, at least 6 in total.
//         It should "sleep" for a random amount of time between each transaction request.
//         When it has finished print out a message to indicate this.


package concurrent;


public class Kiosk extends Thread {

    private String kiosk;
    private SharedBoat[] sharedBoat;

    Kiosk(ThreadGroup kiosksThreadGroup, String kiosk, SharedBoat[] twoSharedBoats) {

        super(kiosksThreadGroup, kiosk);
        this.kiosk = getName();
        this.sharedBoat = twoSharedBoats;

    }

    @Override
    public void run() {

        try {
            System.out.println(" Kiosks are starting to make the transaction: ");

            Transaction BoatOneTransactionOne = new Transaction(getKioskName(),4, "Booked");
            this.sharedBoat[0].bookSeats(BoatOneTransactionOne);
            System.out.println(" Transaction One - Kiosk creates " + sharedBoat[0].toString());
            Thread.sleep((long) (Math.random()*500)+500);

            Transaction BoatOneTransactionTwo = new Transaction(getKioskName(),2, "Cancelled");
            this.sharedBoat[0].bookSeats(BoatOneTransactionTwo);
            System.out.println(" Transaction Two - Kiosk creates " + sharedBoat[0].toString());
            Thread.sleep((long) (Math.random()*500)+500);

            Transaction BoatOneTransactionThree = new Transaction(getKioskName(),3, "Booked");
            this.sharedBoat[0].bookSeats(BoatOneTransactionThree);
            System.out.println(" Transaction Three - Kiosk creates " + sharedBoat[0].toString());
            Thread.sleep((long) (Math.random()*500)+500);

            Transaction BoatOneTransactionFour = new Transaction(getKioskName(),1, "Cancelled");
            this.sharedBoat[0].bookSeats(BoatOneTransactionFour);
            System.out.println(" Transaction Four - Kiosk creates " + sharedBoat[0].toString());
            Thread.sleep((long) (Math.random()*500)+500);

            System.out.println("Boat One : Transactions Finished.");

            //////////////////////////////

            Transaction BoatTwoTransactionOne = new Transaction(getKioskName(),3, "Booked");
            this.sharedBoat[1].bookSeats(BoatTwoTransactionOne);
            System.out.println(" Transaction Five - Kiosk creates " + sharedBoat[1].toString());
            Thread.sleep((long) (Math.random()*500)+500);

            Transaction BoatTwoTransactionTwo = new Transaction(getKioskName(),1, "Cancelled");
            this.sharedBoat[1].bookSeats(BoatTwoTransactionTwo);
            System.out.println(" Transaction Six - Kiosk creates " + sharedBoat[1].toString());
            Thread.sleep((long) (Math.random()*500)+500);

            Transaction BoatThreeTransactionThree = new Transaction(getKioskName(),5, "Booked");
            this.sharedBoat[1].bookSeats(BoatThreeTransactionThree);
            System.out.println(" Transaction Seven - Kiosk creates " + sharedBoat[1].toString());
            Thread.sleep((long) (Math.random()*500)+500);

            Transaction BoatFourTransactionFour = new Transaction(getKioskName(),2, "Cancelled");
            this.sharedBoat[1].bookSeats(BoatFourTransactionFour);
            System.out.println(" Transaction Eight - Kiosk creates " + sharedBoat[1].toString());
            Thread.sleep((long) (Math.random()*500)+500);


            System.out.println("Boat Two : Transactions Finished.");

        }catch (Exception err){

            err.printStackTrace();
            System.out.println("The kiosk error is " + err.toString());

        }

        System.out.println("Kiosk : All the booking and cancelling transactions are created!");

    }
    private String getKioskName(){ return kiosk; }

}

