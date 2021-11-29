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
    }

    public static void printFlightCommands() {

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
