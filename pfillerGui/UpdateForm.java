package pfillerGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 *UpdateForm Class - loads Form component for one-page login sites.
 *@author Ekrem Canavar
 *@version v1
 * @see UpdateDB
 *
 *
 */
public class UpdateForm {

    private JTextField update_website;
    private JPasswordField update_password;
    private JTextField update_usernameField;
    private JTextField update_passwordField;
    private JTextField update_submitField;
    private JPanel update;
    private JButton updateBtn;
    private JTextField update_username;
    private JTextField update_nameField;


    public UpdateForm(String name) throws SQLException {



        JFrame frameUpdate = new JFrame();
        frameUpdate.setContentPane(update);
        frameUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameUpdate.setSize(700,600);
        frameUpdate.setTitle("Update Database");
        frameUpdate.getRootPane().setDefaultButton(updateBtn);
        frameUpdate.setAutoRequestFocus(true);
        frameUpdate.setLocationRelativeTo(null);
        frameUpdate.pack();
        frameUpdate.setVisible(true);

        List<String> ls1 = QueryDB.getItemValues("SELECT * FROM items");
        int row = 0;
        for (String content : ls1) {
            if (content.equals(name)) {

                row = ls1.indexOf(content);


            }
        }
        String website = ls1.get(row+1);
        String username = ls1.get(row+2);
        String password = ls1.get(row+3);
        String usernameF = ls1.get(row+4);
        String passwordF = ls1.get(row+5);
        String submitF = ls1.get(row+6);

        update_nameField.setText(name);
        update_website.setText(website);
        update_username.setText(username);
        update_password.setText(password);
        update_usernameField.setText(usernameF);
        update_passwordField.setText(passwordF);
        update_submitField.setText(submitF);


        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = update_nameField.getText();
                String website = update_website.getText();
                String username = update_username.getText();
                char[] password = update_password.getPassword();
                String usernameF = update_usernameField.getText();
                String passwordF = update_passwordField.getText();
                String submitF = update_submitField.getText();

                System.out.println(name+website+username+password+usernameF+passwordF+submitF);
                try {
                    UpdateDB.updateItemsDatabase(name,website,username,password,usernameF,passwordF,submitF);

                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                frameUpdate.dispose();



            }
        });
    }

}
