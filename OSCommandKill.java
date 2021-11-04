package pfillerGui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *OSCommandKill Class - kills the chromedriver with specific Pid ID.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */

public class OSCommandKill {




    public OSCommandKill(String name) throws FileNotFoundException {

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
            int c = b.get(1);
            Runtime.getRuntime().exec("kill "+c+"");

        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
