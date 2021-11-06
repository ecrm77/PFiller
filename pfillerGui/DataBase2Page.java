package pfillerGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *DataBase2Page Class - creates Database tables for one, and two-page login information.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */

public class DataBase2Page {
    public String usernameDB1;
    public String passwordDB1;


    public DataBase2Page(String usernameDB2, String passwordDB2) throws ClassNotFoundException, SQLException {
        this.usernameDB1 = usernameDB2;
        this.passwordDB1 = passwordDB2;

        String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        String JDBC_URL = "jdbc:derby:"+pathDB+";create=true;user="+usernameDB2+";password="+passwordDB2+"";

        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(JDBC_URL);

        con.createStatement().execute("create table items (name varchar(250), website varchar(1000), username varchar(250), " +
                "password varchar(250), username_field varchar(1000), password_field varchar(1000), submit_field varchar(1000))");
        con.createStatement().execute("create table items2 (name varchar(250), website varchar(1000), username varchar(250), " +
                "password varchar(250), username_field varchar(1000), nextButton_field varchar(1000), password_field varchar(1000), finishButton_field varchar(1000))");


        con.close();
        System.out.println("Tables are created");


    }
}
