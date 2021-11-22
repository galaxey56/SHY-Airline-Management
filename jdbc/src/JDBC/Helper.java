package JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    final static int pageLimit = 10;
    static String header;
    

    public String getHeader() {
        return header;
    }

    public static void setHeader(ResultSet rs) throws SQLException {
        Helper.header = getHeader(rs);
    }

    private static String getHeader(ResultSet rs) throws SQLException{
        int count = rs.getMetaData().getColumnCount();
        String ref = "";
        String colName[] = new String[count];
        for (int i = 0; i < count; i++) {
            colName[i] = (String) rs.getMetaData().getColumnLabel(i + 1);
            ref += colName[i] + " ";
        }
        return ref;
    }
    
    public static List<String> makeList(ResultSet rs) throws Exception {
        
        int count = rs.getMetaData().getColumnCount();
        List<String> hello = new ArrayList<String>();
        
        while (rs.next()) {
            String ref = "";
            for (int i = 0; i < count; i++) {
                Object val = rs.getObject(i + 1);
                ref += val + " ";
            }
            hello.add(ref);
        }
        setHeader(rs);
        return hello;
    }

    public static void pagination(List<String> rs, int page, int totalPages) throws Exception {
        int maxPages = totalPages / pageLimit + 1;
        if (page > maxPages) {
            System.out.println("Page limit exceeded");
            return;
        }
        System.out.println("Displaying page " + (page) + "/" + maxPages + "\n");
        System.out.println(header);
        System.out.println("----------------------------------------------------------------------");
        for (int j = 0; j < pageLimit && ((page-1)*pageLimit + j) < totalPages; j++) {
            System.out.println(rs.get((page - 1) * pageLimit + j));
        }
    }
}
