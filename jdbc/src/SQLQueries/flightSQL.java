package SQLQueries;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import JDBC.ConnectionEst;
import FlightSystem.travelFlight;


public class flightSQL {

    public static void flightDetailsWithADD(String arrival, String departure, String date) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select flight_no,airline,departureCity,arrivalCity,DepartureTime,arrivalTime,price_cal(?,?,?),capacity from flight where departureCity = ? and arrivalCIty = ?";
        CallableStatement cstmt = need.prepareCall(query);
        cstmt.setString(1, departure);
        cstmt.setString(2, arrival);
        cstmt.setString(3, date);
        cstmt.setString(4, departure);
        cstmt.setString(5, arrival);
        cstmt.executeUpdate();
        System.out.println(cstmt.getInt(1));
        System.out.println("Ticket fare may change based on date of booking");
    }

    public static void bookTicket(String id, String flightNum, String date) throws SQLException{
        int ref = checkAvailability(flightNum, date);
        ref = getFlightCapacity(flightNum) - ref;
        if(ref == 0){
            System.out.println("Sorry the flight is fully occupied!!");
            return;
        }
        int seatnum = generateSeatNum(flightNum, date);
        Connection need = ConnectionEst.establishConnection();
        String ticketNum = id + "-" + (Math.round(Math.random() * 10000));
        String query = "insertinto_res(?,?,?,?)";                                                 //insert into the reservation table query;
        PreparedStatement insert = need.prepareStatement(query);
        insert.setString(1,flightNum);
        insert.setString(2,ticketNum);
        insert.setInt(3,seatnum);
        insert.setString(4,date);
        insert.executeQuery();
        passengerSQL.updateTicketNum(ticketNum, id);

    }
    private static int generateSeatNum(String flightNum, String date) throws SQLException{
        int ref = checkAvailability(flightNum, date);
        return ref+1;
    }
    public static void displayTickets(String ticketNum) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select p.passenger_id as id ,p.Name,p.age,p.gender,r.* from passenger p , reservation r where p.ticket_no = r.ticket_no and r.ticket_no = ?;" ;                                                  //Need ticket details of this person based on ticketNum
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, ticketNum);
        ResultSet rs = executableQuery.executeQuery();
        /* hmm galaxy u need to do something here dont forget */
    }
    public static int getFlightCapacity(String flightNum) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select capacity from flight where flight_no = ?;"; 
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, flightNum);
        ResultSet rs = update.executeQuery();
        rs.next();
        int ref = rs.getInt(1);
        return ref;
    }
    public static int checkAvailability(String flightNum, String date) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from reservation where flight_no = ? and date = ?"; 
        PreparedStatement runIt = need.prepareStatement(query);
        runIt.setString(1, flightNum);
        runIt.setString(2, date);
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
        ref.toString();
    }

}

//galaxy lauda
/*
create this function in mysql 

delimiter //
create function price_cal(departure varchar(40),arrival varchar(40) ,d date)
returns int
deterministic
begin
declare fprice int;
if((select DATEDIFF(d,CURDATE())) > 30) then
select 0.5*price into fprice from flight where arrivalCity = arrival and departureCity = departure;
else
select price into fprice from flight where arrivalCity = arrival and departureCity = departure;
end if;
return (fprice);
end //
delimiter ;
*/

/*
delimiter //
create function priceby_id(flightnum varchar(20),da date)
returns int
deterministic
begin
declare pprice int;
if((select DATEDIFF(da,CURDATE())) > 30) then
select 0.5*price into pprice from flight where flight_no = flightnum;
else
select price into pprice from flight where flight_no = flightnum;
end if;
return (pprice);
end //
delimiter ; 
*/ 

/*
delimiter //
create procedure insertinto_res(in flightnum varchar(20),in ticketnum varchar(10),in seatnum int, in d date )
begin 
declare fprice int;
declare aajkitareek date;
select curdate() into aajkitareek;
select priceby_id(flightnum , d) into fprice from flight where flight_no = flightnum;
insert into reservation values(flightnum,ticketnum,d,aajkitareek,seatnum,fprice);
end //
delimiter ;
*/ 