import utils.ConfProperties;
import utils.driver.DriversHandler;
import utils.driver.exception.InvalidPropertiesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {
    List<LoginPage> loginPages;
    List<HomePage> homePages;

    @BeforeEach
    public void setUp() {
        DriversHandler.initDrivers();
        loginPages = new ArrayList<>();
        homePages = new ArrayList<>();
        if (DriversHandler.isDriversEmpty()) throw new InvalidPropertiesException();
        DriversHandler.drivers.forEach(driver -> {
            driver.get(ConfProperties.getProperty("login_page"));
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            loginPages.add(new LoginPage(driver));
            homePages.add(new HomePage(driver));
            driver.manage().window().maximize();
        });
    }

    @AfterEach
    public void close() {
        DriversHandler.closeDrivers();
    }

    @Test
    @DisplayName("Create an account button click test")
    public void createAccBtnClickTest() {
        loginPages.forEach(loginPage -> {
            loginPage.createAccBtnClick();
            loginPage.waitForUrl(ConfProperties.getProperty("create_account_page"));
            assertEquals(ConfProperties.getProperty("create_account_page"), loginPage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Sign in click with incorrect password test")
    public void signInClickWithIncorrectPassword() {
        loginPages.forEach(loginPage -> {
            loginPage.enterLogin(ConfProperties.getProperty("login"));
            loginPage.enterPassword(ConfProperties.getProperty("incorrect_password"));
            loginPage.signInBtnClick();
            loginPage.waitForUrl(ConfProperties.getProperty("session_page"));
            assertEquals(ConfProperties.getProperty("session_page"), loginPage.getWebDriver().getCurrentUrl());
            assertTrue(loginPage.getIncorrectMsg().isDisplayed());
        });
    }

    @Test
    @DisplayName("Sign in click test")
    public void signInClick() {
        loginPages.forEach(loginPage -> {
            loginPage.enterLogin(ConfProperties.getProperty("login"));
            loginPage.enterPassword(ConfProperties.getProperty("password"));
            loginPage.signInBtnClick();
            loginPage.waitForUrl(ConfProperties.getProperty("start_page"));
        });
    }


}
