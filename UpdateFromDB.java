package pfillerGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *UpdateFormDB Class - loads DB items for display.
 *@author Ekrem Canavar
 *@version v1
 * @see UpdateDB
 *
 *
 */

public class UpdateFromDB extends MainGui {
    public JPanel updatePanel;
    private JTabbedPane tabbedPane1;
    private JPanel panelUpdate;
    public String name;



    public UpdateFromDB() throws SQLException, IOException {
        super();


        JFrame updateRemove = new JFrame();
        updateRemove.setContentPane(updatePanel);
        updateRemove.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        updateRemove.setTitle("Update Database");
        updateRemove.setAutoRequestFocus(true);
        updateRemove.setLocationRelativeTo(null);
        panelUpdate.setLayout(new BoxLayout(panelUpdate, BoxLayout.Y_AXIS));






        List<String> ls = QueryDB.getItemValues("select name from items");
        int len = ls.size();


        for (int i = 0; i < len; i++) {
            String content = ls.get(i);
            JButton update = new JButton(content);
            update.setName(content);



            panelUpdate.add(update);



            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    try {

                        name= update.getName();
                        UpdateForm updateForm = new UpdateForm(name);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            });

        }


        List<String> ls2 = QueryDB.getItemValues("select name from items2");
        int len2 = ls2.size();


        for (int i = 0; i < len2; i++) {
            String content = ls2.get(i);
            JButton update2 = new JButton(content);
            update2.setName(content);

            panelUpdate.add(update2);


            update2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    try {
                        name = update2.getName();
                        UpdateForm2Page updateForm2Page = new UpdateForm2Page(name);
                    } catch (SQLException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }


                }
            });


        }

        int lange2 = ls2.size();
        int lange = ls.size();
        int h = lange + lange2;
        int height = (h * 35)+50;

        updateRemove.setMinimumSize(new Dimension(300,height));
        //updateRemove.setSize(300,300);
        //updateRemove.setPreferredSize(new Dimension(300,height));
        updateRemove.setResizable(true);
        updateRemove.pack();
        updateRemove.setVisible(true);
    }

}
