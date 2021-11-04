package pfillerGui;

import java.io.IOException;

/**
 *OSCommandOnExit Class - kills all chromedriver by exit the Application.
 *@author Ekrem Canavar
 *@version v1
 *
 *
 *
 */

public class OSCommandOnExit {



    public static void onExit() throws IOException {
        Runtime.getRuntime().exec("killall chromedriver");
    }



}
