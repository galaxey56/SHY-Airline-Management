package PassengersList;



import SQLQueries.passengerSQL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger {
    private int id;
    private String name;
    private String DOB;
    private String gender;
    private String phoneNum;
    private String email;
    private String ticketNum = "Not booked";
    private static int totalPassengers;
    

    public static int getTotalPassengers() throws SQLException {
        return passengerSQL.getTottalNumOfPassengers();
    }

    public static void setTotalPassengers() throws SQLException {
        Passenger.totalPassengers = passengerSQL.getMaxIDPassengers();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Passenger details: \n------------------------------------------\n" + "ID: " + this.id + "\n" + "Name: "
                + this.name + "\n" + "Date Of Birth: " + this.DOB + "\n" + "Gender: " + this.gender + "\n" + "PhoneNum: "
                + this.phoneNum + "\n" + "Email: " + this.email + "\n" + "ticketNum: " + this.ticketNum
                + "\n------------------------------------------";
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public Passenger(String name, String gender, String DOB, String email, String phoneNum) {
        this.name = name;
        this.gender = gender;
        this.DOB = DOB;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Passenger(String x) throws SQLException {
        String values[] = x.split(",");
        this.name = values[0];
        this.gender = values[1];
        this.DOB = values[2];
        this.email = values[3];
        this.phoneNum = values[4];
        this.id = ++totalPassengers;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String dOB) {
        DOB = dOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Passenger makePassenger(ResultSet rs) throws SQLException {
        Passenger p = new Passenger(rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(6), rs.getString(5));
        p.setId(rs.getInt(1));
        p.setTicketNum(rs.getString(7));
        return p;
    }

    public static void passengerOperations(String[] args) throws Exception {
        switch (args[1]) {
        case "-dp":
            passengerSQL.displayAllPassengers(args);;
            break;
        case "-ap":
            passengerSQL.createObject(args);
            break;
        case "-np":
            passengerSQL.searchWithName(args[2]);
            break;
        case "-idp":
            passengerSQL.searchWithId(Integer.parseInt(args[2]), 1);
            break;
        case "-ep":
            passengerSQL.searchWithEmail(args[2]);
            break;
        case "-pp":
            passengerSQL.searchWithMobileNum(args[2]);
            break;
        case "-tnp":
            passengerSQL.searchWithTicket(args[2]);
            break;
        case "-u":
            passengerSQL.update(args);
            break;
        case "-d":
            passengerSQL.deletePassenger(Integer.parseInt(args[2]));
            break;
        default:
            System.out.println("Wrong Format!! :)");
        }
    }

}