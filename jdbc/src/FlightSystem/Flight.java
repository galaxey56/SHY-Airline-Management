package FlightSystem;

abstract public class Flight {
    protected String flightNumber;
    protected String airline;
    protected int Capacity = 60;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public Flight(String flightNumber, String airline, int capacity) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        Capacity = capacity;
    }
}

class travelFlight extends Flight {
    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String arrivalTime;
    private int price;

    public travelFlight(String flightNumber, String airline, int capacity, String departureCity, String arrivalCity,
            String departureTime, String arrivalTime, int price) {
        super(flightNumber, airline, capacity);
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

}
