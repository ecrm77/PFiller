package pfillerGui;

import java.io.*;

/**
 *OSCommandRead Class - write a hidden file (.xxx) that contains Pid ID from the site.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */


public class OSCommandRead {

    public static String res1;
    public static String res2;

    public OSCommandRead() {
    }

    public static void setPid(String command1, String command2, String name) throws IOException{

        try {
            Process os1 = Runtime.getRuntime().exec(command1);
            BufferedReader reader1 = new BufferedReader(
                    new InputStreamReader(os1.getInputStream()));
            String dirUser = System.getProperty("user.home");
            String filename = dirUser+"/PFiller/."+name+"";
            File file = new File(filename);

           file.setWritable(true);
           file.setReadable(true);


            FileWriter writer = new FileWriter(file);

            while ((res1 = reader1.readLine()) != null) {

                writer.write(res1+"\n");

            }

            Process os2 = Runtime.getRuntime().exec(command2);
            BufferedReader reader2 = new BufferedReader(
                    new InputStreamReader(os2.getInputStream()));
            while ((res2 = reader2.readLine()) != null) {

                writer.write(res2+"\n");

            }

            writer.close();

            reader1.close();
            reader2.close();

            file.setReadOnly();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
