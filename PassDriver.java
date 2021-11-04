package pfillerGui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * PassDriver Class - executes the Chromedriver for the one-page login sites.
 * @author Ekrem Canavar
 * @version v1
 * @see OSCommandKill
 *
 */

public class PassDriver {


    public void drive(String name, String website, String username, String password, String username_Field,
                      String password_Field, String submit_Field) throws IOException {



        String dirUser = System.getProperty("user.home");

        System.setProperty("webdriver.chrome.driver", ""+dirUser+"/PFiller/chromedriver");
        //System.setProperty("webdriver.gecko.driver", "/Users/ecrm/IdeaProjects/testgui/webdriver/geckodriver");
        //WebDriver driver = new FirefoxDriver();
        ChromeOptions options =new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String []{"enable-automation"});
        //options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        Runtime.getRuntime().exec("killall chromedriver");

        WebDriver driver = new ChromeDriver(options);

        OSCommandRead.setPid("pgrep -n chrome_crashpad_handler","pgrep -n chromedriver", name);

        driver.get(website);

        if(username.equals("")){

            OSCommandKill osCommandKill = new OSCommandKill(name);
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement usernameW = driver.findElement(By.xpath(username_Field));
        WebElement passwordW = driver.findElement(By.xpath(password_Field));
        usernameW.sendKeys(username);
        passwordW.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath(submit_Field));

        loginButton.click();

        OSCommandKill osCommandKill = new OSCommandKill(name);


    }

}

