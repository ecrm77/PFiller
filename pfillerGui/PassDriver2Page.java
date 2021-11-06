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
 *PassDriver2Page Class - executes the Chromedriver for the two-page login sites.
 *@author Ekrem Canavar
 *@version v1
 *@see OSCommandKill
 *
 *
 */


public class PassDriver2Page {

    public PassDriver2Page() throws IOException {

    }

    public void drive (String name, String website, String username, String password, String username_Field,
                       String nextButton_Field, String password_Field, String funishButton_Field) throws IOException {
        String dirUser = System.getProperty("user.home");
        // System.out.println("Current relative path is: " + s);
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement usernameW = driver.findElement(By.xpath(username_Field));
        WebElement nextButton = driver.findElement(By.xpath(nextButton_Field));
        usernameW.sendKeys(username);
        nextButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement passwordW = driver.findElement(By.xpath(password_Field));
        WebElement finishButton = driver.findElement(By.xpath(funishButton_Field));
        passwordW.sendKeys(password);
        finishButton.click();

        OSCommandKill osCommandKill = new OSCommandKill(name);



    }


}