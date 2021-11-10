import java.sql.Connection;

public class App {

    public static void doOperationA(String args[]) {
        Connection need = ConnectionEst.establishConnection();
        System.out.println("Doing operation A with " + args[1] + " argument");
    }

    public static void doOperationB(String args[]) {
        System.out.println("Doing operation B with " + args[1] + " " + args[2] + " argument");
    }

    public static void printHelp() {
        System.out.println("Help for commands:");
        System.out.println("-a <filename> for operation A on filename");
        System.out.println("-b <recordType> <string> for operation on <recordType> and search <string>");
        System.out.println("-h (or any) for help menu");
    }

    public static void main(String[] args) {
        switch (args[0]) {
        case "-a":
            doOperationA(args);
            break;
        case "-b":
            doOperationB(args);
            break;
        default:
            printHelp();
            break;
        }
    }
}
