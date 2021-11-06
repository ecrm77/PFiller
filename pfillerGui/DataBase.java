package pfillerGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *DataBase Class - creates initial Database.
 *@author Ekrem Canavar
 *@version v1
 * @see DataBase2Page
 *
 *
 */

public class DataBase {
    public String usernameDB;
    public String passwordDB;
    //final String JDBC_URL = "jdbc:derby:pfdb;create=true";



    public DataBase(String usernameDB1, String passwordDB1) throws ClassNotFoundException, SQLException {

        this.usernameDB = usernameDB1;
        this.passwordDB = passwordDB1;
        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        String JDBC_URL = "jdbc:derby:"+pathDB+";create=true;dataEncryption=true;encryptionAlgorithm=Blowfish/CBC/NoPadding;bootPassword="+passwordDB1+"";
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(JDBC_URL);

        Statement s = con.createStatement();



        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.connection.requireAuthentication', 'true')");

        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.authentication.provider', 'BUILTIN')");

        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user."+usernameDB1+"', '"+passwordDB1+"')");

//        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
//                "'derby.user."+usernameDB+"', '"+passwordDB+"')");
//        System.out.println("executeupdate: "+usernameDB+" "+passwordDB);

        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.propertiesOnly', 'true')");

        System.out.println("Database is created");
        s.close();
        con.close();

        DataBase2Page dataBase2Page = new DataBase2Page(usernameDB1,passwordDB1);



    }




}