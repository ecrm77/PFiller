package pfillerGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *RemoveFromDB Class - removes entries from DB.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 */

public class RemoveFromDB extends MainGui {
    public JPanel removePanel;
    private JTabbedPane tabbedPane1;
    private JPanel panelRemoveDB;
    public String website;


    public RemoveFromDB() throws SQLException, IOException {
        super();


        JFrame frameRemove = new JFrame();
        frameRemove.setContentPane(removePanel);
        frameRemove.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameRemove.setTitle("Remove from Database");
        frameRemove.setAutoRequestFocus(true);
        frameRemove.setLocationRelativeTo(null);
        panelRemoveDB.setLayout(new BoxLayout(panelRemoveDB, BoxLayout.Y_AXIS));







            QueryDB queryDB = new QueryDB();
            List<String> ls = QueryDB.getItemValues("select name from items");
            int len = ls.size();


            for (int i = 0; i < len; i++) {
                String content = ls.get(i);
                JButton reMove = new JButton(content);

                reMove.setName(content);

                panelRemoveDB.add(reMove);

                reMove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        try {
                            website = reMove.getName();
                            DeleteDB.deleteItemsDatabase(website);
                        } catch (ClassNotFoundException | SQLException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        String infoMessage = "Deleted from DB " + "\n" + website;
                        int in = -1;
                        Object[] options = {"OK"};
                        in = JOptionPane.showOptionDialog(removePanel, infoMessage, "Delete", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION, null, options, options[0]);

                        if (in == 0) {
                            frameRemove.dispose();


                        }


                    }
                });


            }

            List<String> ls2 = queryDB.getItemValues("select name from items2");
            int len2 = ls2.size();

            for (int i = 0; i < len2; i++) {
                String content = ls2.get(i);
                JButton reMove2 = new JButton(content);
                reMove2.setName(content);

                panelRemoveDB.add(reMove2);

                reMove2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        try {
                            website = reMove2.getName();
                            DeleteDB2Page.deleteItemsDatabase(website);
                        } catch (ClassNotFoundException | SQLException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        String infoMessage = "Deleted from DB " + "\n" + website;
                        int in = -1;
                        Object[] options = {"OK"};
                        in = JOptionPane.showOptionDialog(removePanel, infoMessage, "Delete", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION, null, options, options[0]);

                        if (in == 0) {
                            frameRemove.dispose();


                        }


                    }
                });


            }
        int lange2 = ls2.size();
        int lange = ls.size();
        int h = lange + lange2;
        int height = (h * 35)+50;
        frameRemove.setMinimumSize(new Dimension(300,height));
        //frameRemove.setSize(300,height);
        //frameRemove.setMinimumSize(new Dimension(300, height));
        frameRemove.setResizable(true);
        frameRemove.pack();
        frameRemove.setVisible(true);


        }

}


        



