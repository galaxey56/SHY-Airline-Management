package SQLQueries;
import JDBC.ConnectionEst;
import PassengersList.Passenger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class passengerSQL {
    public static Passenger createObject(String args[]) throws SQLException{
        if(args.length != 6){
            System.out.println("Please enter all required values in defined order");
        }
        Passenger passenger = new Passenger(args[1], args[2], Integer.parseInt(args[3]), args[4], args[5]);
        passenger.setId(getTottalNumOfPassengers()+1);
        long ticket = (Math.round(Math.random()*10000));
        passenger.setTicketNum(passenger.getId() + "-" + ticket);
        System.out.println(passenger.toString());
        insertIntoSQL(passenger);
        return passenger;
    }
    public static void insertIntoSQL(Passenger p) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "insert into passenger values(?,?,?,?,?,?,?)";
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setInt(1, p.getId());
        executableQuery.setString(2, p.getName());
        executableQuery.setInt(3, p.getAge());
        executableQuery.setString(4, p.getGender());
        executableQuery.setString(5, p.getPhoneNum());
        executableQuery.setString(6, p.getEmail());
        executableQuery.setString(7, p.getTicketNum());
        executableQuery.executeUpdate();
    }
    public static int getTottalNumOfPassengers() throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from passenger";
        PreparedStatement executableQuery = need.prepareStatement(query);
        ResultSet ans = executableQuery.executeQuery();
        return ans.getInt(1);
    }
    public static void searchWithName(String name) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from passenger where name = ?";
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, name);
        ResultSet ans = executableQuery.executeQuery();
        if(!ans.next()){
            System.out.println("No Match Found");
            return;
        }
        Passenger p = Passenger.makePassenger(ans);
        System.out.println(p.toString());
    }
    public static void searchWithId(int id) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from passenger where id = ?";
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setInt(1, id);
        ResultSet ans = executableQuery.executeQuery();
        if(!ans.next()){
            System.out.println("No Match Found");
            return;
        }
        Passenger p = Passenger.makePassenger(ans);
        System.out.println(p.toString());
    }
}
