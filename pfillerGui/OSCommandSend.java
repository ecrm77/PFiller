package pfillerGui;

import java.io.*;
import java.util.ArrayList;

/**
 *OSCommandSend Class - kills the chromedriver with all the Pid IDs.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */


public class OSCommandSend {



    public OSCommandSend(String name) throws FileNotFoundException {

        try {
            String dirUser = System.getProperty("user.home");
            String filename = dirUser+"/PFiller/."+name+"";

            BufferedReader br = new BufferedReader(new FileReader(filename));

            String i;
            ArrayList<Integer> b = new ArrayList<>();
            while ((i=br.readLine()) != null){
                int a = Integer.parseInt(i);
                b.add(a);

            }
            int driverPid = b.get(1);
            int c = b.get(0)-b.get(1);

            for(int s =0; s<c; s++){
                int driverPid1 = driverPid + s;
                System.out.println(driverPid1);
                Runtime.getRuntime().exec("kill "+driverPid1);

            }




        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}