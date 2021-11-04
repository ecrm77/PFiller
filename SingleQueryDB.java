package pfillerGui;

import java.sql.*;

/**
 *SingleQueryDB Class - returns specific Database objects as String Array.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */

public class SingleQueryDB {




    public static String[] getItemValue(String stmt) throws SQLException {
        //this.SQL_STATEMENT = stmt;
        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        final String JDBC_URL = "jdbc:derby:"+pathDB+";create=true";
        Connection con = DriverManager.getConnection(JDBC_URL);
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(stmt);
        int columnCnt = resultSet.getMetaData().getColumnCount();
        String[] objectStr = new String[10];
        for (int i = 0; i<columnCnt; i++) {
            objectStr[i] = resultSet.getString(i);
        }


        resultSet.close();

        statement.close();



        return objectStr;

    }


}
