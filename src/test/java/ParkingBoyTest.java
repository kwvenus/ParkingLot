import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingBoyTest {

    Car expectedCar;
    ParkingBoy expectedParkingBoy;
    ParkingTicket expectedParkingTicket;


    public void setUpTestforStory1(){
        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy();
    }

    @Test
    public void park() {
        //AC1: Given a parkingBoy and a car to be parked, when park, then return new parkingTicket

        setUpTestforStory1();
        expectedParkingTicket = expectedParkingBoy.park(expectedCar);

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"new");

//        System.out.println(expectedParkingTicket.getCar());
//        System.out.println(expectedParkingTicket.getParkingLot());
//        System.out.println(expectedParkingTicket.getTicketStatus());
//        System.out.println(parkingTicket.getCar());
//        System.out.println(parkingTicket.getParkingLot());
//        System.out.println(parkingTicket.getTicketStatus());
        assertEquals(expectedParkingTicket,parkingTicket);
    }

    @Test
    public void fetch() {
        //AC1: Given parked car, right parkingTicket and parkingBoy, when fetch, then return right car

        setUpTestforStory1();
        expectedParkingTicket = expectedParkingBoy.park(expectedCar);

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"new");
        Car car = expectedParkingBoy.fetch(parkingTicket);

//        System.out.println(expectedCar.getParkingTicket().getCar());
//        System.out.println(expectedCar.getParkingTicket().getParkingLot());
//        System.out.println(expectedCar.getParkingTicket().getTicketStatus());
//        System.out.println(car.getParkingTicket().getCar());
//        System.out.println(car.getParkingTicket().getParkingLot());
//        System.out.println(car.getParkingTicket().getTicketStatus());
        assertEquals(expectedCar.getParkingTicket(),car.getParkingTicket());
    }

    @Test
    public void invalidFetch(){
        //AC3: Given parkingBoy and invalid ticket, when fetch, then return error message, the ticket will be confirmed as invalid ticket, and return null car
        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"new");
        Car nullCar = expectedParkingBoy.fetch(parkingTicket);

        assertEquals("invalid",parkingTicket.getTicketStatus());
        assertNull(nullCar.getParkingTicket());
    }

    @Test
    public void fetchWithUsedTicket(){
        // AC4: Given parkingBoy and used ticket, when fetch, then return error message, and null car

        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"used");
        Car nullCar = expectedParkingBoy.fetch(parkingTicket);

        assertEquals("used",parkingTicket.getTicketStatus());
        assertNull(nullCar.getParkingTicket());
    }

    @Test
    public void parkWhenParkingLotFulled(){
        //AC5: Given parkingLot with 0 space, when park, then return error message, the return parkingTicket is valid
        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy(20,20);

        expectedParkingTicket = expectedParkingBoy.park(expectedCar);

        assertEquals("invalid",expectedParkingTicket.getTicketStatus());

    }
    
}