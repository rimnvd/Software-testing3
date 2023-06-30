package utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfProperties;

import java.util.ArrayList;

public class DriversHandler {

    public static ArrayList<WebDriver> drivers = new ArrayList<>();

    public static void initDrivers() {
        if (ConfProperties.getProperty(Constants.WEBDRIVER_CHROME_DRIVER) != null) {
            if (ConfProperties.getProperty(Constants.WEBDRIVER_CHROME_DRIVER).equals(Constants.CHROME_DRIVER)) {
                System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, ConfProperties.getProperty(Constants.WEBDRIVER_CHROME_DRIVER));
                drivers.add(new ChromeDriver());
            }
        }
        
        if (ConfProperties.getProperty(Constants.WEBDRIVER_FIREFOX_DRIVER) != null) {
            if (ConfProperties.getProperty(Constants.WEBDRIVER_FIREFOX_DRIVER).equals(Constants.FIREFOX_FIREFOX)) {
                System.setProperty(Constants.WEBDRIVER_FIREFOX_DRIVER, ConfProperties.getProperty(Constants.WEBDRIVER_FIREFOX_DRIVER));
                drivers.add(new FirefoxDriver());
            }
        }
    }

    public static void closeDrivers() {
        drivers.forEach(WebDriver::close);
    }

    public static boolean isDriversEmpty() {
        return drivers.isEmpty();
    }
}
