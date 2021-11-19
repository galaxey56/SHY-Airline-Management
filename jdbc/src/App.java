import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JDBC.ConnectionEst;
import JDBC.Helper;

public class App {

    public static void insert(String args[]) throws Exception {
        Connection need = ConnectionEst.establishConnection();
        String stmt = "select * from henlo";
        PreparedStatement pstmt = need.prepareStatement(stmt);
        // pstmt.setString(1, args[1]);
        // pstmt.setInt(2, Integer.parseInt(args[2]));
        // pstmt.setDouble(3, Double.parseDouble(args[3]));
        ResultSet rs = pstmt.executeQuery();
        Helper.pagination(Helper.makeList(rs), 1);
    }

    public static void update(String args[]) throws Exception {
        Connection need = ConnectionEst.establishConnection();
    }

    public static void search(String args[]) throws Exception {
        Connection need = ConnectionEst.establishConnection();
        switch (args[1]) {
        case "-f":
            switch (args[2]) {
            case "id":

                break;

            default:
                break;
            }
            break;

        default:
            break;
        }
    }

    public static void printHelp() {
        System.out.println("Help for commands:");
        System.out.println("-a <filename> for operation A on filename");
        System.out.println("-b <recordType> <string> for operation on <recordType> and search <string>");
        System.out.println("-h (or any) for help menu");
    }

    public static void main(String[] args) throws Exception {
        // doOperationA(args);
        System.out.println(args.length);
        switch (args[0]) {
        case "-a":
            insert(args);
            break;
        case "-b":
            update(args);
            break;
        case "-c":
            search(args);
        default:
            printHelp();
            break;
        }
    }
}
