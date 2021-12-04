package TicketClass;


public class EconomyClass extends baseTicket {
    final static int maxWeightOfBaggage = 15;
    final static int maxWeightOfHandBaggage = 7;
    public EconomyClass(String flightNum, String dateOfTravel, int seatNum, int price) {
        super(flightNum, dateOfTravel, seatNum, price);
    }

    @Override
    void setPrice(int price) {
        super.price = price;
        
    }
    
}
