package SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import JDBC.ConnectionEst;
import JDBC.Helper;


public class flightSQL {
    public static void flightDetailsWithAD(String arrival, String departure) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from flight where arrivalCity=? and departureCity = ?"; //check table name here
        PreparedStatement executablStatement = need.prepareStatement(query);
        executablStatement.setString(1, arrival);
        executablStatement.setString(2, departure);
        ResultSet rs = executablStatement.executeQuery();
        Helper.printResultSet(rs);
        System.out.println("Ticket fare may change based on date of booking");
    }
    public static void flightDetailsWithADD(String arrival, String departure, String date) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from flight where arrivalCity=? and departureCity = ? and date=?"; //check table name here
        PreparedStatement executablStatement = need.prepareStatement(query);
        executablStatement.setString(1, arrival);
        executablStatement.setString(2, departure);
        executablStatement.setString(3, date);
        ResultSet rs = executablStatement.executeQuery();
        Helper.printResultSet(rs);
        System.out.println("Ticket fare may change based on date of booking");
    }

    public static void bookTicket(String id, String flightNum, String date) throws SQLException{
        int ref = checkAvailability(flightNum);
        if(ref == 0){
            System.out.println("Sorry the flight is fully occupied!!");
            return;
        }
        updateFlightCapacity(--ref);
        Connection need = ConnectionEst.establishConnection();
        String ticketNum = id + "-" + (Math.round(Math.random() * 10000));
        passengerSQL.updateTicketNum(ticketNum, id);
        String query = "";                                                 //insert into the reservation table query;
        PreparedStatement insert = need.prepareStatement(query);
        insert.executeQuery();

    }
    public static void displayTickets(String ticketNum) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "";                                                  //Need ticket details of this person based on ticketNum
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, ticketNum);
        ResultSet rs = executableQuery.executeQuery();
        /* hmm galaxy u need to do something here dont forget */
    }
    public static void updateFlightCapacity(int num) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = ""; //Update the capacity query
        PreparedStatement update = need.prepareStatement(query);
        update.executeUpdate(); 
    }
    public static int checkAvailability(String flightNum) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from reservation where flight_no = ? and date = ?"; 
        PreparedStatement runIt = need.prepareStatement(query);
        runIt.setString(1, flightNum);
        ResultSet rs = runIt.executeQuery();
        rs.next();
        int ref = rs.getInt(1);
        return ref;
    }
    public static void getFlightDetails(String flightNum){
        
    }

}
