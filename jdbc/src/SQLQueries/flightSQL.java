package SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import JDBC.ConnectionEst;
import JDBC.Helper;
import FlightSystem.travelFlight;


public class flightSQL {
    public static void flightDetailsWithADD(String arrival, String departure, String date) throws Exception {
        Connection need = ConnectionEst.establishConnection();
        String query = "call price_cal(?,?,?)"; //check table name here
        PreparedStatement executablStatement = need.prepareStatement(query);
        executablStatement.setString(2, arrival);
        executablStatement.setString(1, departure);
        executablStatement.setString(3, date);
        ResultSet rs = executablStatement.executeQuery();
        while (rs.next()) {
            String ref = "";
            for (int i = 0; i < 8; i++) {
                Object val = rs.getObject(i + 1);
                ref += val + " ";
            }
            System.out.println(ref);
        }
        System.out.println("Ticket fare may change based on date of booking");
    }

    public static void bookTicket(String id, String flightNum, String date) throws SQLException{
        int ref = checkAvailability(flightNum, date);
        ref = getFlightCapacity(flightNum) - ref;
        if(ref == 0){
            System.out.println("Sorry the flight is fully occupied!!");
            return;
        }
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
    public static int getFlightCapacity(String flightNum) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = ""; //Get capacity from flight table query
        PreparedStatement update = need.prepareStatement(query);
        ResultSet rs = update.executeQuery();
        rs.next();
        int ref = rs.getInt(1);
        return ref;
    }
    public static int checkAvailability(String flightNum, String date) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from reservation where flight_no = ? and date = ?"; 
        PreparedStatement runIt = need.prepareStatement(query);
        runIt.setString(1, flightNum);
        runIt.setString(2, date);
        ResultSet rs = runIt.executeQuery();
        rs.next();
        int ref = rs.getInt(1);
        return ref;
    }
    public static void getFlightDetails(String flightNum) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = ""; //Query for flight details from flight table with flight ticket
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, flightNum);
        ResultSet rs = executableQuery.executeQuery();
        travelFlight ref = travelFlight.convert(rs);
        ref.toString();
    }

}

//galaxy lauda