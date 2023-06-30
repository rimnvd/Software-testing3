import utils.ConfProperties;
import utils.driver.DriversHandler;
import utils.driver.exception.InvalidPropertiesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RepositoriesPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepositoriesPageTest {

    List<RepositoriesPage> repositoriesPages;

    @BeforeEach
    public void setUp() {
        DriversHandler.initDrivers();
        repositoriesPages = new ArrayList<>();
        if (DriversHandler.isDriversEmpty()) throw new InvalidPropertiesException();
        DriversHandler.drivers.parallelStream().forEach(driver -> {
            driver.get(ConfProperties.getProperty("repositories_page"));
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        });
    }

    @AfterEach
    public void close() {
        DriversHandler.closeDrivers();
    }

    @Test
    @DisplayName("Example repository click test")
    public void exampleRepositoryClick() {
        repositoriesPages.forEach(repositoriesPage -> {
            repositoriesPage.exampleRepositoryClick();
            repositoriesPage.waitForUrl(ConfProperties.getProperty("example_repository_page"));
            assertEquals(ConfProperties.getProperty("example_repository_page"), repositoriesPage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("New repository button click test")
    public void newRepositoryBtnClickTest() {
        repositoriesPages.forEach(repositoriesPage -> {
            repositoriesPage.newRepositoryBtnClick();
            repositoriesPage.waitForUrl(ConfProperties.getProperty("new_repository_page"));
            assertEquals(ConfProperties.getProperty("new_repository_page"), repositoriesPage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("find repository without results test")
    public void findRepositoryWithoutResultsTest() {
        repositoriesPages.forEach(repositoriesPage -> {
            repositoriesPage.enterTextIntoFindRepository("ghj");
            repositoriesPage.waitForUrl("https://github.com/rimnvd?tab=repositories&q=ghj&type=&language=&sort=");
            assertTrue(repositoriesPage.getNoRepositoriesMatching().isDisplayed());
            assertEquals("https://github.com/rimnvd?tab=repositories&q=ghj&type=&language=&sort=", repositoriesPage.getWebDriver().getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Find repository test")
    public void findRepositoryTest() {
        repositoriesPages.forEach(repositoriesPage -> {
            repositoriesPage.enterTextIntoFindRepository("soft");
            repositoriesPage.waitForUrl("https://github.com/rimnvd?tab=repositories&q=soft&type=&language=&sort=");
            repositoriesPage.waitForMatching();
            assertEquals("https://github.com/rimnvd?tab=repositories&q=ghj&type=&language=&sort=", repositoriesPage.getWebDriver().getCurrentUrl());
            assertTrue(repositoriesPage.getFirstRepositoryMatching().isDisplayed());
        });
    }
}
