package FlightSystem;
import SQLQueries.flightSQL;
public class ReservationTable {
    public static void reservationOperation(String[] args) throws Exception{
        switch(args[1]){
            case "-b":
                flightSQL.bookClassTicket(Integer.parseInt(args[2]), args[3], args[4], args[5]);
                break;
            case "-t":
                flightSQL.displayTickets(args[2]);
                break;
            case "-d":
                flightSQL.deleteReservation(args[2]);
                break;
            case "-fl":
                flightSQL.displayAllPassengersOfFlight(args[2], args[3], Integer.parseInt(args[4]));
                break;
            default:
                System.out.println("Wrong Format!! :)");

        }
    }
}
