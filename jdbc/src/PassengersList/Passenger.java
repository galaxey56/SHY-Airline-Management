package PassengersList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String phoneNum;
    private String email;
    private String ticketNum;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Passenger details: \n" +
        "ID: " + this.id + "\n" + 
        "Name: " + this.name + "\n" +
        "Age: " + this.age + "\n" +
        "Gender: " + this.gender + "\n" + 
        "PhoneNum: " + this.phoneNum + "\n" + 
        "Email: " + this.email + "\n" + 
        "ticketNum: " + this.ticketNum;
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
    public static Passenger makePassenger(ResultSet rs) throws SQLException{
        Passenger p = new Passenger(rs.getString(2), rs.getString(4), rs.getInt(3), rs.getString(6), rs.getString(5));
        p.setId(rs.getInt(1));
        p.setTicketNum(rs.getString(7));
        return p;
    }
    
}
