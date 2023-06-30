import utils.ConfProperties;
import utils.driver.DriversHandler;
import utils.driver.exception.InvalidPropertiesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.StartPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomePageTest {

    List<HomePage> homePages;
    List<LoginPage> loginPages;
    List<StartPage> startPages;

    @BeforeEach
    public void setUp() {
        DriversHandler.initDrivers();
        homePages = new ArrayList<>();
        loginPages = new ArrayList<>();
        startPages = new ArrayList<>();
        if (DriversHandler.isDriversEmpty()) throw new InvalidPropertiesException();
        DriversHandler.drivers.parallelStream().forEach(driver -> {
            driver.get(ConfProperties.getProperty("start_page"));
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            loginPages.add(new LoginPage(driver));
            startPages.add(new StartPage(driver));
            homePages.add(new HomePage(driver));
            driver.manage().window().maximize();
        });
        startPages.forEach(startPage -> {
            startPage.signInBtnClick();
            startPage.waitForUrl(ConfProperties.getProperty("login_page"));
        });
        loginPages.forEach(loginPage -> {
            String login = ConfProperties.getProperty("login");
            String password = ConfProperties.getProperty("password");
            loginPage.enterLogin(login);
            loginPage.enterPassword(password);
            loginPage.signInBtnClick();
            loginPage.waitForUrl(ConfProperties.getProperty("start_page"));
        });
    }

    @AfterEach
    public void close() {
        DriversHandler.closeDrivers();
    }

    @Test
    @DisplayName("Issues button click test")
    public void issuesBtnClickTest() {
        homePages.forEach(homePage -> {
            homePage.issuesBtnClick();
            homePage.waitForUrl(ConfProperties.getProperty("issues_page"));
            assertEquals(ConfProperties.getProperty("issues_page"), homePage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Profile icon click test")
    public void profileIconClickTest() {
        homePages.forEach(homePage -> {
            homePage.profileIconClick();
            assertTrue(homePage.getProfileIconMenu().isDisplayed());
        });
    }

    @Test
    @DisplayName("Your profile button click test")
    public void yourProfileBtnClickTest() {
        homePages.forEach(homePage -> {
            homePage.profileIconClick();
            homePage.yourProfileBtnClick();
            homePage.waitForUrl(ConfProperties.getProperty("profile_page"));
            assertEquals(ConfProperties.getProperty("profile_page"), homePage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Repositories button click test")
    public void repositoriesBtnClickTest() {
        homePages.forEach(homePage -> {
            homePage.profileIconClick();
            homePage.repositoriesBtnClick();
            homePage.waitForUrl(ConfProperties.getProperty("repositories_page"));
            assertEquals(ConfProperties.getProperty("repositories_page"), homePage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Example repository click test")
    public void exampleRepositoryClickTest() {
        homePages.forEach(homePage -> {
            homePage.exampleRepositoryClick();
            assertEquals(ConfProperties.getProperty("example_repository_page"), homePage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("New repository button click test")
    public void newRepositoryBtnClickTest() {
        homePages.forEach(homePage -> {
            homePage.newRepositoryBtnClick();
            assertEquals(ConfProperties.getProperty("new_repository_page"), homePage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Sign out button click test")
    public void signOutBtnClickTest() {
        homePages.forEach(homePage -> {
            homePage.profileIconClick();
            homePage.signOutBtnClick();
            homePage.waitForUrl(ConfProperties.getProperty("start_page"));
            assertEquals(ConfProperties.getProperty("start_page"), homePage.getWebDriver().getCurrentUrl());
        });
        startPages.forEach(startPage -> assertTrue(startPage.getSignUpBtn().isDisplayed()));
    }



}
