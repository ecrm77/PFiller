package pfillerGui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *QueryDB Class - returns all the Database objects as String List.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */

public class QueryDB {

    public QueryDB(){


    }

    public static List<String> getItemValues(String stmt) throws SQLException {

        String usernameDB = PFillerGui.getUsernameDB();
        String passwordDB = PFillerGui.getPasswordDB();

        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        final String JDBC_URL ="jdbc:derby:"+pathDB+";create=true;bootPassword="+passwordDB+";user="+usernameDB+";password="+passwordDB+"";
        List<String> objectStr = new ArrayList<String>();
        Connection con = DriverManager.getConnection(JDBC_URL);
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(stmt);
        int columnCnt = resultSet.getMetaData().getColumnCount();


        while(resultSet.next()) {
            for(int i = 1; i <= columnCnt;){
                String y = resultSet.getString(i);
                objectStr.add(y);
                i++;
               // System.out.println(y);
            }


        }

        resultSet.close();

        statement.close();

        con.close();



        return objectStr;

    }


}