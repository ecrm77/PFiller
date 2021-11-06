package pfillerGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * App2Page Class - loads the Frame components for the two-page login sites and puts into Database.
 * @author Ekrem Canavar
 * @version v1
 * @see AddDB2Page
 *
 */

public class App2Page {
    private JTextField username2;
    private JPasswordField password2;
    private JTextField username_Field2;
    private JTextField nextButton_Field2;
    private JButton addButton2;
    private JTextField website2;
    private JPanel panel2;
    private JTextField password_Field2;
    private JTextField finishButton_Field2;
    private JLabel labelMsg;
    private JTextField nameField2;


    public App2Page() {

        JFrame frameAdd2 = new JFrame();
        frameAdd2.setContentPane(panel2);
        frameAdd2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAdd2.setSize(700, 620);
        frameAdd2.setTitle("Add new Website");
        frameAdd2.getRootPane().setDefaultButton(addButton2);
        frameAdd2.setAutoRequestFocus(true);
        frameAdd2.setLocationRelativeTo(null);
        frameAdd2.pack();
        frameAdd2.setVisible(true);


        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String name = nameField2.getText();
                    String website = website2.getText();
                    String username = username2.getText();
                    char[] password = password2.getPassword();
                    String username_Field = username_Field2.getText();
                    String password_Field = password_Field2.getText();
                    String nextButton_Field = nextButton_Field2.getText();
                    String finishButton_Field = finishButton_Field2.getText();


                    AddDB2Page.createNewDatabase(name, website,username, password, username_Field,
                            nextButton_Field, password_Field, finishButton_Field);
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                labelMsg.setText("Added new website into DB");
                nameField2.setText("");
                username2.setText("");
                password2.setText("");
                username_Field2.setText("");
                nextButton_Field2.setText("");
                website2.setText("");
                password_Field2.setText("");
                finishButton_Field2.setText("");

            }
        });
    }
}
