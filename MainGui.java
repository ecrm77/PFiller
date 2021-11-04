package pfillerGui;

import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;


/**
 * MainGui Class - loads the Frame components and the existing Database objects and adds object buttons and displays.
 * @author Ekrem Canavar
 * @version v1
 * @see PassDriver
 * @see PassDriver2Page
 * @see App
 *
 */

public class MainGui  {

    public JPanel mainPanel;
    public JTabbedPane mainPane;
    public JPanel tabPanelSettings;
    public JPanel cMain;
    private JButton addNewWebsiteToButton;
    private JButton addNewWebsiteToButton2;
    private JButton removeFromDatabaseButton;
    private JButton reload;
    private JButton updateDB;
    private JEditorPane editorPane1;
    //private JButton remove;
    public String content;
    //public JButton btnRun;
    //public JButton btnRun2;
    public static WebDriver d;
    public static String website;
    public static String name;
    public static int selInt = 0;
    //private JScrollPane scrollPane;


    public MainGui() throws SQLException {
        int row;
        int row2;

        addNewWebsiteToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App app = new App();


            }
        });


        removeFromDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    if(selInt > 0){


                        String msg = "Please close all opened sessions to remove from database";
                        JOptionPane.showMessageDialog(mainPanel, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
                        //JOptionPane.showOptionDialog(mainPanel, msg, "Info", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION, null, options, options[0]);


                    }
                    else {
                        try {
                            RemoveFromDB removeFromDB = new RemoveFromDB();

                        } catch (SQLException | IOException throwables) {
                            throwables.printStackTrace();
                        }

                    }


            }
        });


        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if(selInt > 0){

                        String msg = "Please close all opened sessions to reload from database";
                        JOptionPane.showMessageDialog(mainPanel, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
                        //JOptionPane.showOptionDialog(mainPanel, msg, "Info", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION, null, options, options[0]);


                    }
                    else {
                        reload();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        // Data from ITEMS
        List<String> ls = QueryDB.getItemValues("SELECT name FROM items");
        int len = ls.size();
        editorPane1.setLayout(new BoxLayout(editorPane1, BoxLayout.Y_AXIS));
        editorPane1.setAutoscrolls(true);
        editorPane1.setMaximumSize(new Dimension(200,1000));

        for (int i = 0; i < len; i++) {
            String content = ls.get(i);
            JToggleButton btnRun = new JToggleButton(content);
            btnRun.setName(content);
            //cMain.add(btnRun);
            editorPane1.add(btnRun);

            btnRun.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (btnRun.isSelected()) {

                        selInt++;
                        System.out.println(selInt);
                        name = btnRun.getName();

                        try {
                            List<String> ls1 = QueryDB.getItemValues("SELECT * FROM items");
                            int row1 = 0;
                            for (String content1 : ls1) {
                                if (content1.equals(name)) {

                                    row1 = ls1.indexOf(content1);


                                }
                            }

                            website = ls1.get(row1 + 1);
                            String username = ls1.get(row1 + 2);
                            String password = ls1.get(row1 + 3);
                            String usernameF = ls1.get(row1 + 4);
                            String passwordF = ls1.get(row1 + 5);
                            String submitF = ls1.get(row1 + 6);


                            PassDriver passDriver = new PassDriver();
                            //                      passDriver.drive(website, username, password, usernameF, passwordF, submitF);
                            passDriver.drive(name, website, username, password, usernameF, passwordF, submitF);


                        } catch (SQLException | IOException throwables) {
                            throwables.printStackTrace();
                        }




                    }

                    if (!btnRun.isSelected()) {

                        selInt--;
                        System.out.println(selInt);
                        String name_btn = btnRun.getName();

                        try {
                            OSCommandSend osCommandSend = new OSCommandSend(name_btn);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }


                    }


                }
            });

        }


            // Data from ITEMS2
            List<String> ls2 = QueryDB.getItemValues("SELECT name FROM items2");
            int len2 = ls2.size();

            for (int i = 0; i < len2; i++) {
                String content = ls2.get(i);
                JToggleButton btnRun2 = new JToggleButton(content);
                btnRun2.setName(content);
                editorPane1.add(btnRun2);
                btnRun2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (btnRun2.isSelected()) {

                            selInt++;

                            name = btnRun2.getName();


                            try {
                                List<String> ls21 = QueryDB.getItemValues("SELECT * FROM items2");
                                int row2 = 0;
                                for (String content : ls21) {
                                    if (content.equals(name)) {

                                        row2 = ls21.indexOf(content);


                                    }
                                }
                                String website = ls21.get(row2 + 1);
                                String username = ls21.get(row2 + 2);
                                String password = ls21.get(row2 + 3);
                                String usernameF = ls21.get(row2 + 4);
                                String nextButtonF = ls21.get(row2 + 5);
                                String passwordF = ls21.get(row2 + 6);
                                String finishButtonF = ls21.get(row2 + 7);

                                //System.out.println(username+password+usernameF+passwordF+submitF);
                                PassDriver2Page passDriver2Page = new PassDriver2Page();
                                passDriver2Page.drive(name, website, username, password, usernameF, nextButtonF, passwordF, finishButtonF);

                            } catch (SQLException | IOException throwables) {
                                throwables.printStackTrace();
                            }


                        }
                        if (!btnRun2.isSelected()) {

                            selInt--;

                            String name_btn2 = btnRun2.getName();

                            try {
                                OSCommandSend osCommandSend = new OSCommandSend(name_btn2);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }


                        }

                    }
                });

            }


            addNewWebsiteToButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    App2Page app2 = new App2Page();
                }
            });

            updateDB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(selInt > 0){

                        String msg = "Please close all opened sessions to update the database";
                        JOptionPane.showMessageDialog(mainPanel, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
                        //JOptionPane.showOptionDialog(mainPanel, msg, "Info", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION, null, options, options[0]);


                    }
                    else {

                        try {
                            UpdateFromDB updateFromDB = new UpdateFromDB();
                        } catch (SQLException | IOException throwables) {
                            throwables.printStackTrace();
                        }

                    }

                }
            });
        }

        public void reload () throws SQLException {
            editorPane1.removeAll();
            editorPane1.updateUI();
            editorPane1.setLayout(new BoxLayout(editorPane1, BoxLayout.Y_AXIS));
            List<String> ls = QueryDB.getItemValues("SELECT name FROM items");
            int len = ls.size();
            editorPane1.setLayout(new BoxLayout(editorPane1, BoxLayout.Y_AXIS));
            for (int i = 0; i < len; i++) {
                String content = ls.get(i);
                JToggleButton btnRun = new JToggleButton(content);
                btnRun.setName(content);
                editorPane1.add(btnRun);
                btnRun.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (btnRun.isSelected()) {

                            selInt++;

                            name = btnRun.getName();

                            try {
                                List<String> ls1 = QueryDB.getItemValues("SELECT * FROM items");
                                int row1 = 0;
                                for (String content1 : ls1) {
                                    if (content1.equals(name)) {

                                        row1 = ls1.indexOf(content1);


                                    }
                                }

                                website = ls1.get(row1 + 1);
                                String username = ls1.get(row1 + 2);
                                String password = ls1.get(row1 + 3);
                                String usernameF = ls1.get(row1 + 4);
                                String passwordF = ls1.get(row1 + 5);
                                String submitF = ls1.get(row1 + 6);


                                PassDriver passDriver = new PassDriver();
                                //                      passDriver.drive(website, username, password, usernameF, passwordF, submitF);
                                passDriver.drive(name, website, username, password, usernameF, passwordF, submitF);


                            } catch (SQLException | IOException throwables) {
                                throwables.printStackTrace();
                            }


                        }
                        if (!btnRun.isSelected()) {

                            selInt--;

                            String name_btn = btnRun.getName();

                            try {
                                OSCommandSend osCommandSend = new OSCommandSend(name_btn);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }


                        }

                    }
                });

            }


            // Data from ITEMS2
            List<String> ls2 = QueryDB.getItemValues("SELECT name FROM items2");
            int len2 = ls2.size();

            for (int i = 0; i < len2; i++) {
                String content = ls2.get(i);
                JToggleButton btnRun2 = new JToggleButton(content);
                btnRun2.setName(content);
                editorPane1.add(btnRun2);
                btnRun2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (btnRun2.isSelected()) {
                            selInt++;
                            name = btnRun2.getName();


                            try {
                                List<String> ls21 = QueryDB.getItemValues("SELECT * FROM items2");
                                int row2 = 0;
                                for (String content : ls21) {
                                    if (content.equals(name)) {

                                        row2 = ls21.indexOf(content);


                                    }
                                }
                                String website = ls21.get(row2 + 1);
                                String username = ls21.get(row2 + 2);
                                String password = ls21.get(row2 + 3);
                                String usernameF = ls21.get(row2 + 4);
                                String nextButtonF = ls21.get(row2 + 5);
                                String passwordF = ls21.get(row2 + 6);
                                String finishButtonF = ls21.get(row2 + 7);

                                //System.out.println(username+password+usernameF+passwordF+submitF);
                                PassDriver2Page passDriver2Page = new PassDriver2Page();
                                passDriver2Page.drive(name,website, username, password, usernameF, nextButtonF, passwordF, finishButtonF);

                            } catch (SQLException | IOException throwables) {
                                throwables.printStackTrace();
                            }

                        }
                        if (!btnRun2.isSelected()) {

                            selInt--;

                            String name_btn2 = btnRun2.getName();

                            try {
                                OSCommandSend osCommandSend = new OSCommandSend(name_btn2);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }


                        }

                    }
                });

            }

        }





}







