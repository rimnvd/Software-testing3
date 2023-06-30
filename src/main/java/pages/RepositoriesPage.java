package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RepositoriesPage {
    private final WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"user-repositories-list\"]/ul/li[2]/div[1]/div[1]/h3/a")
    public WebElement exampleRepository;

    @FindBy(xpath = "//a[contains(@class, 'ml-3')]")
    public WebElement newRepositoryBtn;

    @FindBy(xpath = "//*[@id=\"your-repos-filter\"]")
    public WebElement findRepository;

    @FindBy(xpath = "//*[@id=\"user-repositories-list\"]/ul/li/div[1]/div[1]/h3/a")
    public WebElement firstRepositoryMatching;
    

    public RepositoriesPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void exampleRepositoryClick() {
        exampleRepository.click();
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void newRepositoryBtnClick() {
        newRepositoryBtn.click();
    }

    public void enterTextIntoFindRepository(String input) {
        findRepository.sendKeys(String.valueOf(input));
    }

    public WebElement getNoRepositoriesMatching() {
        return webDriver.findElement(By.xpath("//*[@id=\"user-repositories-list\"]/div[2]/h2"));
    }

    public WebElement getFirstRepositoryMatching() {
        return webDriver.findElement(By.xpath("//*[@id=\"user-repositories-list\"]/ul/li/div[1]/div[1]/h3/a"));
    }

    public void waitForMatching() {
        new WebDriverWait(webDriver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(firstRepositoryMatching));
    }








}
