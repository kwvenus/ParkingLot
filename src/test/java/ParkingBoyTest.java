import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingBoyTest {

    Car expectedCar;
    ParkingBoy expectedParkingBoy;
    ParkingTicket expectedParkingTicket;
    ArrayList<ParkingTicket> parkingTicketList = new ArrayList<>();
    ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>();

    ParkingLot parkingLot1 = new ParkingLot(20, parkingTicketList, 20);
    ParkingLot parkingLot2 = new ParkingLot(20, parkingTicketList, 10);

    SuperSmartParkingBoy superSmartParkingBoy;
    ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>();

    //##################### Story 1 #####################

    public void setUpTestforStory1() {
        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy();
    }

    public void setUpTestforStory3() {

        parkingLotArrayList.add(parkingLot1);
        parkingLotArrayList.add(parkingLot2);

        expectedCar = new Car();
    }

    @Test
    public void park() {
        //AC1: Given a parkingBoy and a car to be parked, when park, then return new parkingTicket

        setUpTestforStory1();
        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar, expectedParkingBoy.getOwnedParkingLot(), "new");

//        System.out.println(expectedParkingTicket.getCar());
//        System.out.println(expectedParkingTicket.getParkingLot());
//        System.out.println(expectedParkingTicket.getTicketStatus());
//        System.out.println(parkingTicket.getCar());
//        System.out.println(parkingTicket.getParkingLot());
//        System.out.println(parkingTicket.getTicketStatus());
        assertEquals(expectedParkingTicket, parkingTicket);
    }

    @Test
    public void fetch() {
        //AC1: Given parked car, right parkingTicket and parkingBoy, when fetch, then return right car

        setUpTestforStory1();
        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar, expectedParkingBoy.getOwnedParkingLot(), "new");
        expectedParkingBoy.fetch(parkingTicket);
        Car car = expectedParkingBoy.getCarInControl();

//        System.out.println(expectedCar.getParkingTicket().getCar());
//        System.out.println(expectedCar.getParkingTicket().getParkingLot());
//        System.out.println(expectedCar.getParkingTicket().getTicketStatus());
//        System.out.println(car.getParkingTicket().getCar());
//        System.out.println(car.getParkingTicket().getParkingLot());
//        System.out.println(car.getParkingTicket().getTicketStatus());
        assertEquals(expectedCar.getParkingTicket(), car.getParkingTicket());
    }

    @Test
    public void invalidFetch() {
        //AC3: Given parkingBoy and invalid ticket, when fetch, then return error message, the ticket will be confirmed as invalid ticket,  and no car can be fetched and in control by parkingBoy
        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar, expectedParkingBoy.getOwnedParkingLot(), "new");
        expectedParkingBoy.fetch(parkingTicket);
        Car nullCar = expectedParkingBoy.getCarInControl();

        assertEquals("invalid", parkingTicket.getTicketStatus());
        assertNull(nullCar);
    }

    @Test
    public void fetchWithUsedTicket() {
        // AC4: Given parkingBoy and used ticket, when fetch, then return error message, and no car can be fetched and in control by parkingBoy

        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar, expectedParkingBoy.getOwnedParkingLot(), "used");
        expectedParkingBoy.fetch(parkingTicket);
        Car nullCar = expectedParkingBoy.getCarInControl();

        assertEquals("used", parkingTicket.getTicketStatus());
        assertNull(nullCar);
    }

    @Test
    public void parkWhenParkingLotFulled() {
        //AC5: Given parkingLot with 0 space, when park, then return error message, no ticket return from parkingBoy's hand
        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy(20, 20);

        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

        assertNull(expectedParkingTicket);
    }

    //##################### Story 1 #####################

    //##################### Story 2 #####################

    @Test
    public void returnCorrectMessgaeWhenFetchWithWrongTicket() {
        //AC1: Given parkingBoy, used ticket, when fetch, then return "Unrecognized parking ticket."
        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar, expectedParkingBoy.getOwnedParkingLot(), "used");
        expectedParkingBoy.fetch(parkingTicket);

        assertEquals("Unrecognized parking ticket.", expectedParkingBoy.getFetchStatus());

    }

    @Test
    public void returnCorrectMessgaeWhenFetchWithNoTicket() {
        //AC2: Given parkingBoy, when fetch, then return "Unrecognized parking ticket."
        setUpTestforStory1();

        ParkingTicket parkingTicket = new ParkingTicket(expectedCar, expectedParkingBoy.getOwnedParkingLot(), "used");
        expectedParkingBoy.fetch();

        assertEquals("Please provide your parking ticket.", expectedParkingBoy.getFetchStatus());

    }

    @Test
    public void returnCorrectMessageWhenParkWithNoPosition() {
        //AC3: Given parkingBoy, car, parkingLot with no position, when park, then return "Not enough position."
        setUpTestforStory1();

        expectedCar = new Car();
        expectedParkingBoy = new ParkingBoy(20, 20);

        expectedParkingBoy.park(expectedCar);

        assertEquals("Not enough position.", expectedParkingBoy.getParkStatus());

    }
    //##################### Story 2 #####################

    //##################### Story 3 #####################

    @Test
    public void parktoSeondParkingLotWhenFirstParkingLotFulled() {
        //AC1: Given parkingBoy, car, parkingLotList with first lot fulled and second lot not fulled, when park, then car should be parked into second lot

        setUpTestforStory3();
        expectedParkingBoy = new ParkingBoy(parkingLotArrayList);

        expectedParkingBoy.park(expectedCar);
        expectedParkingTicket = expectedParkingBoy.getTicketOnHand();

        assertEquals(parkingLot2, expectedParkingTicket.getParkingLot());
    }

    //##################### Story 3 #####################

    //##################### Story 4 #####################

    @Test
    public void smartParkingBoy() {
        //Given smart parkingBoy, car, parkingLot list with 2 parkingLot with different position left. When park, then should park to the parkingLot with more space (parkingLot2)

        parkingLot1 = new ParkingLot(20, parkingTicketList, 15);
        setUpTestforStory3();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotArrayList);

        smartParkingBoy.park(expectedCar);
        expectedParkingTicket = smartParkingBoy.getTicketOnHand();

//        System.out.println(parkingLot2);
//        System.out.println(parkingLot1);
//        System.out.println(expectedParkingTicket.getParkingLot());

        assertEquals(parkingLot2, expectedParkingTicket.getParkingLot());

    }

    //##################### Story 4 #####################

    //##################### Story 5 #####################

    @Test
    public void superSmartParkingBoy(){

        //Given super smart parkingBoy, car, parkingLot list with 2 parkingLot with different position left. When park, then should park to the parkingLot with the highest position rate (parkingLot1)
        parkingLot1 = new ParkingLot(30, parkingTicketList, 15);
        parkingLot2 = new ParkingLot(20, parkingTicketList, 15);
        setUpTestforStory3();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotArrayList);

        superSmartParkingBoy.park(expectedCar);
        expectedParkingTicket = superSmartParkingBoy.getTicketOnHand();

//        System.out.println(parkingLot2);
//        System.out.println(parkingLot1);
//        System.out.println(expectedParkingTicket.getParkingLot());

        assertEquals(parkingLot1, expectedParkingTicket.getParkingLot());

    }

    //##################### Story 5 #####################

    //##################### Story 6 #####################

    @Test
    public void managerAssignJob(){
        // AC1: Given car to be parked, parkingManager, when assignJob, then return ticket
        parkingLot1 = new ParkingLot(30, parkingTicketList, 15);
        parkingLot2 = new ParkingLot(20, parkingTicketList, 10);
        setUpTestforStory3();

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotArrayList);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotArrayList);

        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>();
        parkingBoyArrayList.add(superSmartParkingBoy);
        parkingBoyArrayList.add(smartParkingBoy);

        ParkingManager parkingManager = new ParkingManager(parkingBoyArrayList,parkingLotArrayList);
        parkingManager.assignParkJob(expectedCar);
        expectedParkingTicket = parkingManager.ticketOnHand;

//        System.out.println(parkingLot2);
//        System.out.println(parkingLot1);
//        System.out.println(expectedParkingTicket.getParkingLot());

        assertEquals(parkingLot1,expectedParkingTicket.getParkingLot());
    }

    public void setUpTestForStory6(){
        parkingLot1 = new ParkingLot(30, parkingTicketList, 20);
        parkingLot2 = new ParkingLot(20, parkingTicketList, 10);
        setUpTestforStory3();

        superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotArrayList);
        parkingBoyArrayList.add(superSmartParkingBoy);

        ArrayList<ParkingLot> parkingBoyArrayListForManager = new ArrayList<>();
        parkingBoyArrayListForManager.add(parkingLot1);
    }

    @Test void managerParkCar(){
        // AC1: Given parking Manager, car to be parked, when assignParkJob to manager, manager will change to be parking joy, park the car and return the ticket
        setUpTestForStory6();
        ParkingManager parkingManager = new ParkingManager(parkingBoyArrayList,parkingLotArrayList);
        parkingManager.assignParkJob(expectedCar);

        if (parkingManager.getParkingBoyAssigned() == superSmartParkingBoy){
            assertEquals(parkingLot2,superSmartParkingBoy.getTicketOnHand().getParkingLot());
        }else {
            //Target test: when assigned to parking manager
            assertEquals(parkingLot1,parkingManager.getParkingBoyAssigned().getTicketOnHand().getParkingLot());
        }
    }

    @Test
    public void managerFetchCar(){
        //AC2: Given parking Manager, parking ticket, when assignFetchJob to manager, then manager will change to be parking boy, and fetch the car from parking lot 1
        setUpTestForStory6();

        parkingBoyArrayList.remove(superSmartParkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoyArrayList,parkingLotArrayList);
        parkingManager.assignParkJob(expectedCar);

        parkingManager.assignFetchJob(parkingManager.getTicketOnHand());

        assertEquals(expectedCar,parkingManager.getParkingBoyAssigned().getCarInControl());

    }

    @Test
    public void assignFetchJobFailure(){
        //AC3: Given parking manager, used parking ticket, when assignFetchJob, then manager will return operation failure with the reason passes from parking boy
        setUpTestForStory6();

        ParkingManager parkingManager = new ParkingManager(parkingBoyArrayList,parkingLotArrayList);
        parkingManager.assignParkJob(expectedCar);
        parkingManager.assignFetchJob(parkingManager.getTicketOnHand());

        parkingManager.assignFetchJob(parkingManager.getTicketOnHand());

        assertEquals("This is parking manager. Fetch car failure due to reason: Unrecognized parking ticket.",parkingManager.getErrorMessageFromManager());
    }

    //##################### Story 6 #####################
}