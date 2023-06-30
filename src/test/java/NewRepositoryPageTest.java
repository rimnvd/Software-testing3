import utils.ConfProperties;
import utils.driver.DriversHandler;
import utils.driver.exception.InvalidPropertiesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class NewRepositoryPageTest {
    List<NewRepositoryPage> newRepositoryPages;
    List<HomePage> homePages;
    List<LoginPage> loginPages;
    List<StartPage> startPages;

    @BeforeEach
    public void setUp() {
        DriversHandler.initDrivers();
        newRepositoryPages = new ArrayList<>();
        homePages = new ArrayList<>();
        loginPages = new ArrayList<>();
        startPages = new ArrayList<>();
        if (DriversHandler.isDriversEmpty()) throw new InvalidPropertiesException();
        DriversHandler.drivers.forEach(driver -> {
            driver.get("https://github.com/login");
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            loginPages.add(new LoginPage(driver));
            homePages.add(new HomePage(driver));
            driver.manage().window().maximize();
        });

        loginPages.forEach(loginPage -> {
//            String login = ConfProperties.getProperty("login");
//            String password = ConfProperties.getProperty("password");
            loginPage.enterLogin("rimnvd");
            loginPage.enterPassword("dredrimer1122");
            loginPage.signInBtnClick();
            loginPage.waitForUrl(ConfProperties.getProperty("start_page"));
        });
        homePages.forEach(HomePage::newRepositoryBtnClick);

        DriversHandler.drivers.forEach(driver -> {
            driver.get("https://github.com/new");
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            newRepositoryPages.add(new NewRepositoryPage(driver));
            driver.manage().window().maximize();
        });
    }

    @AfterEach
    public void close() {
        DriversHandler.closeDrivers();
    }

    @Test
    @DisplayName("Blank repository name test")
    public void blankRepositoryNameTest() {
        newRepositoryPages.forEach(newRepositoryPage -> {
            newRepositoryPage.inputRepositoryName("");
            newRepositoryPage.createRepositoryBtnClick();
            newRepositoryPage.waitForUrl(ConfProperties.getProperty("new_repository_page"));
            assertTrue(newRepositoryPage.getBlankNameMsg().isDisplayed());
        });
    }

    @Test
    @DisplayName("Create repository test")
    public void createRepositoryTest() {
        newRepositoryPages.get(0).inputRepositoryName(ConfProperties.getProperty("new_repository_name1"));
        assertTrue(newRepositoryPages.get(0).getBlankNameMsg().isDisplayed());
        newRepositoryPages.get(0).waitForBtn();
        newRepositoryPages.get(0).createRepositoryBtnClick();
        newRepositoryPages.get(0).waitForUrl("https://github.com/rimnvd/software-testing3");
        assertEquals("https://github.com/rimnvd/software-testing3", newRepositoryPages.get(0).getWebDriver().getCurrentUrl());
        newRepositoryPages.get(1).inputRepositoryName(ConfProperties.getProperty("new_repository_name2"));
        //assertTrue(newRepositoryPages.get(1).getBlankNameMsg().isDisplayed());
        //newRepositoryPages.get(1).createRepositoryBtnClick();
        //newRepositoryPages.get(1).waitForUrl("https://github.com/rimnvd/software-testing4");
        //assertEquals("https://github.com/rimnvd/software-testing4", newRepositoryPages.get(1).getWebDriver().getCurrentUrl());
    }
}