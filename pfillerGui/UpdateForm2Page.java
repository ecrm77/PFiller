package pfillerGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 *UpdateForm2Page Class - loads Form component for two-page login sites.
 *@author Ekrem Canavar
 *@version v1
 *@see UpdateDB2Page
 */
public class UpdateForm2Page {
    private JButton update2Btn;
    private JTextField update2_website;
    private JTextField update2_username;
    private JTextField update2_usernameField;
    private JTextField update2_nextButtonField;
    private JTextField update2_passwordField;
    private JTextField update2_finishButtonField;
    private JPasswordField update2_password;
    private JPanel update2;
    private JTextField update2_nameField;



    public UpdateForm2Page(String name) throws SQLException {

        JFrame frameUpdate2 = new JFrame();
        frameUpdate2.setContentPane(update2);
        frameUpdate2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameUpdate2.setSize(700,600);
        frameUpdate2.setTitle("Update Database");
        frameUpdate2.getRootPane().setDefaultButton(update2Btn);
        frameUpdate2.setAutoRequestFocus(true);
        frameUpdate2.setLocationRelativeTo(null);
        frameUpdate2.pack();
        frameUpdate2.setVisible(true);


        List<String> ls1 = QueryDB.getItemValues("SELECT * FROM items2");
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
        String nextButtonF = ls1.get(row+5);
        String passwordF = ls1.get(row+6);
        String finishButtonF = ls1.get(row+7);

        update2_nameField.setText(name);
        update2_website.setText(website);
        update2_username.setText(username);
        update2_password.setText(password);
        update2_usernameField.setText(usernameF);
        update2_passwordField.setText(passwordF);
        update2_nextButtonField.setText(nextButtonF);
        update2_finishButtonField.setText(finishButtonF);


        update2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = update2_nameField.getText();
                String website = update2_website.getText();
                String username = update2_username.getText();
                char[] password = update2_password.getPassword();
                String usernameF = update2_usernameField.getText();
                String passwordF = update2_passwordField.getText();
                String nextButtonF = update2_nextButtonField.getText();
                String finishButtonF = update2_finishButtonField.getText();


                try {

                    UpdateDB2Page.updateItemsDatabase(name,website,username,password,usernameF,nextButtonF,passwordF,finishButtonF);
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                frameUpdate2.dispose();

            }
        });
    }
}
