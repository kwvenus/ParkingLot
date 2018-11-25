public class Car {

    private ParkingTicket parkingTicket;

    public Car(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public  Car(){

    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }
}

