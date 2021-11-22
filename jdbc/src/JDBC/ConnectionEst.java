package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionEst {
    final private static String USERNAME = "root";
    final private static String PASSWORD = "samyak";   //Give your sql password here
    final private static String CONNECTION_STRING = "jdbc:mysql://localhost:3306/oops";  //jdbc trials is databse name in ur sql command line

    public static Connection establishConnection() throws SQLException{
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties props = new Properties();
            props.put("user", USERNAME);
            props.put("password", PASSWORD);
            conn = DriverManager.getConnection(CONNECTION_STRING, props);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
