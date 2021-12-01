import java.sql.Connection;
import java.sql.PreparedStatement;

import FlightSystem.travelFlight;
import JDBC.ConnectionEst;
import PassengersList.Passenger;
import FlightSystem.ReservationTable;
import SQLQueries.flightSQL;
import filemanagment.filreader;

public class App {

    public static void insert() throws Exception {
        Connection need = ConnectionEst.establishConnection();
        String stmt = "select * from henlo";
        PreparedStatement pstmt = need.prepareStatement(stmt);
        pstmt.setString(1, "samyak");
        pstmt.setInt(2, 19);
        pstmt.setDouble(3, 9.8);
        // ResultSet rs = pstmt.executeQuery();
        // Helper.pagination(Helper.makeList(rs), 1);
    }


    public static void printHelp() {
        System.out.println("Help for commands:");
        System.out.println("-hp <filename> for passenger commands");
        System.out.println("-hf commands for flights information");
        System.out.println("-hb commands for booking a flight");
        System.out.println("-h default help menu");
    }

    public static void printPassengerCommands() {
        System.out.println("Passenger Commands Syntax");
        System.out.println("----------------------------------------");
        System.out.println("Adding a passenger: \n-p -ap name gender(M/F/N) age email phoneNum");       //Yash examine every switch case i wrote and fill this function :)
        System.out.println("Display all passengers: \n-p -dp ");
        System.out.println("Search a passenger with name: \n-p -np name");
        System.out.println("Search a passenger with ID: \n-p -idp ID");
        System.out.println("Search a passenger with Email: \n-p -ep email");
        System.out.println("Search a passenger with Phone Number: \n-p -pp phone_no.");
        System.out.println("Search a passenger with Ticket Number: \n-p -tnp Ticket_No.");
        System.out.println("Update passenger's name : \n-p -u -n Passenger's ID");
        System.out.println("Update passenger's gender : \n-p -u -g Passenger's ID");
        System.out.println("Update passenger's email : \n-p -u -e Passenger's ID");
        System.out.println("Update passenger's phone_num : \n-p -u -p Passenger's ID");
        System.out.println("Update passenger's age : \n-p -u -a Passenger's ID");
        System.out.println("Delete a passenger : \n-p -d Passenger's ID");

    }

    public static void printFlightCommands() {
        System.out.println("Flights Commands Syntax");
        System.out.println("----------------------------------------");
        System.out.println("Search a Flight with Flight ID and Date of travel: \n-f -d Flight_ID Date_of_Travel(YYYY-MM-DD");       
        System.out.println("Search a Flight with Departure City, Arrival City, Date of travel, Depa: \n-f -s Departure_City Arrival_City Date_of_Travel(YYYY-MM-DD");       
        System.out.println("Display all flight details: \n-f -a");

    }

    public static void main(String[] args) throws Exception {
        // filreader.readfile("src/filemanagment/PassengerDetails.csv");
        // System.out.println(args.length);
        if (args.length == 1) {
            switch (args[0]) {
            case "-h":
                printHelp();
                break;
            case "-hp":
                printPassengerCommands();

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
    }
}
