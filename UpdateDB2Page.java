package pfillerGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *UpdateDB Class - updates the Database with two-page login information "items2".
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */

public class UpdateDB2Page {


    public static void updateItemsDatabase(String name, String website, String username, char[] password, String username_Field, String nextButton_Field, String password_Field,
                                           String finishButton_Field ) throws ClassNotFoundException, SQLException {

        String usernameDB = PFillerGui.getUsernameDB();
        String passwordDB = PFillerGui.getPasswordDB();

        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        final String JDBC_URL = "jdbc:derby:"+pathDB+";create=true;bootPassword="+passwordDB+";user="+usernameDB+";password="+passwordDB+"";
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(JDBC_URL);
        String sqlQuery = "UPDATE items2 SET website = ?, username = ?, password = ?, username_field = ?, nextButton_field = ?, password_field = ?, finishButton_field = ? WHERE name = ?";
        PreparedStatement pt = con.prepareStatement(sqlQuery);

        pt.setString(1,website);
        pt.setString(2,username);
        pt.setString(3,String.valueOf(password));
        pt.setString(4,username_Field);
        pt.setString(5,nextButton_Field);
        pt.setString(6,password_Field);
        pt.setString(7,finishButton_Field);
        pt.setString(8,name);
        pt.executeUpdate();



        con.close();

        System.out.println("Table items are Updated");


    }
}
