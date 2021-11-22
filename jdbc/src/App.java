import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JDBC.ConnectionEst;
import JDBC.Helper;
import PassengersList.Passenger;
import filemanagment.filreader;
import SQLQueries.flightSQL;
import SQLQueries.passengerSQL;

public class App {

    public static void insert() throws Exception {
        Connection need = ConnectionEst.establishConnection();
        String stmt = "select * from henlo";
        PreparedStatement pstmt = need.prepareStatement(stmt);
        pstmt.setString(1, "samyak");
        pstmt.setInt(2, 19);
        pstmt.setDouble(3, 9.8);
        ResultSet rs = pstmt.executeQuery();
<<<<<<< HEAD
        // Helper.pagination(Helper.makeList(rs), 1);
=======
        // Helper.pagination(Helper.makeList(rs), 1, 0);
>>>>>>> f16a544a3228931f1036fdac42275a1f9b988404
    }

    public static void update(String args[]) throws Exception {
        Connection need = ConnectionEst.establishConnection();
    }

    public static void search(String args[]) throws Exception {
        Connection need = ConnectionEst.establishConnection();
        switch (args[1]) {
        case "-f":
            switch (args[2]) {
            case "id":

                break;

            default:
                break;
            }
            break;

        default:
            break;
        }
    }

    public static void printHelp() {
        System.out.println("Help for commands:");
        System.out.println("-a <filename> for operation A on filename");
        System.out.println("-b <recordType> <string> for operation on <recordType> and search <string>");
        System.out.println("-h (or any) for help menu");
    }

    public static void main(String[] args) throws Exception {

        /*
        Call the below function only once to insert data from csv to mySQL in ur system
        */
<<<<<<< HEAD
        // filreader.readfile(); 
        // flightSQL.flightDetailsWithADD("Hyderabad","Chennai","2021-12-27");
        insert();
        // passengerSQL.searchWithId(26);
        // // System.out.println(args.length);
=======
        // filreader.readfile("src/filemanagment/PassengerDetails.csv"); 
        passengerSQL.searchWithId(27);
        // System.out.println(args.length);
>>>>>>> f16a544a3228931f1036fdac42275a1f9b988404
        // switch (args[0]) {
        // case "-p":
        //     Passenger.passengerOperations(args);
        //     break;
        // case "-f":
        //     update(args);
        //     break;
        // case "-s":
        //     search(args);
        // default:
        //     printHelp();
        //     break;
        // }
<<<<<<< HEAD
=======
        passengerSQL.displayAllPassengers();
>>>>>>> f16a544a3228931f1036fdac42275a1f9b988404
    }
}
