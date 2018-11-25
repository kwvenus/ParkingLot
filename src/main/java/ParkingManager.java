import java.util.ArrayList;
import java.util.Random;

public class ParkingManager extends ParkingBoy{

    private ArrayList<ParkingBoy> parkingBoyArrayList;
    private ArrayList<ParkingLot> parkingLotArrayList;

    private String errorMessageFromManager;

    private ParkingBoy parkingBoyAssigned;

    public ParkingManager(ArrayList<ParkingBoy> parkingBoyArrayList,  ArrayList<ParkingLot> parkingLotArrayList) {
        this.parkingBoyArrayList = parkingBoyArrayList;
        this.parkingLotArrayList = parkingLotArrayList;
        this.parkingBoyArrayList.add(this);
        this.ownedParkingLotList = parkingLotArrayList;
    }

    private void getRandomParkingBoy(){
        Random randomNumber = new Random();
        int randomParkingBoyIndex = randomNumber.nextInt(parkingBoyArrayList.size());
        parkingBoyAssigned = parkingBoyArrayList.get(randomParkingBoyIndex);
        if (parkingBoyAssigned==this){
            System.out.println("Assigned to myself. I will be changed from parking manager to parking boy soon...");
        }
    }


    public ParkingBoy getParkingBoyAssigned() {
        return parkingBoyAssigned;
    }

    public String getErrorMessageFromManager() {
        return errorMessageFromManager;
    }

    public void assignParkJob(Car car){
        System.out.println("This is parking manager. Assigning job to parking boy for car parking...");

        getRandomParkingBoy();

        this.carInControl = car;
        parkingBoyAssigned.park(this.carInControl);
        this.ticketOnHand = parkingBoyAssigned.ticketOnHand;
    }

    public void assignFetchJob(ParkingTicket parkingTicket) {
        System.out.println("This is parking manager. Assigning job to parking boy for car fetching...");

        getRandomParkingBoy();

        this.ticketOnHand = parkingTicket;
        parkingBoyAssigned.fetch(this.ticketOnHand);
        this.carInControl = parkingBoyAssigned.carInControl;

        if (parkingBoyAssigned.fetchStatus != "Car fetched."){
            errorMessageFromManager = "This is parking manager. Fetch car failure due to reason: ";
            this.fetchStatus = parkingBoyAssigned.fetchStatus;
            errorMessageFromManager += this.fetchStatus;
            System.out.println(errorMessageFromManager);
        }
    }
}
