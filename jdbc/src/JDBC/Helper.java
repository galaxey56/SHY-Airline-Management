package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;
import java.util.*;

public class Helper {
    final static int pageLimit = 10;
    static String header;

    public String getHeader() {
        return header;
    }

    public static void setHeader(ResultSet rs) throws SQLException {
        Helper.header = getHeader(rs);
    }

    private static String getHeader(ResultSet rs) throws SQLException {
        int count = rs.getMetaData().getColumnCount();
        String ref = "";
        String colName[] = new String[count];
        for (int i = 0; i < count; i++) {
            colName[i] = (String) rs.getMetaData().getColumnLabel(i + 1);
            ref += colName[i] + " ";
        }
        return ref;
    }

    public static String[][] returnDoubleArray(ResultSet rs, int page, int total) throws Exception {
        int maxPages = (total -1 )/ pageLimit + 1;
        if (page > maxPages) {
            System.out.println("Page limit exceeded");
            return null;
        }
        if(page == maxPages){
            printing(makeList(rs), page, total);
            return null;
        }
        int count = rs.getMetaData().getColumnCount();
        String[][] hey = new String[total+1][count];
        for(int j = 0; j < count; j++)hey[0][j] = rs.getMetaData().getColumnLabel(j+1);
        int i = 1;
        while(rs.next()) {
            for (int j = 0; j < count; j++) {
                hey[i][j] = rs.getObject(j + 1) + " ";
            }
            i++;
        }
        System.out.println("Displaying page " + (page) + "/" + maxPages + "\n");
        String[][] yayy = new String[11][count];
        for(int j = 0; j < count; j++)yayy[0][j] = hey[0][j];
        
        for (int j = 1; j < pageLimit + 1 && ((page - 1) * pageLimit + j) < total; j++) {
            for(int k = 0; k < count; k++)
            yayy[j][k] = hey[(page - 1) * pageLimit + j ][k];
        }
        return yayy;
    }

    public static void simpleTable(String[][] table, int page, int total) {
        int maxPages = (total-1) / pageLimit + 1;
        if (page > maxPages) {
            System.out.println("Page limit exceeded");
            return;
        }
        if(page == maxPages)return;
        boolean leftJustifiedRows = false;
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
     
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
			.forEach(a -> System.out.printf(formatString.toString(), table[a]));
    }
    public static int getCount(ResultSet rs) throws Exception {
        int ref = 0;
        while (rs.next())
            ref++;
        return ref;
    }

    public static void printing(List<String> rs, int page, int totalEntries) throws Exception {
        int maxPages = totalEntries / pageLimit + 1;
        if (page > maxPages) {
            System.out.println("Page limit exceeded");
            return;
        }
        System.out.println("Displaying page " + (page) + "/" + maxPages + "\n");
        System.out.println(header);
        System.out.println("----------------------------------------------------------------------");
        for (int j = 0; j < pageLimit && ((page - 1) * pageLimit + j) < totalEntries; j++) {
            System.out.println(rs.get((page - 1) * pageLimit + j));
        }
    }
    public static List<String> makeList(ResultSet rs) throws Exception {

        int count = rs.getMetaData().getColumnCount();
        List<String> hello = new ArrayList<String>();

        setHeader(rs);
        while (rs.next()) {
            String ref = "";
            for (int i = 0; i < count; i++) {
                Object val = rs.getObject(i + 1);
                ref += val + " ";
            }
            hello.add(ref);
        }
        return hello;
    }
}
