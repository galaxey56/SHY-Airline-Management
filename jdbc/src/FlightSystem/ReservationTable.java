package FlightSystem;
import SQLQueries.flightSQL;
public class ReservationTable {
    public static void reservationOperation(String[] args) throws Exception{
        switch(args[1]){
            case "-b":
                flightSQL.bookTicket(Integer.parseInt(args[2]), args[3], args[4]);
                break;
            case "-t":
                flightSQL.displayTickets(args[2]);
                break;
            default:
                System.out.println("Wrong Format!! :)");

        }
    }
}
