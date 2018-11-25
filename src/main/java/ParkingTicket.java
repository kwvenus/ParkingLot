public class ParkingTicket {

    private Car car;
    private ParkingLot parkingLot;
    private String ticketStatus;
    // 3 types of ticket status: new, used, invalid

    public ParkingTicket(Car car, ParkingLot parkingLot, String ticketStatus) {
        this.car = car;
        this.parkingLot = parkingLot;
        this.ticketStatus = ticketStatus;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void updateTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }
}
