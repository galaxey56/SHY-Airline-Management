
import FlightSystem.travelFlight;
import PassengersList.Passenger;
import SQLQueries.passengerSQL;
import filemanagment.filreader;
import FlightSystem.ReservationTable;
import SQLQueries.passengerSQL;

public class App {

    public static void printHelp() {
        System.out.println("Help for commands:");
        System.out.println("-------------------------------------------------------");

        System.out.println("-hp <filename> for passenger commands");
        System.out.println("-------------------------------------------------------");

        System.out.println("-hf commands for flights information");
        System.out.println("-------------------------------------------------------");

        System.out.println("-hb commands for booking a flight");
        System.out.println("-------------------------------------------------------");

        System.out.println("-h default help menu");
        System.out.println("-------------------------------------------------------");

    }

    public static void printPassengerCommands() {
        System.out.println("Passenger Commands Syntax");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Adding a passenger: \n-p -ap name gender(M/F/N) dob email phoneNum");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Display all passengers: \n-p -dp Page_num(pagination)");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Search a passenger with name: \n-p -np name");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Search a passenger with ID: \n-p -idp ID");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Search a passenger with Email: \n-p -ep email");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Search a passenger with Phone Number: \n-p -pp phone_no.");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Search a passenger with Ticket Number: \n-p -tnp Ticket_No.");  
        System.out.println("------------------------------------------------------------------");
        System.out.println("Update passenger's name : \n-p -u -n Passenger's ID");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Update passenger's gender : \n-p -u -g Passenger's ID");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Update passenger's email : \n-p -u -e Passenger's ID");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Update passenger's phone_num : \n-p -u -p Passenger's ID");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Update passenger's age : \n-p -u -a Passenger's ID");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Delete a passenger : \n-p -d Passenger's ID");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Search passengers with age greater than certain number: \n -p -grt age pageNum");
        System.out.println("------------------------------------------------------------------");


    }

    public static void printFlightCommands() {
        System.out.println("Flights Commands Syntax");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println(
                "Search a Flight with Flight ID and Date of travel: \n-f -d Flight_ID Date_of_Travel(YYYY-MM-DD)");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println(
                "Search a Flight with Departure City, Arrival City, Date of travel: \n-f -s Departure_City Arrival_City Date_of_Travel(YYYY-MM-DD)");
                System.out.println("-----------------------------------------------------------------------------------------------------");
        
                System.out.println("Display all flight details: \n-f -a Page_num(pagination)");
        System.out.println("-------------------------------------------------------------------------------------------------");

    }
    public static void printReservationCommands(){
        System.out.println("Reservation Commands Syntax");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Book a flight: \n-s -b Passenger's_ID Flight_ID Date_of_Travel(YYYY-MM-DD) Class(E,B,F)(E-Economy,B-Buisness,F-FirstClass)");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Display a Ticket: \n-s -t Tikcet_Number");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Delete a Ticket: \n-s -d Ticket_Number");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Display all passenger of a flight: \n-s -fl Flight_Number Date_Of_Travel(YYYY-MM-DD) Page_num(pagination)");
        System.out.println("-------------------------------------------------------------------------------------------------");

    }
    public static void main(String[] args) throws Exception {
        if(passengerSQL.getTottalNumOfPassengers() == 0)
        filreader.readfile("src/filemanagment/PassengerDetails.csv");
        if (args.length == 1) {
            switch (args[0]) {
                case "-h":
                    printHelp();
                    break;
                case "-hp":
                    printPassengerCommands();
                    break;
                case "-hf":
                    printFlightCommands();
                    break;
                case "-hr":
                    printReservationCommands();
                    break;
                default:
                    System.out.println("Wrong format!! :)");
            }
        }
        switch (args[0]) {
            case "-p":
                Passenger.passengerOperations(args);
                break;
            case "-f":
                travelFlight.flightOperations(args);
                break;
            case "-s":
                ReservationTable.reservationOperation(args);
                break;
            default:
                printHelp();
                break;
        }
        passengerSQL.cleanDB_reservation();
    }

}
