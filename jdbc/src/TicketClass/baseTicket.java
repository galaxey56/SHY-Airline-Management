package TicketClass;

abstract public class baseTicket {
    private String flightNum;
    private String dateOfTravel;
    private int seatNum;
    protected int price;

    public baseTicket(String flightNum, String dateOfTravel, int seatNum, int price) {
        this.flightNum = flightNum;
        this.dateOfTravel = dateOfTravel;
        this.seatNum = seatNum;
        this.price = price;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getPrice() {
        return price;
    }

    abstract void setPrice(int price);
}
