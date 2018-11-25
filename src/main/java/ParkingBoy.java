import java.util.*;

public class ParkingBoy {

    private ParkingLot ownedParkingLot;
    private Car carInControl;
    //Car received to be parked / Car fetched
    private ParkingTicket ticketOnHand;
    //Ticket generated after car parked / Ticket received and request to fetch a car

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

    public Car getCarInControl() {
        return carInControl;
    }

    public ParkingTicket getTicketOnHand() {
        return ticketOnHand;
    }

    public void park(Car car){
        carInControl = car;
        if (ownedParkingLot.getCapacity() <= ownedParkingLot.getSpaceOccupied()){
            System.out.println("No space in parking lot. Park failure.");
            return;
        } else if (ownedParkingLot.getCapacity() > ownedParkingLot.getSpaceOccupied()){
            ParkingTicket newTicket = new ParkingTicket(carInControl,ownedParkingLot, "new");
            ownedParkingLot.addTicketIntoList(newTicket);
            ownedParkingLot.setSpaceOccupied(ownedParkingLot.getSpaceOccupied() + 1);
            carInControl.setParkingTicket(newTicket);
            carInControl = null;
            System.out.println("Car parked.");
            ticketOnHand = newTicket;
            return;
        }
    }

    public void fetch(ParkingTicket parkingTicket){
        ticketOnHand = parkingTicket;
        if (ownedParkingLot.getTicketList().contains(ticketOnHand) && ticketOnHand.getTicketStatus() == "new" && ticketOnHand.getCar().getParkingTicket().equals(ticketOnHand)){
            ownedParkingLot.setSpaceOccupied(ownedParkingLot.getSpaceOccupied() + 1);
            ownedParkingLot.updateTicketStatus(ticketOnHand,"used");
            System.out.println("Car fetched.");
            carInControl = ticketOnHand.getCar();
            return;
        } else if (ticketOnHand.getTicketStatus() == "used"){
            System.out.println("Ticket already used.");
            return;
        }
        ticketOnHand.updateTicketStatus("invalid");
        System.out.println("Ticket invalid.");
    }
}
