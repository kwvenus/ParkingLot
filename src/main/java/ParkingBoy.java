import java.util.*;

public class ParkingBoy {

    protected ParkingLot ownedParkingLot;
    protected ArrayList<ParkingLot> ownedParkingLotList = new ArrayList<>();
    protected Car carInControl;
    //Car received to be parked / Car fetched
    protected ParkingTicket ticketOnHand;
    //Ticket generated after car parked / Ticket received and request to fetch a car

    protected String fetchStatus;
    protected String parkStatus;

    public ParkingBoy(){
        ArrayList<ParkingTicket> parkingTicketList = new ArrayList<>();
        ownedParkingLotList.add(new ParkingLot(20, parkingTicketList, 0));
    }

    public ParkingBoy(int parkingLotCapacity, int spaceOccupied){
        ArrayList<ParkingTicket> parkingTicketList = new ArrayList<>();
        ownedParkingLotList.add(new ParkingLot(parkingLotCapacity, parkingTicketList, spaceOccupied));
    }

    public ParkingBoy(ArrayList<ParkingLot> ownedParkingLotList){
        this.ownedParkingLotList = ownedParkingLotList;
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

    public String getFetchStatus() {
        return fetchStatus;
    }

    public String getParkStatus() {
        return parkStatus;
    }

    public void park(Car car){
        System.out.println("This is parking boy.");
        carInControl = car;

        for (int i = 0; i < ownedParkingLotList.size(); i++){
            ownedParkingLot = ownedParkingLotList.get(i);

            if (ownedParkingLot.getCapacity() <= ownedParkingLot.getSpaceOccupied()){
                System.out.println("No space in parking lot. Park failure.");
                if (ownedParkingLotList.lastIndexOf(ownedParkingLot) == ownedParkingLotList.size() -1 ){
                    parkStatus = "Not enough position.";
                    return;
                }

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

    public void fetch(ParkingTicket parkingTicket){
        ticketOnHand = parkingTicket;

        for (int i = 0; i < ownedParkingLotList.size(); i++){
            ownedParkingLot = ownedParkingLotList.get(i);

            if (ownedParkingLot.getTicketList().contains(ticketOnHand) && ticketOnHand.getTicketStatus() == "new" && ticketOnHand.getCar().getParkingTicket().equals(ticketOnHand)){
                ownedParkingLot.setSpaceOccupied(ownedParkingLot.getSpaceOccupied() + 1);
                ownedParkingLot.updateTicketStatus(ticketOnHand,"used");
                System.out.println("Car fetched.");
                fetchStatus = "Car fetched";
                carInControl = ticketOnHand.getCar();
                return;
            } else if (ticketOnHand.getTicketStatus() == "used"){
                if (ownedParkingLotList.lastIndexOf(ownedParkingLot) == ownedParkingLotList.size() -1 ){
                    System.out.println("Ticket already used.");
                    fetchStatus = "Unrecognized parking ticket.";
                    return;
                }
            }
            if (ownedParkingLotList.lastIndexOf(ownedParkingLot) == ownedParkingLotList.size() -1 ){
                ticketOnHand.updateTicketStatus("invalid");
                fetchStatus = "Ticket invalid";
                System.out.println("Ticket invalid.");
            }
        }
    }

    public void fetch(){
        System.out.println("Please provide your parking ticket.");
        fetchStatus = "Please provide your parking ticket.";
        return;
    }
}
