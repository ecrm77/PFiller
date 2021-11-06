package pfillerGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * App Class - loads the Frame components for the one-page login sites and puts into Database.
 * @author Ekrem Canavar
 * @version v1
 * @see AddDB
 *
 */

public class App {
    public JPanel panel;
    public JTextField usernameT;
    public JTextField username_fieldT;
    public JButton btnAdd;
    public JTextField websiteT;
    public JTextField password_fieldT;
    public JTextField submit_fieldT;
    public JPasswordField passwordT;
    private JLabel labelMsg;
    private JTextField nameField;


    public App() {
        JFrame frameAdd = new JFrame();
        frameAdd.setContentPane(panel);
        frameAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAdd.setSize(700,600);
        frameAdd.setTitle("Add new Website");
        frameAdd.getRootPane().setDefaultButton(btnAdd);
        frameAdd.setAutoRequestFocus(true);
        frameAdd.setLocationRelativeTo(null);
        frameAdd.pack();
        frameAdd.setVisible(true);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String name = nameField.getText();

                    if(name.contains(".") || name.contains(" ")){
                        String infoMessage = "The Name may not contain empty space or dot(.)";
                        JOptionPane.showMessageDialog(null, infoMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
                        nameField.setText("");
                    }
                    else{
                        String website = websiteT.getText();
                        String username = usernameT.getText();
                        char[] password = passwordT.getPassword();
                        String username_Field = username_fieldT.getText();
                        String password_Field = password_fieldT.getText();
                        String submit_Field = submit_fieldT.getText();

                        AddDB.createNewDatabase(name,website,username,password,username_Field,password_Field,submit_Field);
                        labelMsg.setText("Added new website into DB");
                        nameField.setText("");
                        usernameT.setText("");
                        username_fieldT.setText("");
                        websiteT.setText("");
                        password_fieldT.setText("");
                        submit_fieldT.setText("");
                        passwordT.setText("");

                    }



                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }



            }
        });
    }










}


