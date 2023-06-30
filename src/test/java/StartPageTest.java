import utils.ConfProperties;
import utils.driver.DriversHandler;
import utils.driver.exception.InvalidPropertiesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StartPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class StartPageTest {

    public List<StartPage> startPages;

    @BeforeEach
    public void setUp() {
        DriversHandler.initDrivers();
        startPages = new ArrayList<>();
        if (DriversHandler.isDriversEmpty()) throw new InvalidPropertiesException();
        DriversHandler.drivers.forEach(driver -> {
            driver.get(ConfProperties.getProperty("start_page"));
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            startPages.add(new StartPage(driver));
            driver.manage().window().maximize();
        });
    }

    @AfterEach
    public void close() {
        DriversHandler.closeDrivers();
    }

    @Test
    @DisplayName("Pricing button click test")
    public void pricingBtnClickTest() {
        startPages.forEach(startPage -> {
            startPage.pricingBtnClick();
            startPage.waitForUrl(ConfProperties.getProperty("pricing_page"));
            assertEquals(ConfProperties.getProperty("pricing_page"), startPage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Sign in button click test")
    public void signInBtnClickTest() {
        startPages.forEach(startPage -> {
            startPage.signInBtnClick();
            startPage.waitForUrl(ConfProperties.getProperty("login_page"));
            assertEquals(ConfProperties.getProperty("login_page"), startPage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Sign in button click test")
    public void signUpBtnClickTest() {
        startPages.forEach(startPage -> {
            startPage.signUpBtnClick();
            startPage.waitForUrl(ConfProperties.getProperty("sign_up_page"));
            assertEquals(ConfProperties.getProperty("sign_up_page"), startPage.getWebDriver().getCurrentUrl());
        });
    }



    @Test
    @DisplayName("Product button hover test")
    public void productBtnHoverTest() {
        startPages.parallelStream().forEach(startPage -> {
            assertFalse(startPage.getProductWindow().isDisplayed());
            startPage.productBtnHover();
            assertTrue(startPage.getProductWindow().isDisplayed());
            startPage.moveToElement(ConfProperties.getProperty("move_to_element"));
            assertFalse(startPage.getProductWindow().isDisplayed());
        });
    }
//
    @Test
    @DisplayName("Solutions button hover test")
    public void solutionsBtnHoverTest() {
        startPages.parallelStream().forEach(startPage -> {
            assertFalse(startPage.getSolutionsWindow().isDisplayed());
            startPage.solutionsBtnHover();
            assertTrue(startPage.getSolutionsWindow().isDisplayed());
            startPage.moveToElement(ConfProperties.getProperty("move_to_element"));
            assertFalse(startPage.getSolutionsWindow().isDisplayed());
        });
    }

    @Test
    @DisplayName("Open Source button hover test")
    public void openSourceBtnHoverTest() {
        startPages.parallelStream().forEach(startPage -> {
            assertFalse(startPage.getOpenSourceWindow().isDisplayed());
            startPage.openSourceBtnHover();
            assertTrue(startPage.getOpenSourceWindow().isDisplayed());
            startPage.moveToElement(ConfProperties.getProperty("move_to_element"));
            assertFalse(startPage.getOpenSourceWindow().isDisplayed());
        });
    }
}
