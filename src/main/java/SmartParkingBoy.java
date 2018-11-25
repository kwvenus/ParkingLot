import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy() {
    }

    public SmartParkingBoy(int parkingLotCapacity, int spaceOccupied) {
        super(parkingLotCapacity, spaceOccupied);
    }

    public SmartParkingBoy(ArrayList<ParkingLot> ownedParkingLotList) {
        super(ownedParkingLotList);
    }

    @Override
    public void park(Car car){
        System.out.println("This is smart parking boy.");
        carInControl = car;
        int maxSpaceLeft = 0;
        int spaceLeft;

        for (int i = 0; i < ownedParkingLotList.size(); i++){
            spaceLeft = ownedParkingLotList.get(i).getCapacity()-ownedParkingLotList.get(i).getSpaceOccupied();
            if (spaceLeft > maxSpaceLeft) {
                ownedParkingLot = ownedParkingLotList.get(i);
                maxSpaceLeft = spaceLeft;
            }
        }

        if (ownedParkingLot.getCapacity() <= ownedParkingLot.getSpaceOccupied()){
            System.out.println("No space in parking lot. Park failure.");
            parkStatus = "Not enough position.";
            return;

        } else if (ownedParkingLot.getCapacity() > ownedParkingLot.getSpaceOccupied()){
            ParkingTicket newTicket = new ParkingTicket(carInControl,ownedParkingLot, "new");
            ownedParkingLot.addTicketIntoList(newTicket);
            ownedParkingLot.setSpaceOccupied(ownedParkingLot.getSpaceOccupied() + 1);
            carInControl.setParkingTicket(newTicket);
            carInControl = null;
            System.out.println("Car parked.");
            ticketOnHand = newTicket;
            parkStatus = "Car parked.";
            return;
        }
    }
}
