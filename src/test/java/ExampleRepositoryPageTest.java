import utils.ConfProperties;
import utils.driver.DriversHandler;
import utils.driver.exception.InvalidPropertiesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ExampleRepositoryPage;
import pages.HomePage;
import pages.LoginPage;
import pages.StartPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleRepositoryPageTest {
    List<ExampleRepositoryPage> exampleRepositoryPages;
    List<HomePage> homePages;
    List<LoginPage> loginPages;
    List<StartPage> startPages;

    @BeforeEach
    public void setUp() {
        DriversHandler.initDrivers();
        exampleRepositoryPages = new ArrayList<>();
        homePages = new ArrayList<>();
        loginPages = new ArrayList<>();
        startPages = new ArrayList<>();
        if (DriversHandler.isDriversEmpty()) throw new InvalidPropertiesException();

        DriversHandler.drivers.forEach(driver -> {
            driver.get(ConfProperties.getProperty("login_page"));
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            loginPages.add(new LoginPage(driver));
            homePages.add(new HomePage(driver));
            driver.manage().window().maximize();
        });

        loginPages.forEach(loginPage -> {
            String login = ConfProperties.getProperty("login");
            String password = ConfProperties.getProperty("password");
            loginPage.enterLogin(login);
            loginPage.enterPassword(password);
            loginPage.signInBtnClick();
            loginPage.waitForUrl(ConfProperties.getProperty("start_page"));
        });
        homePages.forEach(homePage -> {
            homePage.profileIconClick();
            homePage.repositoriesBtnClick();

        });
        homePages.forEach(HomePage::profileIconClick);


        DriversHandler.drivers.forEach(driver -> {
            driver.get("https://github.com/rimnvd/software-testing3");
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            exampleRepositoryPages.add(new ExampleRepositoryPage(driver));
            driver.manage().window().maximize();
        });
    }

    @AfterEach
    public void close() {
        DriversHandler.closeDrivers();
    }

    @Test
    public void createNewFileBtnTest() {
        exampleRepositoryPages.forEach(exampleRepositoryPage -> {
            exampleRepositoryPage.createNewFileClk();
            exampleRepositoryPage.waitForUrl("https://github.com/rimnvd/software-testing3/new/main");
            assertEquals("https://github.com/rimnvd/software-testing3/new/main", exampleRepositoryPage.getWebDriver().getCurrentUrl());
        });
    }



}
