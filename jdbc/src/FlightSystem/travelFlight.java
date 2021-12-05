package FlightSystem;

import SQLQueries.flightSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class travelFlight extends Flight {

    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String arrivalTime;
    private int price;

    public travelFlight(String flightNumber, String airline, String departureCity, String arrivalCity,
            String departureTime, String arrivalTime, int price, int capacity) {
        super(flightNumber, airline, capacity);
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public static travelFlight convert(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            System.out.println("Flight not found");
        }
        travelFlight ref = new travelFlight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        return ref;
    }

    public static void flightOperations(String[] args) throws Exception {
        switch (args[1]) {
            case "-s":
                flightSQL.flightDetailsWithADD(args[2], args[3], args[4]);
                break;
            case "-d":
                flightSQL.getFlightDetails(args[2]); // Flight details only with flightNum
                break;
            case "-dd":
                flightSQL.getFlightDetails(args[2], args[3]); // Flight details with date and displayin capacity left
                break;
            case "-a":
                flightSQL.displayAllFlights(Integer.parseInt(args[2]));
                break;
            default:
                System.out.println("Wrong Format!! :)");
        }
    }

    @Override
    public String toString() {
        return "****************************************************************************\n" + "  " + airline
                + "\n  "
                + departureCity + " To " + arrivalCity + "\n  " + "Departure Time:- "
                + departureTime + "\n  " + "Arrival Time:- " + arrivalTime + "\n  " + "Ticket Fare:- " + price + "\n  "
                + "Total Occupancy:-" + Capacity
                + "\n  Note:- Ticket prices may differ on dates and for which class you are booking for\n  50% off on booking 30 days prior to the date of travel\n  Economy class:- normal price, Buisness class:- additional 1000 rupee, First Class:- additional 2000 rupee"
                + "\n****************************************************************************";
    }

}
/*
 * System.out.println(
 * "************************************************************************");
 * System.out.println("<<<<<<<<<<<<<<<<<<<<<+"+s1+">>>>>>>>>>>>>>>>>>>>>>>>");
 * System.out.println("Flight No."+s5);
 * System.out.println("Ticket No."+s6);
 * System.out.println("Seat No."+s9);
 * System.out.println(s2+","+s4+","+s3);
 * System.out.println("Economy Class");
 * System.out.println(s14+" to "+s15);
 * System.out.println("Date:-"+s7);
 * System.out.println("Departure Time:-"+s12);
 * System.out.println("Arrival Time:-"+s13);
 * System.out.
 * println("ALL PASSENGERS ARE ADVISED TO REACH AIRPORT 2 HR PRIOR TO DEPARTURE TIME"
 * );
 * System.out.println(
 * "************************************************************************");
 */