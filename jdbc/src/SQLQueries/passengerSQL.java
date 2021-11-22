package SQLQueries;

import JDBC.ConnectionEst;
import PassengersList.Passenger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class passengerSQL {
    public static Passenger createObject(String args[]) throws SQLException {
        if (args.length != 7) {
            System.out.println("Please enter all required values in defined order");
        }
        Passenger passenger = new Passenger(args[2], args[3], Integer.parseInt(args[4]), args[5], args[6]);
        passenger.setId(getTottalNumOfPassengers() + 1);
        
        System.out.println(passenger.toString());
        insertIntoSQL(passenger);
        return passenger;
    }

    public static void insertIntoSQL(Passenger p) throws SQLException {
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

    public static int getTottalNumOfPassengers() throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select count(*) from passenger";
        PreparedStatement executableQuery = need.prepareStatement(query);
        ResultSet ans = executableQuery.executeQuery();
        ans.next();
        return ans.getInt(1);
    }

    public static void searchWithName(String name) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from passenger where name = ?";
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setString(1, name);
        ResultSet ans = executableQuery.executeQuery();
        if (!ans.next()) {
            System.out.println("No Match Found");
            return;
        }
        Passenger p = Passenger.makePassenger(ans);
        System.out.println(p.toString());
    }

    //Sample for update
    public static void searchWithId(int id) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "select * from passenger where passenger_id = ?";
        PreparedStatement executableQuery = need.prepareStatement(query);
        executableQuery.setInt(1, id);
        ResultSet ans = executableQuery.executeQuery();
        if (!ans.next()) {
            System.out.println("No Match Found");
            return;
        }
        Passenger p = Passenger.makePassenger(ans);
        System.out.println(p.toString());
    }
    public static void updateTicketNum(String ticket, String id) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = ""; //Ticket update in passenger table using his id query 
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, ticket);
        update.setString(2, id);
        update.executeUpdate();
    }
    public static void update(String args[]) throws SQLException {
        int id = Integer.parseInt(args[3]);
        switch (args[2]) {
        case "-n":
            updateName(id, args[4]);
            break;
        case "-a":
            updateAge(id, Integer.parseInt(args[4]));
            break;
        case "-g":
            updateGender(id, args[4]);
            break;
        case "-p":
            updateMobileNum(id, args[4]);
            break;
        case "-e":
            updateEmail(id, args[4]);
        }
    }
    private static void updateName(int id, String name) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "update passenger set name= ? where id= ?";               //Update name with id query in passenger table
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, name);
        update.setInt(2, id);
        update.executeUpdate();
    }
    private static void updateAge(int id, int age) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "update passenger set age= ? where id= ?";               //Update name with id query in passenger table
        PreparedStatement update = need.prepareStatement(query);
        update.setInt(1, age);
        update.setInt(2, id);
        update.executeUpdate();

    }
    private static void updateGender(int id, String gender) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "update passenger set gender= ? where id= ?";               //Update name with id query in passenger table
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, gender);
        update.setInt(2, id);
        update.executeUpdate();

    }
    private static void updateMobileNum(int id, String num) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "update passenger set gender= ? where id= ?";               //Update name with id query in passenger table
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, num);
        update.setInt(2, id);
        update.executeUpdate();

    }
    private static void updateEmail(int id, String email) throws SQLException{
        Connection need = ConnectionEst.establishConnection();
        String query = "update passenger set gender= ? where id= ?";               //Update name with id query in passenger table
        PreparedStatement update = need.prepareStatement(query);
        update.setString(1, email);
        update.setInt(2, id);
        update.executeUpdate();
        
    }
    public static void deletePassenger(int id) throws SQLException {
        Connection need = ConnectionEst.establishConnection();
        String query = "";                                      //Delete Query with id in passenger table
        PreparedStatement delete = need.prepareStatement(query);
        delete.setInt(1, id);
        int ans = delete.executeUpdate();
        if(ans == 1 ){
            System.out.println("Successfully deleted user Details with id " + id  + " from databse");
        }
    }
}
