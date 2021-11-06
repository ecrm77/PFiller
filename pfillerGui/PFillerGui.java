package pfillerGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * This Class initiates the Application - contains Main, Start and Get and Set Methods for Username and Password.
 *
 * @author Ekrem Canavar
 * @version v1
 *
 *
 *
 */

public class PFillerGui {
    private static String passwordDB;
    private static String usernameDB;

    public PFillerGui() throws SQLException, IOException {

        start();

    }

    public static String getUsernameDB() {
        return usernameDB;
    }

    public static void setUsernameDB(String user) {
        usernameDB = user;
    }

    public static String getPasswordDB() {
        return passwordDB;
    }

    public static void setPasswordDB(String pass) {
        passwordDB = pass;
    }

    /**
     * Main Method - checks the existing Database and executes login or loginFinal method.
     * @param args default
     * @throws SQLException throws SQLException
     * @throws IOException throws IOException
     * @see Login
     * @see LoginFinal
     *
     */

    public static void main(String[] args) throws SQLException, IOException {
        String dirUser = System.getProperty("user.home");
        String pathDB = dirUser+"/PFiller/pfdb";
        Path path = Paths.get(pathDB);
        System.out.println(Files.exists(path));
        boolean fileExist = Files.exists(path);

        if (fileExist){

            loginFinal();
        }
        else {
            login();

        }

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                try {
                    OSCommandOnExit.onExit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }



    public static void login() throws IOException {
        Login login = new Login();

    }

    public static void loginFinal() throws IOException {
        LoginFinal loginFinal = new LoginFinal();

    }

    /**
     * Start Method - loads MainGui Class and set the Application Frame, Taskbar Icon and checks Chrome version.
     * @throws SQLException throws SQLException
     * @throws IOException throws IOException
     * @see MainGui
     */
    public static void start() throws SQLException, IOException {

        MainGui mainGui = new MainGui();
        JFrame frame = new JFrame();
        frame.setContentPane(mainGui.mainPanel);
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

        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Password Filler");
        //frame.setMinimumSize(new Dimension(400,400));
        frame.setMaximumSize(new Dimension(400,1000));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        String pathG = "/Applications/Google Chrome.app";
        File f = new File(pathG);

        if (!f.exists() ){


            JEditorPane ep = new JEditorPane("text/html", "<html>Google Chrome is not found in your default Applications Folder."+"\n"+"<html>Please go to <a href=https://www.google.de/chrome/ > https://www.google.de/chrome/ <a/>and install it.</html>");
            ep.addHyperlinkListener(new HyperlinkListener()
            {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e)
                {
                    if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (URISyntaxException uriSyntaxException) {
                            uriSyntaxException.printStackTrace();
                        }
                    }
                }
            });
            ep.setEditable(false);
            ep.setBackground(frame.getBackground());
            int in = -1;
            Object[] options = {"OK"};
            in = JOptionPane.showOptionDialog(mainGui.mainPanel,ep, "Info", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION, null, options, options[0]);

            if (in == 0) {
                frame.dispose();
            }
        }

        if (f.exists()){

            String scriptDir = dirUser+"/PFiller/res/";
            String command = scriptDir+"browserVersion.sh";
            Process os = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(os.getInputStream()));
            String res = reader.readLine();

            String[] result = res.split("\\.");
            String s = result[0];
            System.out.println(s);


            int sInt = Integer.parseInt(s);

            reader.close();

            if(sInt < 83){

                JEditorPane ep = new JEditorPane("text/html", "<html>Google Chrome version is old."+"\n"+"<html>Please go to <a href=https://www.google.de/chrome/ > https://www.google.de/chrome/ <a/>and install the newest version.</html>");
                ep.addHyperlinkListener(new HyperlinkListener()
                {
                    @Override
                    public void hyperlinkUpdate(HyperlinkEvent e)
                    {
                        if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                            try {
                                Desktop.getDesktop().browse(e.getURL().toURI());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            } catch (URISyntaxException uriSyntaxException) {
                                uriSyntaxException.printStackTrace();
                            }
                        }
                    }
                });
                ep.setEditable(false);
                ep.setBackground(frame.getBackground());
                int in = -1;
                Object[] options = {"OK"};
                in = JOptionPane.showOptionDialog(mainGui.mainPanel,ep, "Info", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION, null, options, options[0]);

                if (in == 0) {
                    frame.dispose();

                }

            }

        }

    }

}
