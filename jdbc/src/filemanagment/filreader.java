package filemanagment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import PassengersList.Passenger;
import SQLQueries.passengerSQL;

public class filreader {

    public static void readfile (String filename) throws Exception {
        List<Passenger> passengers = new ArrayList<Passenger>();
        try {
            // BufferedReader reader= new BufferedReader(new FileReader("student.csv"));
            // String studstring;

            // while ((studstring=reader.readLine())!=null) {
            // Student student = new Student(studstring);
            // studs.add(student);
            // }
            // reader.close();

            Passenger.setTotalPassengers(passengerSQL.getTottalNumOfPassengers());
            Path path = Paths.get(filename);
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    Passenger pass = new Passenger(line);
                    passengers.add(pass);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(passengers);
        insertFromCSV(passengers);
    }

    public static void insertFromCSV(List<Passenger> passenger) throws SQLException {
        for (Passenger x : passenger) {
            passengerSQL.insertIntoSQL(x);
        }
    }
}