package pfillerGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *DeleteDB2Page Class - deletes two-page login information from Database "items2".
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */

public class DeleteDB2Page {

    public String website;
    public String username;
    public char[] password;
    public String username_Field;
    public String password_Field;
    public String submit_Field;


    public static void deleteItemsDatabase(String name ) throws ClassNotFoundException, SQLException {

        String usernameDB = PFillerGui.getUsernameDB();
        String passwordDB = PFillerGui.getPasswordDB();
        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        final String JDBC_URL ="jdbc:derby:"+pathDB+";create=true;bootPassword="+passwordDB+";user="+usernameDB+";password="+passwordDB+"";

        // System.out.println(website+" "+username+" "+password+" "+username_Field+" "+password_Field+" "+submit_Field);
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(JDBC_URL);
        String sqlQuery = "DELETE FROM items2 WHERE name = ?";
        PreparedStatement pt = con.prepareStatement(sqlQuery);
        pt.setString(1,name);
//        pt.setString(2,username);
//        pt.setString(3,password);
//        pt.setString(4,username_Field);
//        pt.setString(5,password_Field);
//        pt.setString(6,submit_Field);

        pt.executeUpdate();

        System.out.println("Table items are deleted");


    }
}