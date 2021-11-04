package pfillerGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

/**
 * Login Class - creates a new user to login.
 * @author Ekrem Canavar
 * @version v1
 * @see AddDB
 *
 */

public class Login {

    private JPasswordField passwordR;
    private JPasswordField password;
    private JButton button1;
    private JTextField username;
    private JPanel login;



    public Login() {
        JFrame frameLogin = new JFrame();
        frameLogin.setContentPane(login);
        frameLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameLogin.setMinimumSize(new Dimension(500, 300));
        frameLogin.setAutoRequestFocus(true);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setTitle("Create Login");
        frameLogin.getRootPane().setDefaultButton(button1);
        frameLogin.setResizable(true);
        frameLogin.pack();
        frameLogin.setVisible(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = username.getText();
                String pass = String.valueOf(password.getPassword());
                String passR = String.valueOf(passwordR.getPassword());
                int pLenght = pass.length();

                if (pass.equals(passR) && pLenght >= 8 ){

                    try {

                        DataBase dataBase = new DataBase(user,pass);

                        File sourceFile = new File( "/Applications/PFiller.app/Contents/app/webdriver/chromedriver" );
                        Path sourcePath = sourceFile.toPath();
                        String dirUser = System.getProperty("user.home");
                        File theDir = new File(dirUser+"/PFiller");
                        if (!theDir.exists()){
                            theDir.mkdirs();
                        }
                        String pathDB = dirUser+"/PFiller/chromedriver";
                        File destFile = new File(pathDB);
                        Path destPath = destFile.toPath();

                        Files.copy( sourcePath, destPath );

                        String f = "/Applications/PFiller.app/Contents/app/res/PFiller128.png";

                        BufferedImage bi = ImageIO.read(new File(f));

                        String dirUser2 = System.getProperty("user.home");
                        new File(dirUser2+"/PFiller/res").mkdir();

                        String pathDB2 = dirUser2+"/PFiller/res/PFiller128.png";
                        ImageIO.write(bi, "png", new File(pathDB2));

                        File sourceFileSH = new File( "/Applications/PFiller.app/Contents/app/res/browserVersion.sh" );
                        Path sourcePathSH = sourceFileSH.toPath();

                        String pathDBsh = dirUser+"/PFiller/res/browserVersion.sh";
                        File destFileSH = new File(pathDBsh);
                        Path destPathSH = destFileSH.toPath();

                        Files.copy( sourcePathSH, destPathSH );


                        String executable="chmod u+x " + pathDBsh;
                        Runtime.getRuntime().exec(executable);

                        frameLogin.dispose();
                        LoginFinal loginFinal = new LoginFinal();

                    } catch (IOException | SQLException | ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }


                }
                else {
                    String infoMessage = "Password is not matching or contains less than 8 chracters";
                    JOptionPane.showMessageDialog(null, infoMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
                }



            }
        });

    }


}
