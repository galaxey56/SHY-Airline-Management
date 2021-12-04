package TicketClass;


public class BusinessClass extends baseTicket{

    final static int maxWeightOfBaggage = 25;
    final static int maxWeightOfHandBaggage = 8; 
    public BusinessClass(String flightNum, String dateOfTravel, int seatNum, int price) {
        super(flightNum, dateOfTravel, seatNum, price);
    }

    @Override
    void setPrice(int price) {
        super.price = price + 1000;
    }
    
}
