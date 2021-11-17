import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    final static int pageLimit = 10;

    public static List<String> makeList(ResultSet rs) throws Exception {
        List<String> hello = new ArrayList<String>();
        while (rs.next()) {
            hello.add(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getDouble(3));
        }
        return hello;
    }

    public static void pagination(List<String> rs, int page) throws Exception {
        int maxPages = 25 / pageLimit + 1;
        if (page > maxPages) {
            System.out.println("Page limit exceeded");
            return;
        }
        System.out.println("Displaying page " + (page) + "/" + maxPages);
        for (int j = 0; j < pageLimit; j++) {
            System.out.println(rs.get((page - 1) * pageLimit + j));
        }
    }

    public static int getSize(ResultSet rs) throws Exception {
        int size = 0;
        while (rs.next()) {
            size++;
        }
        return size;
    }
}
