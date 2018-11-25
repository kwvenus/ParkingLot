import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingBoyTest {

    Car expectedCar;
    ParkingBoy expectedParkingBoy;
    ParkingTicket expectedParkingTicket;


    //##################### Story 1 #####################

    public void setUpTestforStory1(){
        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy();
    }

    @Test
    public void park() {
        //AC1: Given a parkingBoy and a car to be parked, when park, then return new parkingTicket

        setUpTestforStory1();
        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

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
        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"new");
        expectedParkingBoy.fetch(parkingTicket);
        Car car = expectedParkingBoy.getCarInControl();

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
        //AC3: Given parkingBoy and invalid ticket, when fetch, then return error message, the ticket will be confirmed as invalid ticket,  and no car can be fetched and in control by parkingBoy
        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"new");
        expectedParkingBoy.fetch(parkingTicket);
        Car nullCar = expectedParkingBoy.getCarInControl();

        assertEquals("invalid",parkingTicket.getTicketStatus());
        assertNull(nullCar);
    }

    @Test
    public void fetchWithUsedTicket(){
        // AC4: Given parkingBoy and used ticket, when fetch, then return error message, and no car can be fetched and in control by parkingBoy

        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"used");
        expectedParkingBoy.fetch(parkingTicket);
        Car nullCar = expectedParkingBoy.getCarInControl();

        assertEquals("used",parkingTicket.getTicketStatus());
        assertNull(nullCar);
    }

    @Test
    public void parkWhenParkingLotFulled(){
        //AC5: Given parkingLot with 0 space, when park, then return error message, no ticket return from parkingBoy's hand
        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy(20,20);

        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

        assertNull(expectedParkingTicket);
    }

    //##################### Story 1 #####################

    //##################### Story 2 #####################

    @Test
    public void returnCorrectMessgaeWhenFetchWithWrongTicket(){
        //AC1: Given parkingBoy, used ticket, when fetch, then return "Unrecognized parking ticket."
        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"used");
        expectedParkingBoy.fetch(parkingTicket);

        assertEquals("Unrecognized parking ticket.",expectedParkingBoy.getFetchStatus());

    }

    @Test
    public void returnCorrectMessgaeWhenFetchWithNoTicket(){
        //AC2: Given parkingBoy, when fetch, then return "Unrecognized parking ticket."
        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar,expectedParkingBoy.getOwnedParkingLot(),"used");
        expectedParkingBoy.fetch();

        assertEquals("Please provide your parking ticket.",expectedParkingBoy.getFetchStatus());

    }

    @Test
    public void returnCorrectMessageWhenParkWithNoPosition(){
        //AC3: Given parkingBoy, car, parkingLot with no position, when park, then return "Not enough position."
        setUpTestforStory1();

        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy(20,20);

        expectedParkingBoy.park(expectedCar);

        assertEquals("Not enough position.",expectedParkingBoy.getParkStatus());

    }
    //##################### Story 2 #####################

    //##################### Story 3 #####################

    @Test
    public void parktoSeondParkingLotWhenFirstParkingLotFulled(){
        //AC1: Given parkingBoy, car, parkingLotList with first lot fulled and second lot not fulled, when park, then car should be parked into second lot
        ArrayList<ParkingTicket> parkingTicketList = new ArrayList<>();

        ParkingLot parkingLot1 = new ParkingLot(20,parkingTicketList,20);
        ParkingLot parkingLot2 = new ParkingLot(20,parkingTicketList,10);
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>();

        parkingLotArrayList.add(parkingLot1);
        parkingLotArrayList.add(parkingLot2);

        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy(parkingLotArrayList);

        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

        assertEquals(parkingLot2,expectedParkingTicket.getParkingLot());
    }

    //##################### Story 3 #####################
}