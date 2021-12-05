package SQLQueries;

import TicketClass.*;

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

    public static void bookClassTicket(int id, String flightNum, String date, String ticketClass) throws SQLException {
        if (passengerSQL.searchWithId(id, 0) == 0) {
            System.out.println("Please register as a passgener through passenger queries");
            System.out.println("To know your id please search passenger details with your name or email");
            return;
        }
        int ref = checkTotal(flightNum, date);
        ref = getFlightCapacity(flightNum) - ref;
        if (ref == 0) {
            System.out.println("Sorry the flight is fully occupied!!");
            return;
        }
        int seatnum = generateSeatNum(flightNum, date, ticketClass);
        String ticketNum = ticketClass + id + "-" + (Math.round(Math.random() * 10000));
        if (ticketClass.equals("E")) {
            EconomyClass booking = new EconomyClass(flightNum, date, seatnum, getFlightFare(flightNum));
            bookTicket(booking, ticketNum, ticketClass);
        } else if (ticketClass.equals("B")) {
            BusinessClass booking = new BusinessClass(flightNum, date, seatnum, getFlightFare(flightNum));
            bookTicket(booking, ticketNum, ticketClass);
        } else if (ticketClass.equals("F")) {
            FirstClass booking = new FirstClass(flightNum, date, seatnum, getFlightFare(flightNum));
            bookTicket(booking, ticketNum, ticketClass);
        } else {
            System.out.println("No such ticket class exists!!");
        }
        passengerSQL.updateTicketNum(ticketNum, id);
        passengerSQL.searchWithId(id, 1);
    }

    public static void bookTicket(EconomyClass booking, String ticketNum, String ticketClass) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "call insertinto_res(?,?,?,?,?)"; // insert into the reservation table query;
        PreparedStatement insert = need.prepareStatement(query);
        insert.setString(1, booking.getFlightNum());
        insert.setString(2, ticketNum);
        insert.setInt(3, booking.getSeatNum());
        insert.setString(4, booking.getDateOfTravel());
        insert.setString(5, ticketClass);
        insert.executeUpdate();
    }

    public static void bookTicket(BusinessClass booking, String ticketNum, String ticketClass) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "call insertinto_res(?,?,?,?,?)"; // insert into the reservation table query;
        PreparedStatement insert = need.prepareStatement(query);
        insert.setString(1, booking.getFlightNum());
        insert.setString(2, ticketNum);
        insert.setInt(3, booking.getSeatNum());
        insert.setString(4, booking.getDateOfTravel());
        insert.setString(5, ticketClass);
        insert.executeUpdate();
    }

    public static void bookTicket(FirstClass booking, String ticketNum, String ticketClass) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "call insertinto_res(?,?,?,?,?)"; // insert into the reservation table query;
        PreparedStatement insert = need.prepareStatement(query);
        insert.setString(1, booking.getFlightNum());
        insert.setString(2, ticketNum);
        insert.setInt(3, booking.getSeatNum());
        insert.setString(4, booking.getDateOfTravel());
        insert.setString(5, ticketClass);
        insert.executeUpdate();
    }

    private static int generateSeatNum(String flightNum, String date, String ticketClass) throws SQLException {
        int ref = checkTotal(flightNum, date, ticketClass);
        return ref + 1;
    }

    public static void displayTickets(String ticketNum) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select f.airline,p.Name,p.dob,p.gender,r.*,f.departureTime , arrivalTime,departurecity , arrivalcity from passenger p , reservation r, flight f where p.ticket_no = r.ticket_no and f.flight_no = r.flight_no and r.ticket_no = ?;";
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, ticketNum);
        ResultSet rs = executableQuery.executeQuery();
        rs.next();
        String s1=rs.getString(1);
        String s2=rs.getString(2);
        String s3=rs.getString(3);
        String s4=rs.getString(4);
        String s5=rs.getString(5);
        String s6=rs.getString(6);
        String s7=rs.getString(7);
        int s9=rs.getInt(9);
        String s11=rs.getString(11);
        String s12=rs.getString(12);
        String s13=rs.getString(13);
        String s14=rs.getString(14);
        String s15=rs.getString(15);
        if (s11.equals("E")) {
            s11="Economy Class";
        } else if (s11.equals("B")) {
            s11="Business Class";
        } else if (s11.equals("F")) {
            s11="First Class";
            
        }
        /* hmm galaxy u need to do something here dont forget */
        System.out.println("************************************************************************");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<+"+s1+">>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Flight No."+s5);
        System.out.println("Ticket No."+s6);
        System.out.println("Seat No."+s9);
        System.out.println(s2+","+s4+","+s3);
        System.out.println("Economy Class");
        System.out.println(s14+" to "+s15);
        System.out.println("Date:-"+s7);
        System.out.println("Departure Time:-"+s12);
        System.out.println("Arrival Time:-"+s13);
        System.out.println("ALL PASSENGERS ARE ADVISED TO REACH AIRPORT 2 HR PRIOR TO DEPARTURE TIME");
        System.out.println("************************************************************************");

    }

    private static int getFlightCapacity(String flightNum) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select capacity from flight where flight_no = ?;";
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, flightNum);
        ResultSet rs = update.executeQuery();
        rs.next();
        int ref = rs.getInt(1);
        return ref;
    }

    private static int checkTotal(String flightNum, String date) throws SQLException {
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
    private static int checkTotal(String flightNum, String date, String ticketClass) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from reservation where flight_no = ? and date_of_travel = ? and ticketClass = ?";
        PreparedStatement runIt = need.prepareStatement(query);
        runIt.setString(1, flightNum);
        runIt.setString(2, date);
        runIt.setString(3, ticketClass);
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

    public static void getFlightDetails(String flightNum, String date) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from flight where flight_no = ?";
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, flightNum);
        ResultSet rs = executableQuery.executeQuery();
        travelFlight ref = travelFlight.convert(rs);
        System.out.println(ref.toString());
        System.out.println("Num of seats left = " + (getFlightCapacity(flightNum) - checkTotal(flightNum, date)));
    }

    public static int getFlightFare(String flightNum) throws SQLException {
        int ref;
        Connection need = ConnectionEst.establishConnection();
        String query = "select price from flight where flight_no = ?";
        PreparedStatement execute = need.prepareStatement(query);
        execute.setString(1, flightNum);
        ResultSet rs = execute.executeQuery();
        rs.next();
        ref = rs.getInt(1);
        return ref;
    }

    public static void displayAllPassengersOfFlight(String flightNum, String date) throws Exception {
        Connection need = ConnectionEst.establishConnection();
        String query = "select R.Ticket_no , Seat_number , Name , Gender, TicketClass from reservation R,Passenger P where R.ticket_no = P.ticket_no and R.flight_no = ? and R.date_of_travel = ?";
        PreparedStatement stmt = need.prepareStatement(query);
        stmt.setString(1, flightNum);
        stmt.setString(2, date);
        ResultSet rs = stmt.executeQuery();
        int ans = Helper.getCount(rs);
        ResultSet re = stmt.executeQuery();
        Helper.pagination(Helper.makeList(re), 1, ans);
    }

    public static void displayAllFlights(int page) throws Exception{
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from flight";
        PreparedStatement stmt = need.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int ans = Helper.getCount(rs);
        ResultSet rs1 = stmt.executeQuery();
        Helper.pagination(Helper.makeList(rs), page, ans);


    }

    public static void deleteReservation(String ticket) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "delete from reservation where ticket_no = ?";
        PreparedStatement stmt = need.prepareStatement(query);
        stmt.setString(1, ticket);
        stmt.executeUpdate();
    }

}
