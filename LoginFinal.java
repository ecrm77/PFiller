package pfillerGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *LoginFinal Class - logs user in.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 */

public class LoginFinal {
    private JTextField username2_field;
    private JPasswordField password2_field;
    private JLabel username2;
    private JLabel password2;
    private JButton loginButton;
    private JPanel loginFinal;

    public LoginFinal() throws IOException {

        JFrame frameLogin2 = new JFrame();
        frameLogin2.setContentPane(loginFinal);
        frameLogin2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameLogin2.setMinimumSize(new Dimension(300, 200));
        frameLogin2.setAutoRequestFocus(true);
        frameLogin2.setLocationRelativeTo(null);
        frameLogin2.setTitle("Login");
        frameLogin2.getRootPane().setDefaultButton(loginButton);
        frameLogin2.setResizable(true);
        frameLogin2.pack();
        frameLogin2.setVisible(true);

        String dirUser = System.getProperty("user.home");
        String path = dirUser+"/PFiller/res/PFiller128.png";
        File imageFile = new File(path);
        Image image =  ImageIO.read(imageFile);

        Taskbar taskbar = Taskbar.getTaskbar();
        try {
            //set icon for mac os (and other systems which do support this method)
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }




        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg ="";
                try {
                    String usernameDB = username2_field.getText();
                    String passwordDB = String.valueOf(password2_field.getPassword());
                    PFillerGui.setUsernameDB(usernameDB);
                    PFillerGui.setPasswordDB(passwordDB);
                    String dirUser = System.getProperty("user.home");
                    String pathDB = dirUser+"/PFiller/pfdb";
                    String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
                    final String JDBC_URL = "jdbc:derby:"+pathDB+";create=true;bootPassword=" + passwordDB + ";user=" + usernameDB + ";password=" + passwordDB + "";
                    Class.forName(DRIVER);
                    Connection con = DriverManager.getConnection(JDBC_URL);

                }
                catch(SQLException | ClassNotFoundException sql){
                    msg = sql.getMessage();
                    JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.INFORMATION_MESSAGE);


                    }

                if (msg.equals("")){
                    frameLogin2.dispose();
                    try {
                        PFillerGui.start();
                    } catch (SQLException | IOException throwables) {
                        throwables.printStackTrace();
                    }
                }

            }
        });
    }
}
