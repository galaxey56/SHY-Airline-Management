package FlightSystem;

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
    public static travelFlight convert(ResultSet rs) throws SQLException{
        rs.next();
        if(!rs.next()){
            System.out.println("Flight not found");
        }
        travelFlight ref = new travelFlight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        return ref;
    }

    /*
    Yash Write a to string method here for displaying a flight details neatly
    */

}