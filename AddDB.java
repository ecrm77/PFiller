package pfillerGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * AddDB Class - writes the data into Database for the one-page login websites - Database name is "items"
 * @author Ekrem Canavar
 * @version v1
 *
 */

public class AddDB {

    public AddDB()  {

    }

    public static void createNewDatabase(String name, String website, String username, char[] password, String username_Field,
                                         String password_Field, String submit_Field ) throws ClassNotFoundException, SQLException {

        String usernameDB = PFillerGui.getUsernameDB();
        String passwordDB = PFillerGui.getPasswordDB();
        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        final String JDBC_URL ="jdbc:derby:"+pathDB+";create=true;bootPassword="+passwordDB+";user="+usernameDB+";password="+passwordDB+"";


        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(JDBC_URL);
        String sqlQuery = "INSERT INTO items (name, website,username,password,username_field,password_field,submit_field) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sqlQuery);
        pt.setString(1, name);
        pt.setString(2, website);
        pt.setString(3, username);
        pt.setString(4, String.valueOf(password));
        pt.setString(5, username_Field);
        pt.setString(6, password_Field);
        pt.setString(7, submit_Field);

        pt.executeUpdate();
        con.close();



        System.out.println("Table is inserted");

    }

}
