import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy() {
    }

    public SuperSmartParkingBoy(int parkingLotCapacity, int spaceOccupied) {
        super(parkingLotCapacity, spaceOccupied);
    }

    public SuperSmartParkingBoy(ArrayList<ParkingLot> ownedParkingLotList) {
        super(ownedParkingLotList);
    }

    @Override
    public void park(Car car){
        System.out.println("This is super smart parking boy.");
        carInControl = car;
        Double maxAvailablePositionRate = (double) 0;
        Double calculateAvailablePositionRate;
        int spaceLeft;

        for (int i = 0; i < ownedParkingLotList.size(); i++){
            spaceLeft = ownedParkingLotList.get(i).getCapacity()-ownedParkingLotList.get(i).getSpaceOccupied();
            calculateAvailablePositionRate = (double) spaceLeft / (double) ownedParkingLotList.get(i).getSpaceOccupied();
            if (calculateAvailablePositionRate > maxAvailablePositionRate) {
                ownedParkingLot = ownedParkingLotList.get(i);
                maxAvailablePositionRate = calculateAvailablePositionRate;
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
