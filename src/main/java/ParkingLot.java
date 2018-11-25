import java.util.ArrayList;

public class ParkingLot {

    private int capacity;
    private ArrayList<ParkingTicket> ticketList;
    private int spaceOccupied;

    public ParkingLot(int capacity, ArrayList<ParkingTicket> ticketList, int spaceOccupied) {
        this.capacity = capacity;
        this.ticketList = ticketList;
        this.spaceOccupied = spaceOccupied;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<ParkingTicket> getTicketList() {
        return ticketList;
    }

    public void updateTicketStatus(ParkingTicket parkingTicket, String ticketStatus){
        parkingTicket.updateTicketStatus(ticketStatus);
    }

    public void addTicketIntoList(ParkingTicket parkingTicket) {
        ticketList.add(parkingTicket);
    }

    public int getSpaceOccupied() {
        return spaceOccupied;
    }

    public void setSpaceOccupied(int spaceOccupied) {
        this.spaceOccupied = spaceOccupied;
    }
}
