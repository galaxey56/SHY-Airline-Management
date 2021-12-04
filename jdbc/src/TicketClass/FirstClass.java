package TicketClass;


public class FirstClass extends baseTicket {
    final static int maxWeightOfBaggage = 30;
    final static int maxWeightOfHandBaggage = 10;

    public FirstClass(String flightNum, String dateOfTravel, int seatNum, int price) {
        super(flightNum, dateOfTravel, seatNum, price);
    }

    @Override
    void setPrice(int price) {
        this.price = price + 2000;
    }
    
}
