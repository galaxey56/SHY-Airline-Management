package SQLQueries;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import JDBC.ConnectionEst;
import JDBC.Helper;
import FlightSystem.travelFlight;


public class flightSQL {

    public static void flightDetailsWithADD(String departure, String arrival, String date) throws Exception {
        Connection need = ConnectionEst.establishConnection();
        String query = "select flight_no,airline,departureCity,arrivalCity,DepartureTime,arrivalTime,price_cal(?,?,?),capacity from flight where departureCity = ? and arrivalCIty = ?";
        PreparedStatement cstmt = need.prepareStatement(query);
        cstmt.setString(1, departure);
        cstmt.setString(2, arrival);
        cstmt.setString(3, date);
        cstmt.setString(4, departure);
        cstmt.setString(5, arrival);
        ResultSet rs = cstmt.executeQuery();
        Helper.pagination(Helper.makeList(rs), 1, flightDetailsWithADDCount(arrival, departure, date));
        System.out.println("Ticket fare may change based on date of booking");
    }
    private static int flightDetailsWithADDCount(String arrival, String departure, String date) throws Exception {
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from flight where departureCity = ? and arrivalCIty = ?";
        PreparedStatement cstmt = need.prepareStatement(query);
        cstmt.setString(1, departure);
        cstmt.setString(2, arrival);
        ResultSet rs = cstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public static void bookTicket(int id, String flightNum, String date) throws SQLException{
        if(passengerSQL.searchWithId(id, 0) == 0){
            System.out.println("Please register as a passgener through passenger queries");
            System.out.println("To know your id please search passenger details with your name or email");
            return;
        }
        int ref = checkAvailability(flightNum, date);
        ref = getFlightCapacity(flightNum) - ref;
        if(ref == 0){
            System.out.println("Sorry the flight is fully occupied!!");
            return;
        }
        int seatnum = generateSeatNum(flightNum, date);
        Connection need = ConnectionEst.establishConnection();
        String ticketNum = id + "-" + (Math.round(Math.random() * 10000));
        String query = "call insertinto_res(?,?,?,?)";                                                 //insert into the reservation table query;
        PreparedStatement insert = need.prepareStatement(query);
        insert.setString(1,flightNum);
        insert.setString(2,ticketNum);
        insert.setInt(3,seatnum);
        insert.setString(4,date);
        insert.executeUpdate();
        passengerSQL.updateTicketNum(ticketNum, id);
        passengerSQL.searchWithId(id, 1);
    }
    private static int generateSeatNum(String flightNum, String date) throws SQLException{
        int ref = checkAvailability(flightNum, date);
        return ref+1;
    }
    public static void displayTickets(String ticketNum) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select p.passenger_id as id ,p.Name,p.age,p.gender,r.* from passenger p , reservation r where p.ticket_no = r.ticket_no and r.ticket_no = ?;" ;                                                  //Need ticket details of this person based on ticketNum
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, ticketNum);
        ResultSet rs = executableQuery.executeQuery();
        /* hmm galaxy u need to do something here dont forget */
    }
    private static int getFlightCapacity(String flightNum) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select capacity from flight where flight_no = ?;"; 
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, flightNum);
        ResultSet rs = update.executeQuery();
        rs.next();
        int ref = rs.getInt(1);
        return ref;
    }
    private static int checkAvailability(String flightNum, String date) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from reservation where flight_no = ? and date_of_travel = ?"; 
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
        String query = "select * from flight where flight_no = ?"; 
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, flightNum);
        ResultSet rs = executableQuery.executeQuery();
        travelFlight ref = travelFlight.convert(rs);
        System.out.println(ref.toString());
    }
    private static int getTotalNumOfFlights() throws Exception{
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from flight";
        PreparedStatement hey = need.prepareStatement(query);
        ResultSet rs = hey.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public static void deleteReservation(String ticket) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "delete from reservation where ticket_no = ?";
    }

}


