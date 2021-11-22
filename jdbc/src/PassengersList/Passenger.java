package PassengersList;

import SQLQueries.passengerSQL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String phoneNum;
    private String email;
    private String ticketNum = "No tickets booked yet";
    public static int totalPassengers;
    

    public static int getTotalPassengers() throws SQLException {
        return passengerSQL.getMaxIDPassengers();
    }

    public static void setTotalPassengers(int totalPassengers) {
        Passenger.totalPassengers = totalPassengers;
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
                + this.name + "\n" + "Age: " + this.age + "\n" + "Gender: " + this.gender + "\n" + "PhoneNum: "
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

    public Passenger(String name, String gender, int age, String email, String phoneNum) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Passenger(String x) throws SQLException {
        String values[] = x.split(",");
        this.name = values[0];
        this.gender = values[1];
        this.age = Integer.parseInt(values[2]);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Passenger makePassenger(ResultSet rs) throws SQLException {
        Passenger p = new Passenger(rs.getString(2), rs.getString(4), rs.getInt(3), rs.getString(6), rs.getString(5));
        p.setId(rs.getInt(1));
        p.setTicketNum(rs.getString(7));
        return p;
    }

    public static void passengerOperations(String[] args) throws SQLException {
        switch (args[1]) {
        case "-c":
            passengerSQL.createObject(args);
            break;
        }
    }

}
