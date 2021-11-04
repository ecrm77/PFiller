package pfillerGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *AddDB2Page Class - writes the data into Database for the two-page login websites - Database name is "items2"
 *@author Ekrem Canavar
 *@version v1
 *
 */


public class AddDB2Page {


    public AddDB2Page()  {

    }

    public static void createNewDatabase(String name, String website, String username, char[] password, String username_Field,
                                         String nextButton_Field, String password_Field, String finishButton_Field ) throws ClassNotFoundException, SQLException {
        String usernameDB = PFillerGui.getUsernameDB();
        String passwordDB = PFillerGui.getPasswordDB();
        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        final String JDBC_URL ="jdbc:derby:"+pathDB+";create=true;bootPassword="+passwordDB+";user="+usernameDB+";password="+passwordDB+"";


        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(JDBC_URL);
        String sqlQuery = "INSERT INTO items2 (name, website,username,password,username_field,nextButton_field,password_field,finishButton_field) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sqlQuery);
        pt.setString(1, name);
        pt.setString(2, website);
        pt.setString(3, username);
        pt.setString(4, String.valueOf(password));
        pt.setString(5, username_Field);
        pt.setString(6, nextButton_Field);
        pt.setString(7, password_Field);
        pt.setString(8, finishButton_Field);


        pt.executeUpdate();
        con.close();



        System.out.println("Table2 is inserted");

    }

}
