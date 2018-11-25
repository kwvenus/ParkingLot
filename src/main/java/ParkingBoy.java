import java.util.*;

public class ParkingBoy {

    private ParkingLot ownedParkingLot;

    public ParkingBoy(){
        ArrayList<ParkingTicket> parkingTicketList = new ArrayList<>();
        this.ownedParkingLot = new ParkingLot(20, parkingTicketList, 0);
    }

    public ParkingBoy(int parkingLotCapacity, int spaceOccupied){
        ArrayList<ParkingTicket> parkingTicketList = new ArrayList<>();
        this.ownedParkingLot = new ParkingLot(parkingLotCapacity, parkingTicketList, spaceOccupied);
    }

    public ParkingLot getOwnedParkingLot() {
        return ownedParkingLot;
    }

    public ParkingTicket park(Car car){
        if (ownedParkingLot.getCapacity() <= ownedParkingLot.getSpaceOccupied()){
            System.out.println("No space in parking lot. Park failure.");
        } else if (ownedParkingLot.getCapacity() > ownedParkingLot.getSpaceOccupied()){
            ParkingTicket newTicket = new ParkingTicket(car,ownedParkingLot, "new");
            ownedParkingLot.addTicketIntoList(newTicket);
            ownedParkingLot.setSpaceOccupied(ownedParkingLot.getSpaceOccupied() + 1);
            car.setParkingTicket(newTicket);
            System.out.println("Car parked.");
            return newTicket;
        }
        return new ParkingTicket(car, ownedParkingLot, "invalid");
    }

    public Car fetch(ParkingTicket parkingTicket){
        if (ownedParkingLot.getTicketList().contains(parkingTicket) && parkingTicket.getTicketStatus() == "new" && parkingTicket.getCar().getParkingTicket().equals(parkingTicket)){
            ownedParkingLot.setSpaceOccupied(ownedParkingLot.getSpaceOccupied() + 1);
            ownedParkingLot.updateTicketStatus(parkingTicket,"used");
            System.out.println("Car fetched.");
            return parkingTicket.getCar();
        } else if (parkingTicket.getTicketStatus() == "used"){
            System.out.println("Ticket already used.");
            return new Car();
        }
        parkingTicket.updateTicketStatus("invalid");
        System.out.println("Ticket invalid.");
        return new Car();

    }
}
